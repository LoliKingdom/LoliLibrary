package zone.rong.lolilib.tfc.tile;

import net.dries007.tfc.objects.inventory.capability.IItemHandlerSidedCallback;
import net.dries007.tfc.objects.te.ITileFields;
import net.dries007.tfc.objects.te.TETickableInventory;
import net.dries007.tfc.util.calendar.ICalendarTickable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class TileCustomFirePit extends TETickableInventory implements ICalendarTickable, ITileFields, IItemHandlerSidedCallback {

    public TileCustomFirePit(int inventorySize) {
        super(inventorySize);
    }

    @Override
    public boolean canInsert(int i, ItemStack itemStack, EnumFacing enumFacing) {
        return false;
    }

    @Override
    public boolean canExtract(int i, EnumFacing enumFacing) {
        return false;
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void setField(int i, int i1) {

    }

    @Override
    public int getField(int i) {
        return 0;
    }

    @Override
    public void onCalendarUpdate(long l) {

    }

    @Override
    public long getLastUpdateTick() {
        return 0;
    }

    @Override
    public void setLastUpdateTick(long l) {

    }

}
