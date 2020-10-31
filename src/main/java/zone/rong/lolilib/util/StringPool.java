package zone.rong.lolilib.util;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;

public final class StringPool extends Reference2ReferenceOpenHashMap<String, String> {

    public static final StringPool POOL = new StringPool(20000);

    private StringPool(int expectedSize) {
        super(expectedSize);
    }

    public String getCanonicalString(String s) {
        String canon;
        if ((canon = this.putIfAbsent(s, s)) == null) {
            if (size >= 20000) {
                this.clear();
            }
            return s;
        }
        return canon;
    }

}
