/*Christian Valdez
  masc0385
 */

import java.util.Iterator;
import data_structures.*;

public class PhoneNumber implements Comparable<PhoneNumber> {
    String areaCode, prefix, number;
    String phoneNumber;

    public PhoneNumber(String n){
        phoneNumber = n;
        verify(phoneNumber);
    }

    public int compareTo(PhoneNumber n){
        return phoneNumber.compareTo(n.phoneNumber);
    }

    public int hashCode() {
        return phoneNumber.hashCode();
    }

    private void verify(String n) {
        try{
            int area  = Integer.parseInt(getAreaCode());
            int pre = Integer.parseInt(getPrefix());
            int num = Integer.parseInt(getNumber());
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        if (!isHyphen(n)){
            throw new IllegalArgumentException();
        }
    }

    public String getAreaCode() {
        return areaCode = phoneNumber.substring(0,3);
    }

    public String getPrefix(){
        return prefix = phoneNumber.substring(4,7);
    }

    public String getNumber(){
        return number = phoneNumber.substring(8,12);
    }

    private boolean isHyphen(String n) {
        return n.substring(3,4).equals("-") && n.substring(7,8).equals("-");
    }

    public String toString(){
        return phoneNumber;
    }
}
