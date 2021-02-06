package zone.rong.lolilib.util;

import com.google.common.collect.Iterators;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.util.ObjectIntIdentityMap;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class EnhancedObjectIntIdentityMap<T> extends ObjectIntIdentityMap<T> {

    protected final Reference2IntMap<T> identityMap;
    protected final List<T> objectList;

    public EnhancedObjectIntIdentityMap() {
        this(512);
    }

    public EnhancedObjectIntIdentityMap(int expectedSize) {
        this.objectList = new ObjectArrayList<>(expectedSize);
        this.identityMap = new Reference2IntOpenHashMap<>(expectedSize);
        this.identityMap.defaultReturnValue(-1);
    }

    @Override
    public Iterator<T> iterator() {
        return Iterators.filter(this.objectList.iterator(), Objects::nonNull);
    }

    @Override
    public void put(T key, int value) {
        this.identityMap.put(key, value);
        while (this.objectList.size() <= value) {
            this.objectList.add(null);
        }
        this.objectList.set(value, key);
    }

    @Override
    public int get(T key) {
        return this.identityMap.getInt(key);
    }

    @Nullable
    @Override
    public T getByValue(int value) {
        return value >= 0 && value < this.objectList.size() ? this.objectList.get(value) : null;
    }

    @Override
    public int size() {
        return this.identityMap.size();
    }

    public void clear() {
        this.identityMap.clear();
        this.objectList.clear();
    }

    public void remove(T key) {
        int prev = this.identityMap.removeInt(key);
        if (prev != -1) {
            this.objectList.set(prev, null);
        }
    }

}
