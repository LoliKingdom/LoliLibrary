package zone.rong.lolilib;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;

import java.util.ListIterator;

public class LoliLibTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (transformedName.equals("net.dries007.tfc.util.calendar.ICalendarFormatted")) {
            return modifyStartingYear(bytes);
        }
        // if (transformedName.equals("rustic.core.Rustic")) {
            // return template(transformedName, bytes);
        // }
        return bytes;
    }

    private static byte[] modifyStartingYear(byte[] bytes) {
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

}
