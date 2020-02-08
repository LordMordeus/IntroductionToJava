package lesson1;

@FunctionalInterface
public interface Jumpable {
    void jump();

    default void superJump() {
        System.out.println("superJump");
    }
}
