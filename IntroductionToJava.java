package JetBrainsTasks;

public class IntroductionToJava {
    public static void main(String[] args) {
        byte a = 5;
        short b = 15;
        int c = 23;
        long d = 158;
        float e = 11.5f;
        double f = 38.5;
        boolean bool = true;
        char g = 'A';
    }

    private static double calculation(double a, double b, double c, double d) {
        return a * (b + (c / d));
    }

    private static boolean task4(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    private static void task5(int a) {
        if (a >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    private static boolean task6(int a) {
        return a < 0;
    }

    private static void task7 (String userName) {
        System.out.println("Привет, " + userName + "!");
    }

    private static void task8(int a) {
        if (((a % 4 == 0) && !(a % 100 == 0)) || (a % 400 == 0)) {
            System.out.println(a + " год является високосным");
        }else {
            System.out.println(a + " год не является високосным");
        }
    }
}
