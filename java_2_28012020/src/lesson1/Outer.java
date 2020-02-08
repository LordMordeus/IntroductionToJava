package lesson1;

public class Outer {
    private Inner[] inners= new Inner[]{
            new Inner(5,8),
            new Inner(1,2),
            new Inner(6,7)
    };

    private int outX = 9;

    static class MyStaticClass{
        int f =123;
    }

    class Inner{
        int x;
        int y;

        public Inner(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void printOutValues(){
            System.out.println(outX);
        }
    }

//    void printInValues(){
//        Inner in1 = new Inner(5,8);
//        System.out.println(in1.x);
//    }
}
