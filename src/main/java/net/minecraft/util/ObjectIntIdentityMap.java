package net.minecraft.util;

import com.google.common.collect.Iterators;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * {@link zone.rong.lolilib.LoliLibTransformer}
 */
public class ObjectIntIdentityMap<T> implements IObjectIntIterable<T> {

    protected final Reference2IntMap<T> identityMap;
    protected final List<T> objectList;

    public ObjectIntIdentityMap() {
        this(512);
    }

    public ObjectIntIdentityMap(int expectedSize) {
        this.objectList = new ObjectArrayList<>(expectedSize);
        this.identityMap = new Reference2IntOpenHashMap<>(expectedSize);
        this.identityMap.defaultReturnValue(-1);
    }

    @Override
    public Iterator<T> iterator() {
        return Iterators.filter(this.objectList.iterator(), Objects::nonNull);
    }

    public void put(T key, int value) {
        this.identityMap.put(key, value);
        while (this.objectList.size() <= value) {
            this.objectList.add(null);
        }
        this.objectList.set(value, key);
    }

    public int get(T key) {
        return this.identityMap.getInt(key);
    }

    @Nullable
    public final T getByValue(int value) {
        return value >= 0 && value < this.objectList.size() ? this.objectList.get(value) : null;
    }

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
