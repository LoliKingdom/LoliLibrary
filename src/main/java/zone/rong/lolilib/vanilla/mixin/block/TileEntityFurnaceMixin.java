package zone.rong.lolilib.vanilla.mixin.block;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import zone.rong.lolilib.vanilla.FurnaceHelper;

@Mixin(TileEntityFurnace.class)
public abstract class TileEntityFurnaceMixin extends TileEntity {

    @Shadow public abstract boolean hasCustomName();

    @Shadow protected String furnaceCustomName;
    @Shadow protected NonNullList<ItemStack> furnaceItemStacks;
    @Shadow public int cookTime;
    @Shadow protected int totalCookTime;
    @Shadow protected int furnaceBurnTime;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", this.furnaceBurnTime);
        compound.setInteger("CookTime", this.cookTime);
        compound.setInteger("CookTimeTotal", this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.furnaceItemStacks);
        if (this.hasCustomName()) {
            compound.setString("CustomName", this.furnaceCustomName);
        }
        return compound;
    }

    /**
     * @author Rongmario
     * @reason Modifiable cook times!
     */
    @Overwrite
    public int getCookTime(ItemStack stack) {
        return FurnaceHelper.getCookTime(stack);
    }

}
