package zone.rong.lolilib;

import net.minecraft.launchwrapper.IClassTransformer;

public class LoliLibTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        // if (transformedName.equals("rustic.core.Rustic")) {
            // return template(transformedName, bytes);
        // }
        return bytes;
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
