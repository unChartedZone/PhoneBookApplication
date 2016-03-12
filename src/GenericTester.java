public class GenericTester {

    public static void main(String[] args) {
        String[] array = new String[4];
        Person[] array2 = new Person[5];

        Person person1 = new Person("Christian");
        Person person2 = new Person("Kathleen");
        Person person3 = new Person("Alex");
        Person person4 = new Person("Tereso");
        Person person5 = new Person("Ramona");

        array2[0] = person1;
        array2[1] = person2;
        array2[2] = person3;
        array2[3] = person4;
        array2[4] = person5;

        array[0] = "Kathleen";
        array[1] = "Alex";
        array[2] = "Chris";
        array[3] = "Alex";

//        array[3] = .03;
//        array[4] = .00004;
//        array[5] = 34.5;
//        array[6] = 7.4;
//        array[7] = 2.7;
//        array[8] = 9.0;
//        array[9] = 3.45;

        GenericSorter.insertionSort(array);
//        GenericSorter.quickSort(array);
//        GenericSorter.shellSort(array);
        GenericSorter.insertionSort(array2);
//        for (int i = 0; i < 4; i++) {
//            System.out.println(array[i]);
//        }

        for(int i = 0; i < 5; i++) {
            System.out.println(array2[i]);
        }

    }

}
