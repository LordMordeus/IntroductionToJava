public class AnimalRunner {
    public static void main(String[] args) {

        Animal[] animals = {new Cat(), new Dog(), new Dog()};

        animals[1].setLimitRun(400);
        animals[1].setLimitJump(0.3);
        animals[2].setLimitRun(600);
        animals[2].setLimitJump(1);

        for (Animal animal : animals) {
            String animalName = animal.getClass().getName();
            System.out.println(animalName + ": run(500):  " + animal.run(500));
            System.out.println(animalName + ": swim(5):   " + animal.swim(5));
            System.out.println(animalName + ": jump(2):   " + animal.jump(1));
            System.out.println();
        }

    }
}
