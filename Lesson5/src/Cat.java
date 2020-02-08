public class Cat {
    private String name;
    private int appetite;
    private boolean fed;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void eat(CatBowl catBowl) {
        fed = catBowl.decreaseFood(appetite);
    }



    public void info() {
        System.out.println(name + " " + (fed ? " full " : " hungry "));
    }
}
