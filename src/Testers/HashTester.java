package Testers;

import data_structures.*;

import java.security.Key;
import java.util.Iterator;

public class HashTester {

    public static void main(String[] args) {
        Hashtable<Integer,String> table = new Hashtable(10);

        table.add(1, "Chris");
        table.add(2, "Kathleen");
        table.add(2,"Jordan");
        table.add(3, "Caleb");
        table.add(4, "Alex");

        Iterator keys = table.keys();
        while(keys.hasNext()) {
            System.out.println(keys.next());
        }

//        System.out.println("This is the size: " + table.size());
        System.out.println("Did it delete Kathleen: " + table.delete(2));
//        System.out.println();
//        System.out.println("This is the size: " + table.size());
//        System.out.println();
//        System.out.println("Chris is "  + table.getKey("Chris"));
//        System.out.println("Kathleen is " + table.getKey("Kathleen"));
//        System.out.println("Caleb is " + table.getKey("Caleb"));
//        System.out.println("Alex is " + table.getKey("Alex"));
//        System.out.println("Michael is " + table.getKey("Michael"));
//        System.out.println();
//        System.out.println("Does it contain Chris?: " + table.contains(1));
//        System.out.println("Does it contain Kathleen: "  + table.contains(2));
//        System.out.println("Does it contain Caleb: " + table.contains(3));
//        System.out.println("Does it contain Alex: " + table.contains(4));
//        System.out.println("Does it contain key 5: " + table.contains(5));

//        Iterator keys = table.keys();
        Iterator keys2 = table.keys();
        while(keys2.hasNext()) {
            System.out.println(keys2.next());
        }
//        System.out.println();
//        System.out.println(table.size());
//        System.out.println(table.isFull());
//        System.out.println(table.isEmpty());
//        System.out.println("1 is " + table.getValue(1));
//        System.out.println("2 is " + table.getValue(2));
//        System.out.println("3 is " + table.getValue(3));
//        System.out.println("4 is " + table.getValue(4));
//        System.out.println("8 is " + table.getValue(8));


    }
}
