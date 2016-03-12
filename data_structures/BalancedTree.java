/*Christian Valdez
  masc0385
 */

package data_structures;

import java.util.Iterator;
import java.util.TreeMap;

public class BalancedTree<K,V> implements DictionaryADT<K,V>{

    private TreeMap<K,V> map;

    public BalancedTree() {
        map = new TreeMap();
    }

    public boolean contains(K key) {
        return map.containsKey(key);
    }

    public boolean add(K key, V value) {
        if(key == null || value == null || map.containsKey(key))
            return false;
        map.put(key,value);
        return true;
    }

    public boolean delete(K key) {
        if(map.containsKey(key)) {
            map.remove(key);
            return true;
        }
        return false;
    }

    public V getValue(K key) {
        return map.get(key);
    }

    public K getKey(V value) {
        Iterator kIterator = keys();
        Iterator vIterator = values();
        while(kIterator.hasNext()) {
            K temp = (K) kIterator.next();
            if(((Comparable<V>)vIterator.next()).compareTo(value) == 0)
                return temp;
        }
        return null;
    }

    public int size() {
        return map.size();
    }

    public boolean isFull() {
        return false; //Can never be full
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }

    public Iterator<K> keys() {
        return map.keySet().iterator();
    }

    public Iterator<V> values() {
        return map.values().iterator();
    }
}
