/*Christian Valdez
  masc0385
 */

public class GenericSorter<E> {

    E[] on;

    public GenericSorter(E[] array) {
        on = array;
    }

    public static <E> E[] insertionSort(E[] array) {
        E[] on = array;
        int in, out;
        E temp;
        for(out = 1; out < on.length; out++){
            temp = on[out];
            in = out;
            while(in > 0 && ((Comparable<E>)on[in - 1]).compareTo(temp) > 0) {
                on[in] = on[in - 1];
                in--;
            }
            on[in] = temp;
        }
        return array;
    }

    public static <E> E[] shellSort(E[] array) {
        E[]on = array;
        int in,out, h = 1;
        E temp;
        int size = on.length;
        while(h <= size/3)
            h = h*3+1;
        while(h > 0) {
            for(out = h; out < size; out++) {
                temp = on[out];
                in = out;
                while(in > h - 1 && ((Comparable<E>)on[in - h]).compareTo(temp) >= 0) { //This line too
                    on[in] = on[in - h];
                    in -= h;
                }
                on[in] = temp;
            }
        h = (h - 1)/3;
        }
        return on;
    }

    //Following methods are for the quickSort algorithm

    public static <E> E[] quickSort(E[] array) {
        E[]on = array;
            quickSort(0, on.length - 1,on);
            return on;
    }

    private static <E> void quickSort(int left,int right, E[] on) {
        if(right-left <= 0) return;
        E pivot = on[right];
        int partiion = getPartition(left,right,pivot,on);
        quickSort(left, partiion - 1,on);
        quickSort(partiion + 1, right, on);
    }

    private static <E> int getPartition(int left, int right, E pivot, E[] on) {
        int lPtr = left - 1;
        int rPtr = right;
        for(;;) {
            while(((Comparable<E>)on[++lPtr]).compareTo(pivot) < 0) ;
            while(rPtr > 0 && ((Comparable<E>)on[--rPtr]).compareTo(pivot) > 0) ;
            if(lPtr >= rPtr)
                break;
            else
                swap(lPtr,rPtr, on);
        }
        swap(lPtr,right,on);
        return lPtr;
    }

    private static <E> void swap(int one, int two, E[] on) {
        E temp = on[one];
        on[one] = on[two];
        on[two] = temp;
    }
}
