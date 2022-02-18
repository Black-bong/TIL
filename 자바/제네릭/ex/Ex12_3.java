import java.util.ArrayList;
import java.util.List;

class Fruit implements Eatable {

    @Override
    public String toString() {
        return "Fruit";
    }
}

class Apple extends Fruit {

    @Override
    public String toString() {
        return "Apple";
    }
}

class Grape extends Fruit {

    @Override
    public String toString() {
        return "Grape";
    }
}

interface Eatable {}

class Toy {

    @Override
    public String toString() {
        return "Toy";
    }
}

public class Ex12_3 {
    public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<>();
        FruitBox<Apple> appleBox = new FruitBox<>();
        FruitBox<Grape> grapeBox = new FruitBox<>();

        Box<Toy> toyBox = new Box<>();

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple());
        fruitBox.add(new Grape());
        appleBox.add(new Apple());
        grapeBox.add(new Grape());

        System.out.println("fruitBox: " + fruitBox);
        System.out.println("appleBox: " + appleBox);
        System.out.println("grapeBox: " + grapeBox);
    }
}

class FruitBox<T extends Fruit & Eatable> extends Box<T> {}

class Box<T> {
    List<T> list = new ArrayList<>();
    
    public void add(T item) {
        list.add(item);
    }

    T get(int i) {
        return list.get(i);
    }

    int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
