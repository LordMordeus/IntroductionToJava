package lesson1.memory;

public class Main {
    private int a;
    int b;
    protected int c;
    public int d;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        if (a > 0) {
            this.a = a;
        } else {
            System.out.println("error a<=0");
        }
    }

    public static void main(String[] args) {

        Transport t1 = new Traktor();
        Transport t2 = new Motorcicle();

        t1.go();
        t2.go();

        System.out.println(t1 instanceof Traktor);
        System.out.println(t2 instanceof Traktor);

        Transport[] transports = {
                new Traktor(),
                new Motorcicle(),
                new Motorcicle(),
        };
        for (int i = 0; i < transports.length; i++) {
            if (transports[i] instanceof Traktor) {
                ((Traktor) transports[i]).pahat();
            }
        }

        for (Transport t : transports) {
            if (t instanceof Traktor) {
                ((Traktor) t).pahat();
            }
        }

    }
}
