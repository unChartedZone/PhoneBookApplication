public class Tester {


    public static void main(String[] args) {
        PhoneBook book = new PhoneBook(40000);

        book.load("p4_data.txt");
//
        PhoneNumber num1 = new PhoneNumber("619-251-7863");
        PhoneNumber num2 = new PhoneNumber("619-384-1004");
        PhoneNumber num3 = new PhoneNumber("619-251-7841");
        PhoneNumber num4 = new PhoneNumber("250-675-6971");

        book.addEntry(num1,"Valdez, Christian");
        book.addEntry(num2,"Kathleen");
        book.addEntry(num3,"Alex");
        book.addEntry(num4,"Caleb");


        System.out.println(book.nameLookup("Alexander, Chris"));
        System.out.println(book.numberLookup(num1));
        System.out.println(book.nameLookup("Kathleen"));

//        book.printAll();
//        book.printByAreaCode("619");
//        System.out.println();
//        book.deleteEntry(num2);
//        book.printAll();
//        book.printNames();
//        book.printByAreaCode("619");
    }
}
