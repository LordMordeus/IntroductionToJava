package Lesson1.Maraphon;

import Lesson1.Maraphon.Competitors.Cat;
import Lesson1.Maraphon.Competitors.Competitor;
import Lesson1.Maraphon.Competitors.Human;
import Lesson1.Maraphon.Competitors.Robot;
import Lesson1.Maraphon.Obstacles.Cross;
import Lesson1.Maraphon.Obstacles.Obstacle;
import Lesson1.Maraphon.Obstacles.Wall;


public class Main {
    public static void main(String[] args) {
        Competitor[] competitors ={
                new Human("Volodya"),
                new Cat("Kusya"),
                new Robot("KozaniyMeshok")
        };
        Obstacle[] obstacles ={
                new Wall(10),
                new Cross(3000)
        };
        for ( Competitor c:competitors ) {
            for (Obstacle o:obstacles ) {
                o.doIt(c);
                if(!c.isDistance()){
                    break;
                }
            }
        }

        for (Competitor c:competitors) {
            c.info();
        }
    }
}