import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ex12_2 {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("김자바", 1, 1));
        list.add(new Student("홍자바", 1, 2));
        list.add(new Student("박자바", 2, 1));

        Iterator<Student> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().name);
        }
    }
}

class Student {
    String name;
    int ban;
    int num;

    public Student(String name, int ban, int num) {
        this.name = name;
        this.ban = ban;
        this.num = num;
    }
}
