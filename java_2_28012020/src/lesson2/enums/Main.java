package lesson2.enums;

public class Main {
    public static void main(String[] args) {
//        System.out.println(Fruit.APPLE);
        for (Fruit f : Fruit.values()) {
            System.out.println(f);
            System.out.println(f.ordinal());
            System.out.println(f.getRusName());
            System.out.println(f.getWeight());

        }

        Fruit fruit1 = Fruit.CHERY;
        Fruit fruit2 = Fruit.CHERY;
        System.out.println(fruit1 == fruit2);
    }
}
