public class Cat {
    private String name;
    private int appetite;
    private boolean satiety;//сытость

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = true;
    }

    void info() {
        System.out.println(name + " имеет аппетит " + appetite + ".\n" + name + " приступает к еде.");
        String isHungry = !satiety ? " сыт/сыта" : " остается голодным/голодной,т.к. в тарелке мало еды.";
        System.out.println(name + isHungry);
    }

    public void eat(Plate p) {
        if (satiety == true && p.decreaseFood(appetite) == true) {
            satiety = false;
        }
        System.out.println("=====================================");
    }
}
