public class Task3 {
public static void main(String[]args){
    int[] nums = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };

    for (int i = 0; i < nums.length; i++) {
        nums[i] = nums[i] < 6 ? nums[i] *2 : nums[i];
        System.out.print(nums[i] + " ");
    }
}
}
