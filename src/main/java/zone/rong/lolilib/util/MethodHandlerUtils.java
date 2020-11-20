package zone.rong.lolilib.util;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

/**
 * Class that helps with MethodHandle's invoking, unreflecting etc.
 *
 * CACHE IN FINAL VARS WHERE EVER POSSIBLE!
 */
public class MethodHandlerUtils {

    public static final MethodHandles.Lookup lookup = MethodHandles.lookup();

    public static MethodHandle retrieveMethod(Class<?> clazz, String methodName, Class<?>... params) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, params);
            method.setAccessible(true);
            return lookup.unreflect(method);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private MethodHandlerUtils() {}

}
