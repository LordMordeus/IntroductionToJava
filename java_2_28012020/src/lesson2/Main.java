package lesson2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
//        try {
//            int a = 1;
//            int b = 5;
//            System.out.println(b / a);
//
//            try {
//                int[] arr = {3, 4, 5};
//                System.out.println("Сейчас должно быть исключение");
//                arr[99] = 7;
//            } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println("внутренний ");
////                throw e;
//                throw new RuntimeException("сами создали это исключение");
//            }
//
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("внешний ");
//        } catch (ArithmeticException e) {
//            e.printStackTrace();
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            System.out.println(mySqrt(-9));
//        } catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//        }

//        try {
//            InputStream in = new FileInputStream("1.txt");
//            throw new RuntimeException();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("finally .....");
//        }

//        try(InputStream in = new FileInputStream("1.txt")){
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//
//        }

//        System.out.println(div(8, 0));

//        try {
//            someThink("10.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        try {
//            System.out.println(factorial(-6));
//        } catch (FactorialException e) {
////            e.printStackTrace();
//            System.out.println(e.getMessage());
//            System.out.println(e.getNumber());
//        }


        try {
            int x = Integer.parseInt("567.67");
//            double x = Double.parseDouble("567.67");
            System.out.println(x);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String[][] strings ={
                {"123","34534","567"},
                {"123","34534"},
                {"123","34534","reterter","43534"}
        };


        System.out.println("END");
    }

    static int factorial(int n) throws FactorialException{
        if (n < 0) {
            throw new FactorialException("факториал не может быть вычислен от числа < 0", n);
        }
        int f = 1;
        for (int i = 1; i <= n; i++) {
            f *= i;
        }
        return f;
    }

    static int div(int a, int b) throws ArithmeticException {
        return a / b;
    }

    static void someThink(String filename) throws FileNotFoundException {
        InputStream in = new FileInputStream(filename);
    }


    static double mySqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("корень из отрицательного числа не извлекается " + x);
        }
        return Math.sqrt(x);
    }


    static void a() {
        b();
    }

    static void b() {
        c();
    }

    static void c() {
        d();
    }

    static void d() {
        int a = 0;
        int b = 5;
        System.out.println(b / a);
    }

}
