public class Task1 {
    public static void main(String[] args) {

        int[] nums = { 0, 0, 1, 1, 0, 1, 0, 1 };

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] == 1 ? 0 : 1;
            System.out.print(nums[i] + " ");
        }
    }
}
