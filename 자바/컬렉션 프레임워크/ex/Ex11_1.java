package ex;
import java.util.ArrayList;
import java.util.Collections;

class Ex11_1 {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();

        list1.add(5);
        list1.add(4);
        list1.add(2);
        list1.add(0);
        list1.add(1);
        list1.add(3);

        ArrayList<Integer> list2 = new ArrayList<>(list1.subList(1, 4));
        Collections.sort(list1);
        Collections.sort(list2);
        print(list1, list2);
        
        System.out.println("list1.containsAll(list):" + list1.containsAll(list2));

        System.out.println("index = " + list1.indexOf(1));
        list1.remove(0);

        print(list1, list2);

        list1.clear();
        list2.clear();

        print(list1, list2);

        for (int i = 0; i < 5; i++) {
            list1.add(i);
            list2.add(i);
        }

        print(list1, list2);

        for (int i = 0; i < list1.size(); i++) {
            list1.remove(i);
        }
        for (int i = list2.size() - 1; i >= 0; i--) {
            list2.remove(i);
        }

        print(list1, list2);
    }


    static void print(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
        System.out.println();
    }
}