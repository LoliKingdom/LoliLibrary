package zone.rong.lolilib.ic2.mixin;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.machine.IFoodCanEffect;
import ic2.core.IC2;
import ic2.core.item.misc.ItemTinCan;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import zone.rong.lolilib.ic2.IC2Main;

@Mixin(ItemTinCan.class)
public abstract class ItemTinCanMixin extends Item/* implements IItemSize*/ { }

    /*
    /**
     * @author Rongmario

    @Overwrite
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (int meta : ClassicRecipes.canningMachine.getEffectMap().keySet()) {
                ItemStack metaStack = new ItemStack(this, 1, meta);
                if (metaStack.hasCapability(CapabilityFood.CAPABILITY, null)) {
                    metaStack.getCapability(CapabilityFood.CAPABILITY, null).setNonDecaying();
                }
                items.add(metaStack);
            }
        }
    }

    /**
     * @author Rongmario

    @Overwrite(remap = false)
    public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        IC2.achievements.issueStat(player, "foodcanEaten");
        // player.heal(2.0F);
        ItemStack can = Ic2Items.tinCan.copy();
        if (!player.inventory.addItemStackToInventory(can)) {
            player.dropItem(can, false);
        } else {
            player.openContainer.detectAndSendChanges();
        }
        ((IFoodStatsTFC) player.getFoodStats()).addStats(stack);
        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        player.addStat(StatList.getObjectUseStats(this));
        int meta = stack.getItemDamage();
        if (meta != 0) {
            IFoodCanEffect effect = ClassicRecipes.canningMachine.getEffectFromID(meta);
            if (effect != null) {
                effect.onEaten(stack, world, player);
            }
        }
    }

    /*
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

     */
