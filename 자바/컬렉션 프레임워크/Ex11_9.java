import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Ex11_9 {
    public static void main(String[] args) {
        Object[] objArr = {"1", (Object)1, "2", "2", "3", "3", "4", "4", "4"};
        Set<Object> set = new HashSet<>();

        for (int i = 0; i < objArr.length; i++) {
            set.add(objArr[i]);
        }

        System.out.println(set);

        Iterator<Object> it = set.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}