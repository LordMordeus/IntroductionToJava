package lesson2.enums;

public enum Fruit {
    ORANGE("Апельсин",80), APPLE("Яблоко",50),
    BANANA("Банан",150), CHERY("Вишня",20);

    private String rusName;
    private double weight;

    Fruit(String rusName, double weight) {
        this.rusName = rusName;
        this.weight = weight;
    }

    Fruit(String rusName) {
        this.rusName = rusName;
    }

    Fruit() {
    }

    public String getRusName() {
        return rusName;
    }

    public double getWeight() {
        return weight;
    }
}
