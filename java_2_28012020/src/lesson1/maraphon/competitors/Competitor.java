package lesson1.maraphon.competitors;

public interface Competitor {
    void run(int dist);
    void jump(int heigth);
    void swim(int dist);
    boolean isDistance();
    void info();
}
