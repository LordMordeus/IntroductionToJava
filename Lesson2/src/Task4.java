public class Task4 {
    public static void main(String[] args) {

        final int count = 10;

        int[][] nums = new int[count][count];

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                nums[i][j] =  (i == j || i == count - j - 1) ? 1 : 0;
            }

            }
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }

    }

}


