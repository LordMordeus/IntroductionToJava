public class Runner {
    public static void main(String[] args) {
        Cat[] cats = {new Cat("Koosya", 60),
                new Cat("Kotya", 30),
                new Cat("Kasya", 25),
        };
        CatBowl plate = new CatBowl(100);
        for (Cat anotherCat : cats) {
            anotherCat.eat(plate);
            anotherCat.info();
            plate.info();
        }
        plate.addFood(100);
        plate.info();
    }
}
