/*Christian Valdez
  masc0385
 */

import data_structures.*;
import java.util.Iterator;
import java.io.*;

public class PhoneBook {

    DictionaryADT<PhoneNumber,String> phoneBook;

    public PhoneBook(int maxSize){
        phoneBook = new Hashtable(maxSize);
    }

    public void load(String filename) {
        String line;
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(filename));
            while((line = in.readLine()) != null) {
                PhoneNumber key = new PhoneNumber(line.substring(0,12));
                String value = line.substring(13);
                if(!addEntry(key, value))
                    throw new RuntimeException("Duplicate Entry!!");
            }
            in.close();
        }
        catch(IOException excp) {
            System.out.println("Sorry, error reading the file." +
                    excp);
        }
        catch(Exception e) {
            System.out.println("ERROR " + e);
        }
    }

    public String numberLookup(PhoneNumber number) {
        return phoneBook.getValue(number);
    }

    public PhoneNumber nameLookup(String name) {
        return phoneBook.getKey(name);
    }

    public boolean addEntry(PhoneNumber number, String name) {
//        if(phoneBook.contains(number) || phoneBook.isFull())
//            return false;
        return phoneBook.add(number,name);
    }

    public boolean deleteEntry(PhoneNumber number){
        return phoneBook.delete(number);
    }

    public void printAll() {
        Iterator keys = phoneBook.keys();
        while(keys.hasNext())
            System.out.println(keys.next());
    }

    public void printByAreaCode(String code) {
        PhoneNumber[] array = new PhoneNumber[phoneBook.size()];
        Iterator nums = phoneBook.keys();
        int i = 0;
        PhoneNumber num;
        String area;
        while(nums.hasNext()) {
            num = (PhoneNumber)nums.next();
            area = num.getAreaCode();
            if(area.equals(code))
                array[i] = num;
            i++;
        }
        for(PhoneNumber x: array)
            if(x != null)
                System.out.println(x);
    }

    public void printNames() {
        String[] array = new String[phoneBook.size()];
        Iterator values = phoneBook.values();
        int i = 0;
        while(values.hasNext()) {
            array[i] = (String)values.next();
            i++;
        }
        array = shellSort(array);

        for(String name:array)
            System.out.println(name);
    }

    private String[] shellSort(String array[]) {
        String[] n = array;
        int in,out, h = 1;
        String temp;
        int size = n.length;

        while(h <= size/3)
            h = h*3+1;
        while(h > 0) {
            for(out = h; out < size; out++) {
                temp = n[out];
                in = out;
                while(in > h-1 && ((Comparable<String>)n[in-h]).compareTo(temp) >= 0) {
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
