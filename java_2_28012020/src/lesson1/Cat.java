package lesson1;

public class Cat implements Sleepable, SpeedJumpable {
    @Override
    public void speedJump(int speed) {

    }

    @Override
    public void jump() {
        System.out.println("cat jump!");
    }

    @Override
    public void sleep() {
        System.out.println("cat sleep...");
    }

    void voice(){
        System.out.println("miaw");
    }
}
