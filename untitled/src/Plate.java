public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int n) {
        int difference = food - n;
        if (difference < 0) return false;
        food -= n;
        return true;
    }

    public void info() {
        System.out.println("plate: " + food);
    }

    public void addFood(int food) {
        System.out.println("=====================================\nДобавляем еду: " + food);
        this.food += food;
    }
}