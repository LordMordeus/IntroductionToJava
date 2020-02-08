package Lesson1.Maraphon.Obstacles;

import Lesson1.Maraphon.Competitors.Competitor;

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