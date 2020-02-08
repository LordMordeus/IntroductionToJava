public class Task6 {
    public static void main(String[] args) {
        int [] nums = {8, 4, 5, 5, 1, 8, 4, 5, 6};
        System.out.println(checkBalance(nums));
    }
    private static boolean checkBalance(int[] nums) {
        int lSum, rSum;

        for (int i = 0; i < nums.length; i++) {
            lSum = 0;
            rSum = 0;

            for (int j = 0; j < i; j++) {
                lSum += nums[j];
            }

            for (int j = i; j < nums.length; j++) {
                rSum += nums[j];
            }

            if (lSum == rSum) return true;
        }
        return false;
    }

}
