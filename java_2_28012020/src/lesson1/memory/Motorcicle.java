package lesson1.memory;

public class Motorcicle extends Transport{

    public Motorcicle(int speed, String model) {
        super(speed, model);

    }

    @Override
    void go() {
        System.out.println("go go go");
    }

    public Motorcicle() {
    }

    @Override
    void voice() {
        System.out.println(".....");
    }
}
