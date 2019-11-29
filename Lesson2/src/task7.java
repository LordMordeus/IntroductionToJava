import java.util.Arrays;

public class task7 {
    public static void main(String[] args) {
        int [] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int shiftCount = -25 ;
        shiftOfNumbers(numbers, shiftCount);
        System.out.println(Arrays.toString(numbers));
    }
    private static void shiftOfNumbers(int[] arrayNumbers, int n){

        int stepsCount;
        if (n > arrayNumbers.length){
            stepsCount = Math.abs(n % arrayNumbers.length);
        }
        else {
            stepsCount = n;
        }

        if (stepsCount > 0) {
            for (int i = 0; i < stepsCount; i++) {
                int buffer = arrayNumbers[0];
                arrayNumbers[0] = arrayNumbers[arrayNumbers.length - 1];

                for (int j = 1; j < arrayNumbers.length - 1; j++) {
                    arrayNumbers[arrayNumbers.length - j] = arrayNumbers[arrayNumbers.length - j - 1];
                }

                arrayNumbers[1] = buffer;
            }
        }
        else if (stepsCount < 0) {
            for (int i = 0; i > stepsCount; i--) {
                int buffer = arrayNumbers[arrayNumbers.length - 1];
                arrayNumbers[arrayNumbers.length - 1] = arrayNumbers[0];

                for (int j = 1; j < arrayNumbers.length - 1; j++) {
                    arrayNumbers[j - 1] = arrayNumbers[j];
                }

                arrayNumbers[arrayNumbers.length - 2] = buffer;
            }
        }
    }
}

