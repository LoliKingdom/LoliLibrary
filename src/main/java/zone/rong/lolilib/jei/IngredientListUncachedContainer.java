package zone.rong.lolilib.jei;

import mezz.jei.ingredients.IngredientFilter;
import zone.rong.lolilib.util.MethodHandlerUtils;

import java.lang.invoke.MethodHandle;

/**
 * Contains the MethodHandle retrieval, so this doesn't initialize too early
 */
public class IngredientListUncachedContainer {

    static final MethodHandle handle_getIngredientListUncached;

    static {
        handle_getIngredientListUncached = MethodHandlerUtils.retrieveMethod(IngredientFilter.class, "getIngredientListUncached", String.class);
    }

}
