package lesson1.memory;

public abstract class Transport {
    public int speed;
    private String model;
    public int year;

    String base;

    void go(){
        System.out.println("go!");
    }

    abstract void voice();

    public Transport(int speed, String model) {
        this.speed = speed;
        this.model = model;
    }

    public Transport() {
    }

    static void someThink(){
        System.out.println("dghdfgdf");
    }

}
