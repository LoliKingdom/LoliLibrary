package zone.rong.lolilib;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;

import java.util.ListIterator;

public class LoliLibTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (transformedName.equals("net.dries007.tfc.util.calendar.ICalendarFormatted")) {
            return modifyStartingYear(bytes);
        }
        if (transformedName.equals("com.mushroom.midnight.common.CommonEventHandler")) {
            return removeEventBusSubscriberAnnotations(bytes);
        }
        if (transformedName.equals("net.minecraft.item.crafting.FurnaceRecipes")) {
            return submitBetterFurnaceRecipesInstance(bytes);
        }
        if (transformedName.equals("net.minecraft.block.Block")) {
            return modifyObjectIntIdentityMap(bytes);
        }
        // if (transformedName.equals("net.minecraft.util.EnumFacing")) {
            // return fixEnumArrayDupe(bytes);
        // }
        // if (transformedName.equals("rustic.core.Rustic")) {
            // return template(transformedName, bytes);
        // }
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

    private byte[] modifyObjectIntIdentityMap(byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);

        node.fields.stream()
                .filter(f -> f.name.equals("BLOCK_STATE_IDS"))
                .findFirst()
                .ifPresent(f -> f.signature = "it.unimi.dsi.fastutil.objects.Reference2IntMap");

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

    /**
     * NEW net/minecraft/util/ResourceLocation
     * DUP
     * LDC DOMAIN_STRINGCONSTANT
     * LDC PATH_STRINGCONSTANT
     * INVOKESPECIAL net/minecraft/util/ResourceLocation.<init> (Ljava/lang/String;Ljava/lang/String;)V
     */
    private byte[] seekForResourceLocations(byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);

        node.fields.stream()
                .filter(f -> f.desc.equals("Lnet/minecraft/util/ResourceLocation;"))
                .forEach(f -> f.access |= Opcodes.ACC_FINAL);

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        return writer.toByteArray();
    }

    /*
    private static byte[] template(String transformedName, byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);


        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        return writer.toByteArray();
    }
     */

    public static class ASMHelperMethods {



    }

}
