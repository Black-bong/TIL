import java.util.ArrayList;
import java.util.List;

class Product<T> {
    List<T> products = new ArrayList<>();

    public void add(T product) {
        products.add(product);
    }
}

class Tv extends Product {
}

class Audio extends Product {
}

class Ex12_1 {
    public static void main(String[] args) {
        Product<Tv> tv = new Product<>();
        Product<Audio> audio = new Product<>();
        List<Tv> tvList = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        tv.add(new Tv());
        audio.add(new Audio());
        tvList.add(new Tv());
        products.add(new Tv());
        products.add(new Audio());

        printAll(products);
    }

    private static void printAll(List<Product> productList) {
        for (var p : productList) {
            System.out.println(p);
        }
    }
}