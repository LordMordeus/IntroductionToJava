package lesson1;

public class Main {
    public static void main(String[] args) {
//        Cat cat = new Cat();
////        cat.jump();
////
////        Jumpable cat1 = new Cat();
////        cat1.jump();
////        cat1.superJump();
////        ((Cat) cat1).speedJump(5);
////        ((Cat) cat1).voice();
////        ((Cat) cat1).sleep();
////
////        Sleepable cat2 = new Cat();
////        cat2.sleep();
////
////        sleep(cat);

//        Outer out = new Outer();
//        Outer.Inner in = out.new Inner(6,7);
//        in.printOutValues();
//        Outer.MyStaticClass msc = new Outer.MyStaticClass();
//        System.out.println(msc.f);


        jumper(new Jumpable() {
            @Override
            public void jump() {
                System.out.println("yo!!! jump!!!");
            }
        });

        jumper(() ->{System.out.println("jump!!!");});

    }

    static void jumper(Jumpable j){
        j.jump();
    }

    static void sleep(Sleepable sleepable){
        sleepable.sleep();
        class LocalClass{
            private int a;

            public LocalClass(int a) {
                this.a = a;
            }

            public int getA() {
                return a;
            }
        }
        System.out.println(new LocalClass(67).getA());
    }
}
