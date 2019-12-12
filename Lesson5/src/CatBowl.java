public class CatBowl {
    private int food;

    public CatBowl(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public boolean decreaseFood(int appetite) {
        if (food - appetite < 0) {
            return false;
        }
        else {
            food -= appetite;
            return true;
        }
    }

    public void info() {
        System.out.println("plate " + food);
    }

    public void addFood(int food) {
        System.out.println("Add food to the bowl");
        this.food += food;
    }
}

