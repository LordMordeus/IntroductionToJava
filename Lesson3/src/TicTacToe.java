import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    private static int size = 3;
    private static char[][] map = new char[size][size];
    private static final char DotX = 'X';
    private static final char Dot_0 = 'O';
    private static final char DotEmpty = '•';
    private static Scanner scanner = new Scanner(System.in);
    private static boolean sillyMode = false;

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            if (isEndGame(DotX)) {
                break;
            }
            computerTurn();
            if (isEndGame(Dot_0)) {
                break;
            }

        }
    }

    private static void initMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = DotEmpty;
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }


    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты ячейки (X Y)");
            x = scanner.nextInt() - 1; // Считывание номера строки
            y = scanner.nextInt() - 1; // Считывание номера столбца
        }
        while (!isCellValid(x, y));

        map[x][y] = DotX;
    }

    private static void computerTurn () {
        int x = -1;
        int y = -1;
        int max = -1;
        if(sillyMode){
            do {
                x = random.nextInt(size);
                y = random.nextInt(size);
            } while(!isCellValid(x, y));
        }
        else{
                int helper[][] = new int[size][size];
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (isCellValid(i, j)) {
                            helper[i][j] = checkWeight(i, j);
                        } else{
                            helper[i][j] = -1;
                        }
                    }
                }
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {

                        if (helper[i][j] > max) {
                            max = helper[i][j];
                            x = i;
                            y = j;
                        }
                    }
                }
        }
        System.out.println("Компьютер выбрал ячейку " + (x + 1) + " " + (y + 1));
        map[x][y] = Dot_0;
    }

    private static int checkWeight (int x , int y) {
        int weight = 0;
        int k, l;
        for (k = x-1; k <= x +1; k++) {
            for (l = y-1; l <= y + 1; l++){
                if (isCellViable(k,l)){
                    if (map [k][l] == Dot_0){
                        weight++;
                    }
                }
            }
        }
        return weight;
    }

    private static boolean isCellValid(int x, int y) {
            boolean result = true;

            if (x < 0 || x >= size || y < 0 || y >= size) {
                result = false;
            }
        if (map[x][y] != DotEmpty) {
            result = false;
        }
            return result;
    }

    private static boolean isCellViable(int x, int y) {
        boolean result = true;

        if (x < 0 || x >= size || y < 0 || y >= size) {
            result = false;
        }
        return result;
    }


    private static boolean isEndGame(char playerSymbol) {
        boolean result = false;
        printMap();
        if (checkWin(playerSymbol)) {
            System.out.println("Победители " + playerSymbol);
            result = true;
        }

        if (isMapFull()) {
            System.out.println("Ничья");
            result = true;
        }
        return result;
    }
    private static boolean checkWin(char playerSymbol) {
        boolean result = false;

        if ((map[0][0] == playerSymbol && map[0][1] == playerSymbol && map[0][2] == playerSymbol) ||
                (map[1][0] == playerSymbol && map[1][1] == playerSymbol && map[1][2] == playerSymbol) ||
                (map[2][0] == playerSymbol && map[2][1] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[0][0] == playerSymbol && map[1][0] == playerSymbol && map[2][0] == playerSymbol) ||
                (map[0][1] == playerSymbol && map[1][1] == playerSymbol && map[2][1] == playerSymbol) ||
                (map[0][2] == playerSymbol && map[1][2] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[0][0] == playerSymbol && map[1][1] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[2][0] == playerSymbol && map[1][1] == playerSymbol && map[0][2] == playerSymbol)) {
            result = true;
        }

        return result;
    }

    private static boolean isMapFull() {
        boolean result = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == DotEmpty) {
                    return false;
                }
            }
        }
        return result;
    }
    private static Random random = new Random();
}