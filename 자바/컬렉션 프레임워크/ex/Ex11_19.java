package ex;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static java.util.Collections.*;

public class Ex11_19 {
    public static void main(String[] args) {
        List list = new ArrayList();
        System.out.println(list);

        addAll(list, 1, 2, 3, 4, 5);
        System.out.println(list);

        rotate(list, 2);
        System.out.println(list);

        swap(list, 0, 2);
        System.out.println(list);

        shuffle(list); // 저장된 요소의 위치를 임의로 변경
        System.out.println(list);

        sort(list, reverseOrder()); // == reverse(list);
        System.out.println(list);

        sort(list);
        System.out.println(list);

        int index = binarySearch(list, 3);
        System.out.println("index of 3 = " + index);

        System.out.println("max = " + max(list));
        System.out.println("min = " + min(list));
        System.out.println("min = " + max(list, reverseOrder()));

        fill(list, 9);
        System.out.println(list);

        List newList = nCopies(list.size(), 2); // list와 같은 크기의 새로운 리스트를 만들고, 2로 채운다. 단, 결과는 변경불가
        System.out.println(newList);

        System.out.println(disjoint(list, newList)); // 공통요소가 없으면 true

        copy(list, newList);
        System.out.println("newList = " + newList);
        System.out.println("list = " + list);

        replaceAll(list, 2, 1);
        System.out.println(list);

        Enumeration e = enumeration(list); // enumeration == iterator
        ArrayList list2 = list(e);

        System.out.println(list2);
    }
}