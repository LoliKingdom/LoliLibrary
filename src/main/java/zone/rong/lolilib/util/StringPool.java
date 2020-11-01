package zone.rong.lolilib.util;

import java.util.WeakHashMap;

public final class StringPool extends WeakHashMap<String, String> {

    public static final StringPool POOL = new StringPool(10000);

    private StringPool(int expectedSize) {
        super(expectedSize);
    }

    public String getCanonicalString(String s) {
        String canon;
        if ((canon = this.putIfAbsent(s, s)) == null) {
            if (size() >= 10000) {
                this.clear();
            }
            return s;
        }
        return canon;
    }

}
