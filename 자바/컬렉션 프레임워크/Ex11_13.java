import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Ex11_13 {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>(new ReversSort());

        for (int i = 0; i < 10; i ++) {
            set.add((int)(Math.random()*45) + 1);
        }

        System.out.println(set);
        set.stream().sorted(new ReversSort()).forEach(System.out::println);
    }
}

class ReversSort implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }
    
}