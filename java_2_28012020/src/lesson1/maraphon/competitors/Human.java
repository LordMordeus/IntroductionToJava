package lesson1.maraphon.competitors;

public class Human implements Competitor{
    String name;

    int maxRunDistance;
    int maxJumpHeigth;
    int maxSwimDistance;

    boolean active;

    public Human( String name, int maxRunDistance, int maxJumpHeigth, int maxSwimDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeigth = maxJumpHeigth;
        this.maxSwimDistance = maxSwimDistance;
        this.active = true;
    }

    public Human(String name) {
        this(name,2000,7,200);

    }

    @Override
    public void run(int dist) {
        if (dist <= maxRunDistance) {
            System.out.println(name + " успешно пробежал");
        } else {
            System.out.println(name + " провалил тест на бег");
            active = false;
        }
    }

    @Override
    public void jump(int heigth) {
        if (heigth <= maxJumpHeigth) {
            System.out.println(name + " успешно перепрыгнул");
        } else {
            System.out.println(name + " провалил тест на прыжок");
            active = false;
        }
    }

    @Override
    public void swim(int dist) {
        if (dist <= maxSwimDistance) {
            System.out.println(name + " успешно проплыл");
        } else {
            System.out.println(name + " провалил тест на плавание");
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
