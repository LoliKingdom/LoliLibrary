package zone.rong.lolilib.jei;

import mezz.jei.api.IIngredientFilter;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
public class LoliJEIPlugin implements IModPlugin {

    static IIngredientFilter ingredientFilter;

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        ingredientFilter = jeiRuntime.getIngredientFilter();
    }

}
