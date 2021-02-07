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

    public LoliLibTransformer() {
        transformations.put("net.dries007.tfc.util.calendar.ICalendarFormatted", this::modifyStartingYear);
        transformations.put("com.mushroom.midnight.common.CommonEventHandler", this::removeEventBusSubscriberAnnotations);
        transformations.put("net.minecraft.item.crafting.FurnaceRecipes", this::submitBetterFurnaceRecipesInstance);
        transformations.put("net.minecraft.util.ObjectIntIdentityMap", bytes -> this.replaceWithExistingClass(bytes, "net.minecraft.util.ObjectIntIdentityMap", false));
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        Function<byte[], byte[]> getBytes = transformations.get(transformedName);
        if (getBytes != null) {
            return getBytes.apply(bytes);
        }
        return bytes;
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



        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        return writer.toByteArray();
    }

}
