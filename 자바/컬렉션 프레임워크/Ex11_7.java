import java.util.Arrays;
import java.util.Comparator;

class Ex11_7 {
    public static void main(String[] args) {
        String[] strArr = {"cat", "Dog", "lion", "tiger"};

        Arrays.sort(strArr);
        System.out.println("strArr = " + Arrays.toString(strArr));

        Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER); // 대소문자 구분안함
        System.out.println("strArr = " + Arrays.toString(strArr));

        Arrays.sort(strArr, new Descending()); // 역순 정렬
        System.out.println("strArr = " + Arrays.toString(strArr));
    }
}

class Descending implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
}