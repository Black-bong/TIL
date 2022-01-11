import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Ex11_12 {
    public static void main(String[] args) {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        Set<Integer> setHab = new HashSet<>();
        Set<Integer> setKyo = new HashSet<>();
        Set<Integer> setCha = new HashSet<>();

        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.add(4);
        setA.add(5);
        System.out.println("A = " + setA);

        setB.add(4);
        setB.add(5);
        setB.add(6);
        setB.add(7);
        setB.add(8);

        System.out.println("B = " + setB);

        // setA.retainAll(setB);
        // setA.removeAll(setB);
        // setA.containsAll(setB);

        System.out.println(setA);
    }
}