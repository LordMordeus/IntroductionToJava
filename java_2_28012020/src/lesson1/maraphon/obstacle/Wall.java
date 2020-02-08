package lesson1.maraphon.obstacle;

import lesson1.maraphon.competitors.Competitor;

public class Wall extends Obstacle{
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.jump(height);
    }
}
