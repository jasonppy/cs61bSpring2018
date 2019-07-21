package lab9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    public MyHashMap(int initialSize) {
        buckets = new ArrayMap[initialSize];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int hash = hash(key);
        if (hash >= buckets.length || hash < 0) { return null; }
        return buckets[hash].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        int hash = hash(key);
        if (buckets[hash].containsKey(key)) {
            buckets[hash].remove(key);
            buckets[hash].put(key, value);
            return;
        }
        buckets[hash].put(key, value);
        size++;
        if (loadFactor() > MAX_LF) {
            MyHashMap<K, V> tempMap = new MyHashMap<>(buckets.length * 2);
            for (int i = 0; i < buckets.length; i++) {
                for (K k: buckets[i]) {
                    int newHash = tempMap.hash(k);
                    V val = buckets[i].get(k);
                    tempMap.buckets[newHash].put(k, val);
                }
            }
            this.buckets = tempMap.buckets;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> ks = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            for (K key: buckets[i]) {
                ks.add(key);
            }
        }
        return ks;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        V removedValue = buckets[hash(key)].remove(key);
        if (removedValue != null) { size--; }
        return removedValue;
    }


    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> hashmap = new MyHashMap<>();
        hashmap.put("c", 5);
        hashmap.put("a", 10);
        hashmap.put("d", 22);
        hashmap.put("b", 90);
        hashmap.remove("c");
        Set ks = hashmap.keySet();
    }
}
