/*Christian Valdez
  masc0385
 */

package data_structures;

import java.security.Key;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class Hashtable<K,V> implements DictionaryADT<K,V> {

    private LinearList<Wrapper<K,V>>[] table;
    private int currentSize, maxSize,tableSize;
    private long modCounter;


    public Hashtable(int n) {
        maxSize = n;
        tableSize = (int)(maxSize * 1.3);
        currentSize = 0;
        modCounter = 0;
        table = new LinearList[tableSize];
        for(int i = 0; i < tableSize; i++)
            table[i] = new LinearList<Wrapper<K,V>>();
    }

    public boolean contains(K key) {
        Wrapper<K,V> temp = table[getIndex(key)].find(new Wrapper(key,null));
        if(temp == null) return false;
        return true;
    }

    public boolean add(K key, V value) {
        if(isFull()) return false;
        if(contains(key)) return false;
        Wrapper<K,V> newWrapper = new Wrapper(key,value);
        LinearList<Wrapper<K,V>> list = table[getIndex(key)];
        list.addLast(newWrapper);
        modCounter++;
        currentSize++;
        return true;
    }

    public boolean delete(K key) {
        if(getValue(key) == null) return false;
        table[getIndex(key)].remove(new Wrapper<K,V>(key,null));
        modCounter++;
        currentSize--;
        return true;
    }

    public V getValue(K key) {
        if(!contains(key)) return null;
        return table[getIndex(key)].find(new Wrapper<K,V>(key,null)).value;

    }

    private int getIndex(K key) {
        int index = (key.hashCode() & 0x7FFFFFFF) % tableSize;
        if(index < 0 || index >= tableSize)
            return -1;
        return index;
    }

    public K getKey(V value) {
        for(int i = 0; i < tableSize; i++){
            for(Wrapper<K,V> w: table[i]){
                if(((Comparable<V>)value).compareTo(w.value) == 0)
                    return w.key;
            }
        }
        return null;
    }

    public int size() {
        return currentSize;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void clear() {
        for(int i = 0; i < tableSize; i++){
            table[i].clear();
        }
        currentSize = 0;
        modCounter++;
    }

    public Iterator<K> keys() {
        return new KeyIteratorHelper();
    }

    public Iterator<V> values() {
        return new ValueIteratorHelper();
    }

    private abstract class IteratorHelper<E> implements Iterator<E> {
        protected Wrapper<K,V> [] nodes;
        protected int index;
        protected long modCheck;

        public IteratorHelper() {
            nodes = new Wrapper[currentSize];
            index = 0;
            int j = 0;
            modCheck = modCounter;
            for(int i = 0; i < tableSize; i++)
                for(Wrapper w : table[i])
                    nodes[j++] = w;
            nodes = shellSort(nodes);
        }

        public boolean hasNext() {
            if(modCheck != modCounter)
                throw new ConcurrentModificationException();
            return index < currentSize;
        }

        public abstract E next();

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Wrapper[] shellSort(Wrapper array[]) {
            Wrapper[] n = array;
            int in,out, h = 1;
            Wrapper<K,V> temp;
            int size = n.length;

            while(h <= size/3)
                h = h*3+1;
            while(h > 0) {
                for(out = h; out < size; out++) {
                    temp = n[out];
                    in = out;
                    while(in > h-1 && ((Comparable<Wrapper>)n[in-h]).compareTo(temp) >= 0) {
                        n[in] = n[in-h];
                        in -= h;
                    }
                n[in] = temp;
                }
            h = (h-1)/3;
            }
            return n;
        }

    }

    private class KeyIteratorHelper<K> extends IteratorHelper<K> {
        public KeyIteratorHelper() {
            super();
        }

        public K next() {
            return (K) nodes[index++].key;
        }
    }

    private class ValueIteratorHelper<V> extends IteratorHelper<V> {
        public ValueIteratorHelper(){
            super();
        }

        public V next(){
            return (V) nodes[index++].value;
        }
    }



    private class Wrapper<K,V> implements Comparable<Wrapper<K,V>> {
        K key;
        V value;

        public Wrapper(K key,V value) {
            this.key = key;
            this.value = value;
        }

        public int compareTo(Wrapper<K,V> node) {
            return ((Comparable<K>) key).compareTo((K)node.key);
        }
    }
}
