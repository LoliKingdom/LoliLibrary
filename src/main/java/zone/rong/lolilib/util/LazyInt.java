package zone.rong.lolilib.util;

import java.util.function.IntSupplier;

public class LazyInt {

    private final IntSupplier intSupplier;
    private final int defaultInt;

    private int intValue;

    public LazyInt(IntSupplier intSupplier, int defaultInt) {
        this.intSupplier = intSupplier;
        this.defaultInt = defaultInt;
        this.intValue = intSupplier.getAsInt();
    }

    public int get() {
        return intValue;
    }

    public void invalidate() {
        try {
            intValue = intSupplier.getAsInt();
        } catch (NumberFormatException e) {
            intValue = defaultInt;
        }
    }
}
