
public class Person implements Comparable<Person>{

    private String firstName;

    public Person(String first) {
        firstName = first;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String first) {
        firstName = first;
    }

    @Override
    public int compareTo(Person o) {
        return firstName.compareTo(o.firstName);
    }

    public String toString() {
        return firstName;
    }

//    public int compareTo(String o) {
//        return ((Comparable<String>)firstName).compareTo();
//    }
}
