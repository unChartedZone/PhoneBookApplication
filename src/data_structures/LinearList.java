/*Christian Valdez
  masc0385
 */
package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinearList<E> implements LinearListADT<E>{

    private Node<E> head;
    private Node<E> tail;
    private int currentSize;
    private long modCounter;

    public LinearList(){
        head = null;
        tail = null;
        currentSize = 0;
    }

    public boolean addFirst(E obj) {
        Node<E> newNode = new Node<E>(obj);
        if(head == null){
            head = tail = newNode;

        }
        else{
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        currentSize++;
        modCounter++;
        return true;
    }

    public boolean addLast(E obj) {
        Node<E> newNode = new Node<E>(obj);
        if(head == null)head = tail = newNode;
        else{
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        }
        currentSize++;
        modCounter++;
        return true;
    }

    public E removeFirst(){
        if(head == null)return null;
        E dummyVariable = head.data;
        head = head.next;
        if(head == null) {
            tail = null;
            currentSize--;
            modCounter++;
            return dummyVariable;
        }
        head.previous = null;
        currentSize--;
        modCounter++;
        return dummyVariable;

    }

    public E removeLast() {
        if(head == null)return null;
        E dummyVariable = tail.data;
        tail = tail.previous;
        if(tail == null){
            head = null;
            currentSize--;
            modCounter++;
            return dummyVariable;
        }
        tail.next = null;
        currentSize--;
        modCounter++;
        return dummyVariable;
    }

    public E remove(E obj) {
        Node<E> current = head;
        E dummyVariable = null;
        //Only searches list till the last node
        while(current.next != null){
            if(((Comparable<E>)obj).compareTo(current.data) == 0){
                if(current == head)return removeFirst();
                dummyVariable = current.data;
                current.previous.next = current.next;
                current.next.previous = current.previous;
                currentSize--;
                modCounter++;
                return dummyVariable;
            }
            current = current.next;
        }
        if(current == tail && ((Comparable<E>)obj).compareTo(current.data) == 0){
            return removeLast();
        }
        return null;
    }

    public E peekFirst() {
        if(head == null)return null;
        return head.data;
    }

    public E peekLast() {
        if(head == null)return null;
        return tail.data;
    }

    public boolean contains(E obj) {
        if(find(obj) != null)
            return true;
        return false;
    }

    public E find(E obj) {
        if(head == null) return null;
        Node<E> current = head;
        E dummyVariable = null;
        while(current.next != null){
            if(((Comparable<E>)obj).compareTo(current.data) == 0) {
                dummyVariable = current.data;
                return dummyVariable;
            }
            current = current.next;
        }
        if(current == null)return null;
        if(current == tail && ((Comparable<E>)obj).compareTo(current.data) == 0){
            dummyVariable = current.data;
            return dummyVariable;
        }
        return null;
    }

    public void clear() {
        head = null;
        tail = null;
        currentSize = 0;

    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        //Linked lists are never full
        return false;
    }

    public int size() {
        return currentSize;
    }

    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    private class IteratorHelper implements Iterator<E>{
        long modCheck;
        Node<E> iteratorPointer;

        public IteratorHelper(){
            modCheck = modCounter;
            iteratorPointer = head;
        }

        public boolean hasNext(){
            if(iteratorPointer == null)return false;
            if(modCheck != modCounter)
                throw new ConcurrentModificationException();
            return true;
        }

        public E next(){
            if(!hasNext())
                throw new NoSuchElementException();
            E dummyVariable = iteratorPointer.data;
            iteratorPointer = iteratorPointer.next;
            return dummyVariable;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    protected class Node<T>{
        T data;
        protected Node<T> previous;
        protected Node<T> next;

        public Node(T data){
            this.data = data;
            previous = next = null;
        }
    }
}
