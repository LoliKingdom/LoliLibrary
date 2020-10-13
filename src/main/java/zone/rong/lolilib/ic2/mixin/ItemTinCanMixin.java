package zone.rong.lolilib.ic2.mixin;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.core.item.misc.ItemTinCan;
import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.dries007.tfc.api.capability.food.FoodHandler;
import net.dries007.tfc.api.capability.food.IFoodStatsTFC;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import zone.rong.lolilib.ic2.IC2Main;

@Mixin(ItemTinCan.class)
public abstract class ItemTinCanMixin extends Item implements IItemSize {

    /**
     * @author Rongmario
     */
    @Overwrite
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            items.add(new ItemStack(this, 1, 0));
            for (int meta : ClassicRecipes.canningMachine.getEffectMap().keySet()) {
                ItemStack metaStack = new ItemStack(this, 1, meta);
                metaStack.getCapability(CapabilityFood.CAPABILITY, null).setNonDecaying();
                items.add(metaStack);
            }
        }
    }

    @Redirect(method = "onFoodEaten", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/FoodStats;addStats(IF)V"), remap = false)
    private void redirectToTFCFoodStats(FoodStats foodStats, int foodLevelIn, float foodSaturationModifier, ItemStack stack, World world, EntityPlayer player) {
        ((IFoodStatsTFC) foodStats).addStats(stack);
    }

    @Override
    public Size getSize(ItemStack itemStack) {
        return Size.SMALL;
    }

    @Override
    public Weight getWeight(ItemStack itemStack) {
        return Weight.VERY_LIGHT;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return stack.getItemDamage() == 0 ? null : new FoodHandler(null, IC2Main.FILLED_TIN_CAN_FOOD_DATA);
    }

}
