package Lesson3;
import java.util.HashMap;


    class ArrayWords {
        public static void main(String[] args) {
            String[] creatures = {"Cat", "Cat", "Dog", "Mouse", "Mouse", "Man", "Hamster", "Cat", "Hamster", "Woman", "Bird", "Fish", "Woman", "Turtle", "Cat"};
            HashMap<String, Integer> creaturesList = new HashMap<>();
            for (String x : creatures) {
                creaturesList.put(x, creaturesList.getOrDefault(x, 0) + 1);
            }
            System.out.println(creaturesList);
        }
    }

