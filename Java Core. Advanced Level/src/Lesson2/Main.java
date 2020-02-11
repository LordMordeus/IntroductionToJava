package Lesson2;

public class Main {

    public static void main(String[] args) {

        String[][] arr = new String[][]{{"5", "5", "3", "1",}, {"23", "11", "6", "13"}, {"31", "22", "3", "12"}, {"21", "6", "13", "28"}};
        try {
            try {
                int sumResult = arraySum(arr);
                System.out.println(sumResult);
            } catch (MyArraySizeException e) {
                System.out.println("Array size exceeded!");
            }
        }
        catch (MyArrayDataException e) {
            System.out.println("Invalid array value!");
            System.out.println("Cell error: " + e.i + "x" + e.j);
        }

    }


    public static int arraySum(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int count = 0;
        if (arr.length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException();
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    count += Integer.parseInt(arr[i][j]);
                }
                catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }

        }
        return count;
    }

}
