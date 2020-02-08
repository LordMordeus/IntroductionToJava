package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

// ================================================================================================================== //
/** Класс GameActionListener, расширяющий класс ActionListener. Определяет обработку действий в рамках игры.
 *  Уровень доступа:
 *      - доступность самого класса, конструктора и перегруженного метода actionPerformed: доступно по всему проекту;
 *      - для основных переменных и методов совершения хода игроками: доступно только внутри класса. */
public class GameActionListener implements ActionListener {
// ================================================================================================================== //
    /* Блок основных переменных. */
    private int row; // строка
    private int cell; // столбец
    private GameButton button; // ссылочка на кнопочку
    private final String GAME_STAT = "Статус игры"; // заголовок статусного сообщения
    private final String GAME_WARN = "Внимание!"; // заголовок предупреждающего сообщения
    private boolean SILLY_MODE = false; // тумблер электронного болвана
    private char DOT_X = 'X'; // знак человека, нужен для работы портированных методов
    private char DOT_O = 'O'; // знак компьютера, также нужен для работы портированных методов
    private static Random randy = new Random(); // Вселенский Рандом
    private boolean isGameOver = false; // флаг, сообщающий об окончании игры
// ================================================================================================================== //
    /* Блок конструкторов класса. */
// ------------------------------------------------------------------------------------------------------------------ //
    /** Описание: Конструктор для обработчика действий в игре.
     *  Входящие данные:
     *      @param row - строка.
     *      @param cell - столбец.
     *      @param gButton - ссылка на обрабатываемую кнопку.
     */
    public GameActionListener(int row, int cell, GameButton gButton) {
        this.row = row;
        this.cell = cell;
        this.button = gButton;
    }
// ================================================================================================================== //
    /* Блок методов класса. */
// ------------------------------------------------------------------------------------------------------------------ //
    /** Описание: Переопределённый метод из ActionListener для обработки внутриигровых событий.
     *  Входящие данные:
     *      @param event ActionEvent - текущее событие.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        GameBoard board = button.getBoard();
        isGameOver = false; // пока игра активна (нужно для разделения туров игры)
        if(board.isTurnable(row, cell)) {
            updateByPlayersData(board); // обработка действий игрока-человека
            if(board.isFull()) {
                board.getGame().showMessage("Ничья!", GAME_STAT, JOptionPane.INFORMATION_MESSAGE);
                board.emptyField();
                board.getGame().setHumanityFirst(); // установка начального положения хода в следующем туре
            } else { // так как даже после победы человека этот код всё равно достигается
                if(!isGameOver) { // принято решение ввести флаг окончания игры
                    updateByAIData(board); // чтобы запускать ход компьютера только при активной игре
                }
            }
        } else {
            board.getGame().showMessage("Некорректный ход!", GAME_WARN, JOptionPane.WARNING_MESSAGE);
        }
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Описание: Ход человека.
     *  Входящие данные:
     *      @param board GameBoard - ссылка на игровое поле.
     */
    private void updateByPlayersData(GameBoard board) {
        board.updateGameField(row, cell); // обновление матрицы игры
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign())); // обновление содержимого кнопки
        if(board.checkWin()) {
            button.getBoard().getGame().showMessage("Кожаный мешок с мясом выиграл!", GAME_STAT, JOptionPane.INFORMATION_MESSAGE);
            isGameOver = true; // флаг окончания текущего тура игры
            board.emptyField();
            board.getGame().setHumanityFirst(); // установка начального положения хода в следующем туре
        } else {
            board.getGame().passTurn();
        }
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Описание: Ход компьютера. Дополнено алгоритмом из HomeworkThree.
     *  Входящие данные:
     *      @param board GameBoard - ссылка на игровое поле.
     */
    private void updateByAIData(GameBoard board) {

        // Первоначальная реализация выбора ячейки компьютером
        /*Random rnd = new Random();
        do { // генерация значений координат для хода компьютера
            x = rnd.nextInt(GameBoard.dimension);
            y = rnd.nextInt(GameBoard.dimension);
        } while(!board.isTurnable(x, y));*/

        int x = -1;
        int y = -1; // объявление координат для хода компьютера
        int[] xyIsxy; // слепленный кусок координат
        if(SILLY_MODE) { // включение дурачка с порога
            xyIsxy = RandyCoordinates(board);
            x = xyIsxy[0];
            y = xyIsxy[1];
        } else { // попытка посмотреть вокруг
            for(int i = 0; i < GameBoard.dimension; i++) {
                for (int j = 0; j < GameBoard.dimension; j++) {
                    if(board.isTurnable(i, j)) {
                        if(WinPathChecker(board, DOT_O)) { // подвернувшаяся выигрышная комбинация для компьютера
                            x = i;
                            y = j;
                        } else if(WinPathChecker(board, DOT_X)) { // блокировка выигрышной комбинации человека
                            x = i;
                            y = j;
                        } else { // включение дурачка, если ничего не помогло
                            xyIsxy = RandyCoordinates(board);
                            x = xyIsxy[0];
                            y = xyIsxy[1];
                        }
                    }
                }
            }
        }
        board.updateGameField(x, y); // обновление матрицы игры
        int cellIndex = GameBoard.dimension * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
        if(board.checkWin()) { // проверка победы
            button.getBoard().getGame().showMessage("Компьютер выиграл!", GAME_STAT, JOptionPane.INFORMATION_MESSAGE);
            board.emptyField();
            board.getGame().setHumanityFirst(); // установка начального положения хода в следующем туре
        } else {
            board.getGame().passTurn();
        }
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Описание: Метод для генерации псевдослучайных координат в ходе компьютера.
     *  Входящие данные:
     *      @param board - ссылка на текущее игровое поле.
     *  Исходящие данные:
     *      @return int[] - пара сгенерированных координат.
     *  Примечание: Портирован из HomeworkThree.
     */
    private static int[] RandyCoordinates(GameBoard board) {
        int x, y;
        int[] result = new int[2];
        do {
            x = randy.nextInt(GameBoard.dimension); // генерация псевдослучайного значения для горизонтали
            y = randy.nextInt(GameBoard.dimension); // генерация псевдослучайного значения для вертикали
        } while (!board.isTurnable(x, y));
        result[0] = x;
        result[1] = y;
        return result;
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Описание: Метод для оценки привлекательности хода.
     *  Входящие данные:
     *      @param board - ссылка на текущее игровое поле.
     *      @param playerDOT - знак анализируемого игрока.
     *  Исходящие данные:
     *      @return boolean - признак привлекательности хода по линии.
     *  Примечание: Портирован из HomeworkThree.
     */
    private static boolean WinPathChecker(GameBoard board, char playerDOT) {
        for(int i = 0; i < GameBoard.dimension; i++) {
            if (LineChecker(board, i, 0, 0, 1, playerDOT)) { // проверка строк
                return true;
            }
            if (LineChecker(board, 0, i, 1, 0, playerDOT)) { // проверка столбцов
                return true;
            }
        }
        if(LineChecker(board, 0,0,1,1, playerDOT)) { // проверка одной диагонали
            return true;
        }
        if(LineChecker(board, 0,GameBoard.dimension - 1,1,-1, playerDOT)) { // проверка другой диагонали
            return true;
        }
        return false;
    }
// ------------------------------------------------------------------------------------------------------------------ //
    /** Описание: Метод оценки привлекательности линии для хода.
     *  Входящие данные:
     *      @param board - ссылка на текущее игровое поле.
     *      @param startX - начальная позиция по горизонтали.
     *      @param startY - начальная позиция по вертикали.
     *      @param deltaX - смещение по горизонтали.
     *      @param deltaY - смещение по вертикали.
     *      @param playerDOT - знак анализируемого игрока.
     *  Исходящие данные:
     *      @return boolean - признак привлекательности линии.
     *  Примечание: Портирован из HomeworkThree.
     */
    private static boolean LineChecker(GameBoard board, int startX, int startY, int deltaX, int deltaY, char playerDOT) {
        int x, y;
        for(int i = 0; i < (GameBoard.dimension * GameBoard.dimension); i++) {

            x = i / GameBoard.dimension; // вычисление координаты по горизонтали
            y = i % GameBoard.dimension; // вычисление координаты по вертикали

            if(board.getGameFieldCell((startX + x * deltaX),(startY + y * deltaY)) != playerDOT) {
                return false;
            }
        }
        return true;
    }
}
// ================================================================================================================== //
