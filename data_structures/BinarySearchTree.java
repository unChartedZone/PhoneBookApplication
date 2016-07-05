/*Christian Valdez
  masc0385
*/

package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class BinarySearchTree<K,V> implements DictionaryADT<K,V> {

    private Node<K,V> root;
    private int currentSize;
    private long modCounter;
    private K foundKey;
    private boolean usedSuccessorLast = true;

    public BinarySearchTree() {
        root = null;
        currentSize = 0;
    }

    public boolean contains(K key) {
        return getValue(key)!= null;
    }

    public boolean add(K key, V value) {
        if(root == null)
            root = new Node<K,V>(key,value);
        else if(contains(key)) return false;
        else
            insert(key,value,root,null,false);
        modCounter++;
        currentSize++;
        return true;
    }

    private void insert(K k, V v, Node<K,V> n, Node<K,V> parent, boolean wasLeft) {
        if(n == null) {
            if(wasLeft) parent.left = new Node<K,V>(k,v);
            else parent.right = new Node<K,V>(k,v);
        }
        else if (((Comparable<K>)k).compareTo((K)n.key) < 0) //fix this what if its greater
            insert(k,v,n.left,n,true);
        else
            insert(k,v,n.right,n,false);
    }

    public boolean delete(K key) {
        if(currentSize == 0) return false;
        Node<K,V> newNode = getNode(key,root);
        if(newNode == null) return false;
        if(newNode == root) return deletRoot();
        if(newNode.left != null && newNode.right != null) return twoChild(key,newNode);
        if(newNode.left == null && newNode.right == null) return noChild(key);
        return oneChild(key);
    }

    //Only call for Deleted
    private Node<K,V> getNode(K key, Node<K,V> n) {
        if(n == null) return null;
        int compare = ((Comparable<K>)key).compareTo(n.key);
        if(compare < 0)
            return getNode(key, n.left);
        if(compare > 0)
            return getNode(key, n.right);
        return n;
    }

    //Only called for Delte
    private Node<K,V> getParent(K key, Node<K,V> n, Node<K,V> parent) {
        if(n == null) return null;
        int compare = ((Comparable<K>)key).compareTo(n.key);
        if(compare < 0) {
            parent = n;
            return getParent(key, n.left, parent);
        }
        if(compare > 0) {
            parent = n;
            return getParent(key, n.right, parent);
        }
        if(parent == null) return null;
        return parent;
    }

    private boolean deletRoot(){
        if(currentSize != 1) {
            if (root.left != null) {
                Node<K, V> predecessor = root.left;
                while (predecessor.right != null)
                    predecessor = predecessor.right;
                Node<K,V> temp = predecessor.left;
                Node<K,V> preParent = getParent(predecessor.key, root, null);
                if (predecessor != root.left) {
                    predecessor.left = root.left;
                    predecessor.right = root.right;
                    preParent.right = null;
                    root = predecessor;
                    if (temp != null)
                        preParent.right = temp;
                }
                if (predecessor == root.left) {
                    predecessor.right = root.right;
                    root = predecessor;
                }
                currentSize--;
                modCounter++;
                return true;
            }
            if (root.left == null && currentSize != 1) {
                Node<K, V> successor = root.right;
                while (successor.left != null)
                    successor = successor.left;
                Node<K, V> temp = successor.right;
                Node<K, V> succParent = getParent(successor.key, root, null);
                if (successor != root.right) {
                    successor.left = root.left;
                    successor.right = root.right;
                    succParent.left = null;
                    root = successor;
                    if (temp != null)
                        succParent.left = temp;
                }
                if (successor == root.right) {
                    successor.left = root.left;
                    root = successor;
                }
                currentSize--;
                modCounter++;
                return true;
            }
        }
        if(currentSize == 1)
            root = null;
        modCounter++;
        currentSize--;
        return true;
    }

    //For Delete
    private boolean twoChild(K key,Node<K,V> newNode) {
        Node<K,V> parent = getParent(key,root,null);
        if(usedSuccessorLast) {
            usedSuccessorLast = false;
            usePredecessor(newNode,parent);
        }
        else {
            usedSuccessorLast = true;
            useSuccessor(newNode,parent);
        }
        modCounter++;
        currentSize--;
        return true;
    }

    //Used for Delete but only for the two child condition
    private void usePredecessor(Node<K,V> newNode, Node<K,V> parent) {
        Node<K,V> predecessor = newNode.left;
        while(predecessor.right != null)
            predecessor = predecessor.right;
        Node<K,V> temp  = predecessor.left;
        Node<K,V> preParent = getParent(predecessor.key,root,null);
        if(predecessor != newNode.left) {
            if (newNode == parent.left) {
                predecessor.left = newNode.left;
                predecessor.right = newNode.right;
                preParent.right = null;
                parent.left = predecessor;
            }
            if (newNode == parent.right) {
                predecessor.left = newNode.left;
                predecessor.right = newNode.right;
                preParent.right = null;
                parent.right = predecessor;
            }
            if (temp != null)
                preParent.right = temp;
        }
         if(newNode == parent.left) {
             predecessor.right = newNode.right;
             parent.left = predecessor;
         }
         if(newNode == parent.right) {
             predecessor.right = newNode.right;
             parent.right = predecessor;
         }
    }

    //Used for Delete but only for the two child condition
    private void useSuccessor(Node<K,V> newNode, Node<K,V> parent) {
        Node<K,V> successor = newNode.right;
        while(successor.left != null)
            successor = successor.left;
        Node<K,V> temp = successor.right;
        Node<K,V> succParent = getParent(successor.key,root,null);
        if(successor != newNode.right) {
            if (newNode == parent.left) {
                successor.left = newNode.left;
                successor.right = newNode.right;
                succParent.left = null;
                parent.left = successor;
            }
            if (newNode == parent.right) {
                successor.left = newNode.left;
                successor.right = newNode.right;
                succParent.left = null;
                parent.right = successor;
            }
            if (temp != null)
                succParent.left = temp;
        }
        if(newNode == parent.left) {
            successor.left = newNode.left;
            parent.left = successor;
        }
        if(newNode == parent.right) {
            successor.left = newNode.left;
            parent.right = successor;
        }
    }


    //Used only for Delete
    private boolean noChild(K key) {
        Node<K,V> newNode = getNode(key,root);
        Node<K,V> parent = getParent(key,root,null);
        if(parent.left == newNode)
            parent.left = null;
        if(parent.right == newNode)
            parent.right = null;
        modCounter++;
        currentSize--;
        return true;
    }

    //Used only for Delete
    private boolean oneChild(K key) {
        Node<K,V> parent = getParent(key,root,null);
        Node<K,V> newNode = getNode(key,root);
        if(parent.right == newNode) {
            if(newNode.right != null)
                parent.right = newNode.right;
            if(newNode.left != null)
                parent.right = newNode.left;
        }
        if(parent.left == newNode) {
            if(newNode.right != null)
                parent.left = newNode.right;
            if(newNode.left != null)
                parent.left = newNode.left;
        }
        modCounter++;
        currentSize--;
        return true;
    }

    public V getValue(K key) {
        return find(key, root);
    }

    private V find(K key, Node<K,V> n) {
        if(n == null) return null;
        int compare = ((Comparable<K>)key).compareTo(n.key);
        if(compare < 0)
            return find(key, n.left);
        if(compare > 0)
            return find(key, n.right);
        return n.value;
    }

    public K getKey(V value) {
        foundKey = null;
        valueFinder(root,value);
        return foundKey;
    }

    private void valueFinder(Node<K,V> n, V value) {
        if(n == null) return;
        if(((Comparable<V>)value).compareTo(n.value) == 0)
            foundKey = n.key;
        valueFinder(n.left,value);
        valueFinder(n.right,value);
    }

    public int size() {
        return currentSize;
    }

    public boolean isFull() {
        return false; //Can never be full
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void clear() {
        currentSize = 0;
        root = null;
        modCounter++;
    }

    public Iterator<K> keys() {
        return new KeyIteratorHelper();
    }

    public Iterator<V> values() {
        return new ValueIteratorHelper();
    }

    private abstract class IteratorHelper<E> implements Iterator<E> {
        protected Node<K,V>[] nodes;
        protected int index;
        protected long modCheck;
        private int j;

        public IteratorHelper() {
            nodes = new Node[currentSize];
            index = 0;
            j = 0;
            modCheck = modCounter;
            inOrder(root);
        }

        private void inOrder(Node<K,V> n) {
            if(n != null) {
                inOrder(n.left);
                nodes[j++] = n;
                inOrder(n.right);
            }
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
        public ValueIteratorHelper() {
            super();
        }
        public V next() {
            return (V) nodes[index++].value;
        }
    }

    private class Node<K,V> {

        K key;
        V value;
        Node<K,V> left;
        Node<K,V> right;

        public Node(K k, V v) {
            key = k;
            value = v;
            left = right = null;
        }
    }
}
