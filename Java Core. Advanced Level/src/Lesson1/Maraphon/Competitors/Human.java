package Lesson1.Maraphon.Competitors;

public class Human implements Competitor{
    String name;
    int maxRunDistance;
    int maxJumpHeight;

    boolean active;

    public Human( String name, int maxRunDistance, int maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.active = true;
    }

    public Human(String name) {
        this(name,1000,5);

    }

    @Override
    public void run(int dist) {
        if (dist <= maxRunDistance) {
            System.out.println(name + " successfully ran");
        } else {
            System.out.println(name + " unsuccessfully ran");
            active = false;
        }
    }

    @Override
    public void jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println(name + " successfully jumped");
        } else {
            System.out.println(name + " unsuccessfully jumped");
            active = false;
        }
    }

    @Override
    public boolean isDistance() {
        return active;
    }

    @Override
    public void info() {
        System.out.println( name + " "+ active);
    }
}

