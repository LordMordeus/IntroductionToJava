package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ================================================================================================================== //
/** Класс GameBoard, расширяющий класс JFrame. Определяет рабочую область игры и её поведение.
 *  Уровень доступа:
 *      - доступность самого класса, конструктора и метода getButton: доступно по всему проекту;
 *      - для основных переменных и методов проверки строк/диагоналей: доступно только внутри класса;
 *      - для "геттеров"/"сеттеров" и остальных методов: доступно внутри пакета. */
public class GameBoard extends JFrame {
// ================================================================================================================== //
    /* Блок основных переменных. */
    static int dimension = 3; // размерность поля
    static int cellSize = 150; // размер клетки игрового поля
    private char[][] gameField; // матрица игры
    private GameButton[] gameButtons; // массив кнопок
    static char nullSymbol = '\u0000'; // null-символ
    private Game game; // ссылка на игру
// ================================================================================================================== //
    /* Блок конструкторов класса. */
// ------------------------------------------------------------------------------------------------------------------ //
    /** Описание: Конструктор для создания игрового поля.
     *  Входящие данные:
     *      @param currentGame - ссылка на текущую игру.
     */
    GameBoard(Game currentGame) {
        this.game = currentGame;
        initField();
    }
// ================================================================================================================== //
    /* Блок методов класса. */
// ------------------------------------------------------------------------------------------------------------------ //
    /** Описание: Метод инициализации и отрисовки игрового поля.
     *  Содержит вложенный метод, переопределяющий метод actionPerformed.
     */
    private void initField() {
        // задаём основные настройки окна игры
        setBounds(cellSize * dimension, cellSize * dimension, 400, 300);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        JPanel controlPanel = new JPanel(); // панель управления игрой
        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                emptyField();
            }
        });

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize * dimension, 150);

        JPanel gameFieldPanel = new JPanel(); // панель самой игры
        gameFieldPanel.setLayout(new GridLayout(dimension, dimension));
        gameFieldPanel.setSize(cellSize * dimension, cellSize * dimension);

        gameField = new char[dimension][dimension];
        gameButtons = new GameButton[dimension * dimension];

        for(int i = 0; i < (dimension * dimension); i++) { // инициализируем игровое поле
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameButtons[i] = fieldButton;
        }

        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);
        setVisible(true);
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Метод очистки поля и матрицы игры
     *  Примечание: исправлено изначальное объявление переменных x и y в теле цикла.
     */
    void emptyField() {
        int x, y; // объявление переменных для координат ячейки поля
        for(int i = 0; i < (dimension * dimension); i++) {
            gameButtons[i].setText("");

            /* Первоначальная реализация
             * int x = i / GameBoard.dimension;
             * int y = i % GameBoard.dimension; */

            x = i / GameBoard.dimension; // вычисление координаты по горизонтали
            y = i % GameBoard.dimension; // вычисление координаты по вертикали

            gameField[x][y] = nullSymbol; // "обнуление" ячейки
        }
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Метод для получения ссылки на текущую игру.
     *  Исходящие данные:
     *      @return Game - ссылка на текущую игру.
     */
    Game getGame() {
        return game;
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Метод для получения ячейки игрового поля.
     *  Входящие данные:
     *      @param x - номер координаты по горизонтали.
     *      @param y - номер координаты по вертикали.
     *  Исходящие данные:
     *      @return char[][] - ячейка игрового поля.
     */
    char getGameFieldCell(int x, int y) {
        return gameField[x][y];
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Метод проверки доступности клетки для хода.
     *  Входящие данные:
     *      @param x - координата по горизонтали.
     *      @param y - координата по вертикали.
     *  Исходящие данные:
     *      @return boolean - результат проверки.
     */
    boolean isTurnable(int x, int y) {
        boolean result = false;
        if(gameField[x][y] == nullSymbol) {
            result = true;
        }
        return result;
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Обновление матрицы игры после хода.
     *  Входящие данные:
     *      @param x - координата по горизонтали.
     *      @param y - координата по вертикали.
     */
    void updateGameField(int x, int y) {
        gameField[x][y] = game.getCurrentPlayer().getPlayerSign();
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Метод проверки победы по линиям и столбцам.
     *  Исходящие данные:
     *      @return boolean - флаг победы.
     */
    boolean checkWin() {
        boolean result = false;
        char playerSymbol = getGame().getCurrentPlayer().getPlayerSign();
        if(checkWinDiagonals(playerSymbol) || checkWinLines(playerSymbol)) {
            result = true;
        }
        return result;
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Метод проверки заполненности поля.
     *  Исходящие данные:
     *      @return boolean - флаг заполненности.
     */
    boolean isFull() {
        boolean result = true;
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                if(gameField[i][j] == nullSymbol) {
                    result = false;
                }
            }
        }
        return result;
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Проверка победы по столбцам и линиям.
     *      Входящие данные:
     *          @param playerSymbol - символ текущего игрока.
     *  Исходящие данные:
     *      @return boolean - флаг победы.
     */
    private boolean checkWinLines(char playerSymbol) {
        boolean cols, rows, result;
        result = false;

        for(int col = 0; col < dimension; col++) {
            cols = true;
            rows = true;

            for(int row = 0; row < dimension; row++) {
                cols &= (gameField[col][row] == playerSymbol);
                rows &= (gameField[row][col] == playerSymbol);
            }

            /* Это условие после каждой проверки колонки и столбца
             * позволяет остановить дальнейшее выполнение, без проверки
             * всех остальных столбцов и строк. */
            if(cols || rows) {
                result = true;
                break;
            }

            if(result) {
                break;
            }
        }
        return result;
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Проверка победы по диагоналям.
     *  Входящие данные:
     *      @param playerSymbol - символ текущего игрока.
     *  Исходящие данные:
     *      @return boolean - флаг победы. */
    private boolean checkWinDiagonals(char playerSymbol){
        boolean leftRight, rightLeft, result;

        leftRight = true;
        rightLeft = true;
        result = false;

        for(int i = 0; i < dimension; i++){
            leftRight &= (gameField[i][i] == playerSymbol); //оптимизация кода, чтобы не было через if/else
            rightLeft &= (gameField[dimension - i - 1][i] == playerSymbol);
        }

        if(leftRight || rightLeft){
            result = true;
        }

        return result;
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Метод для получения ссылки на внутриигровую кнопку.
     *  Входящие данные:
     *      @param buttonIndex - индекс запрашиваемой кнопки.
     *  Исходящие данные:
     *      @return GameButton - объект запрашиваемой кнопки. */
    public GameButton getButton(int buttonIndex) {
        return gameButtons[buttonIndex];
    }
}
// ================================================================================================================== //
