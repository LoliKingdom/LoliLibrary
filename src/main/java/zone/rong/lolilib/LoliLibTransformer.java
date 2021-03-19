package zone.rong.lolilib;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import java.io.IOException;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Function;

public class LoliLibTransformer implements IClassTransformer {

    final Map<String, Function<byte[], byte[]>> transformations = new Object2ObjectOpenHashMap<>();

    private final boolean isDeobf = (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

    public LoliLibTransformer() {
        addTransformation("net.dries007.tfc.util.calendar.ICalendarFormatted", this::modifyStartingYear);
        addTransformation("com.mushroom.midnight.common.CommonEventHandler", this::removeEventBusSubscriberAnnotations);
        addTransformation("net.minecraft.util.EnumFacing", this::fixEnumArrayDupe);
        addTransformation("net.minecraft.item.EnumDyeColor", this::fixEnumArrayDupe);
        addTransformation("net.minecraft.client.renderer.block.model.BakedQuad", this::optimizeBakedQuad);
        addTransformation("net.minecraft.util.ObjectIntIdentityMap", bytes -> this.replaceWithExistingClass(bytes, "net.minecraft.util.ObjectIntIdentityMap", false));
        addTransformation("net.minecraft.item.crafting.FurnaceRecipes", bytes -> this.replaceWithExistingClass(bytes, "net.minecraft.item.crafting.FurnaceRecipes", false));
    }

    public void addTransformation(String key, Function<byte[], byte[]> value) {
        LoliLogger.INSTANCE.info("Adding class {} to transformation queue", key);
        transformations.put(key, value);
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        Function<byte[], byte[]> getBytes = transformations.get(transformedName);
        if (getBytes != null) {
            return getBytes.apply(bytes);
        }
        return bytes;
    }

    private byte[] optimizeBakedQuad(byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);

        final String tintIndex = isDeobf ? "tintIndex" : "field_178213_b";

        // Transform tintIndex int field -> byte field
        for (FieldNode field : node.fields) {
            if (field.name.equals(tintIndex)) {
                field.desc = "B";
                break;
            }
        }

        for (MethodNode method : node.methods) {
            if (method.access == 0x1 && method.name.equals("<init>")) {
                ListIterator<AbstractInsnNode> iterator = method.instructions.iterator();
                boolean transformed = false;
                while (iterator.hasNext() && !transformed) {
                    AbstractInsnNode instruction = iterator.next();
                    if (instruction.getOpcode() == Opcodes.PUTFIELD) {
                        FieldInsnNode fieldInstruction = (FieldInsnNode) instruction;
                        if (fieldInstruction.name.equals(tintIndex)) {
                            method.instructions.insertBefore(instruction, new InsnNode(Opcodes.I2B));
                            fieldInstruction.desc = "B";
                            transformed = true;
                        }
                    }
                }
                break;
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        return writer.toByteArray();
    }

    private byte[] replaceWithExistingClass(byte[] bytes, String existingClassName, boolean alignPath) {
        try {
            ClassReader reader = new ClassReader(Launch.classLoader.getClassBytes(existingClassName));
            ClassNode node = new ClassNode();
            reader.accept(node, 0);

            if (alignPath) {
                ClassReader originalReader = new ClassReader(bytes);
                ClassNode originalNode = new ClassNode();
                originalReader.accept(originalNode, 0);

                node.signature = originalNode.signature;
                node.name = originalNode.name;
            }

            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            node.accept(writer);
            return writer.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    private byte[] modifyStartingYear(byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);

        node.methods.stream()
                .filter(m -> m.name.equals("getTotalYears"))
                .findFirst()
                .ifPresent(m -> {
                    for (final ListIterator<AbstractInsnNode> iterator = m.instructions.iterator(); iterator.hasNext();) {
                        AbstractInsnNode instruction = iterator.next();
                        if (instruction instanceof LdcInsnNode && ((LdcInsnNode) instruction).cst instanceof Long) {
                            m.instructions.insert(instruction, new LdcInsnNode(2020L));
                            m.instructions.remove(instruction);
                            break;
                        }
                    }
                });

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        return writer.toByteArray();
    }

    private byte[] removeEventBusSubscriberAnnotations(byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);

        // Remove @EventBusSubscriber(modid = "midnight")
        node.visibleAnnotations.remove(node.visibleAnnotations.stream().filter(a -> a.desc.equals("Lnet/minecraftforge/fml/common/Mod$EventBusSubscriber;")).findFirst().get());

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        return writer.toByteArray();
    }

    private byte[] submitBetterFurnaceRecipesInstance(byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);

        node.methods.stream()
                .filter(n -> n.name.equals("<clinit>"))
                .findFirst()
                .ifPresent(m -> {
                    boolean startRemoving = false;
                    for (final ListIterator<AbstractInsnNode> iterator = m.instructions.iterator(); iterator.hasNext();) {
                        AbstractInsnNode insnNode = iterator.next();
                        if (insnNode.getOpcode() == Opcodes.NEW) {
                            startRemoving = true;
                        } else if (insnNode.getOpcode() == Opcodes.RETURN) {
                            break;
                        } else if (startRemoving) {
                            m.instructions.remove(insnNode);
                        }
                    }
                });

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        return writer.toByteArray();
    }

    private byte[] fixEnumArrayDupe(byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);

        node.methods.stream()
                .filter(m -> m.name.equals("values"))
                .forEach(m -> {
                    AbstractInsnNode returnNode = m.instructions.getLast();
                    m.instructions.remove(returnNode.getPrevious().getPrevious());
                    m.instructions.remove(returnNode.getPrevious());
                });

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        return writer.toByteArray();
    }

}
