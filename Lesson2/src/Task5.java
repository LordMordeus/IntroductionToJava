public class Task5 {
    public static void main(String[] args) {

        int [] nums = {8, 4, 5, 8, 10, 22, -2};

        int max = nums[0];

        int min = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if ( nums[i] > max ){
                max = nums[i];
            }
            if ( nums[i] < min ){
                min = nums[i];
            }
    }
        System.out.println("Максимальный элемент в массиве равен " + max);
        System.out.println("Минимальный элемент в массиве равен " + min);
    }
}
