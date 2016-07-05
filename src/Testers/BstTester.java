package Testers;

import java.util.Iterator;
import data_structures.*;

public class BstTester {
    public static void main(String[] args) {
        BinarySearchTree<Integer,String> tree = new BinarySearchTree();

        tree.add(25,"Chris");
        tree.add(17,"Chris");
        tree.add(32,"Alex");
        tree.add(8,"Kathleen");
        tree.add(22,"Chris");
        tree.add(19,"Chris");
        tree.add(20,"Chris");
        tree.add(4,"Chris");
        tree.add(12,"Chris");
        tree.add(9,"Chris");
        tree.add(27,"Chris");
        tree.add(46,"Chris");

        tree.delete(25);
        tree.delete(32);
//        tree.delete(8);
        tree.delete(19);
        tree.delete(20);
        tree.delete(9);
//        tree.delete(22);
        tree.delete(17);
        tree.delete(4);
        tree.delete(12);
        tree.delete(27);
        tree.delete(46);




//        System.out.println("Should be null " + tree.returnParent(25));
//        System.out.println("Should be 25 " + tree.returnParent(17));
//        System.out.println("Should be 25 " + tree.returnParent(32));
//        System.out.println("Should be 17 " + tree.returnParent(8));
//        System.out.println("Should be 17 "+ tree.returnParent(22));
//        System.out.println("Should be 22 " + tree.returnParent(19));
//        System.out.println("Should be 19 " + tree.returnParent(20));
//        System.out.println("Should be 20 " + tree.returnParent(21));

//        tree.clear();

        Iterator keys = tree.keys();
        Iterator values = tree.values();
        while(keys.hasNext())
            System.out.println(keys.next() + " " + values.next());


//        System.out.println(tree.size());

//        System.out.println(tree.contains(8));
//        System.out.println(tree.getValue(8));
    }
}
