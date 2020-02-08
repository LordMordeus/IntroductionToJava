package lesson1.memory;

public class Traktor extends Transport {
    public Traktor(int speed, String model) {
        super(speed, model);
        super.year = 2019;
    }

    public Traktor() {
    }

    @Override
    void go() {
        System.out.println("rrr rr r ");
    }

    @Override
    void voice() {
        System.out.println("ooooo");
    }

    void pahat(){
        System.out.println("pahat");
    }
}
