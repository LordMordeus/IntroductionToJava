package game;

import javax.swing.*;

// ================================================================================================================== //
/** Класс GameButton, расширяющий класс JButton. Определяет кнопки в игре и их поведение.
 *  Уровень доступа:
 *      - доступность самого класса, конструктора и метода getBoard: доступно по всему проекту;
 *      - для основных переменных: доступно только внутри класса. */
public class GameButton extends JButton {
// ================================================================================================================== //
    /* Блок основных переменных. */
    private int buttonIndex;
    private GameBoard board;
// ================================================================================================================== //
    /* Блок конструкторов класса. */
// ------------------------------------------------------------------------------------------------------------------ //
    /** Описание: Конструктор для создания внутриигровой кнопки.
     *  Входящие данные:
     *      @param gameButtonIndex - индекс внутриигровой кнопки.
     *      @param currentGameBoard - ссылка на текущую игровую область.
     */
    public GameButton(int gameButtonIndex, GameBoard currentGameBoard) {
        buttonIndex = gameButtonIndex;
        board = currentGameBoard;
        int rowNum = buttonIndex / GameBoard.dimension;
        int cellNum = buttonIndex % GameBoard.dimension;
        setSize(GameBoard.cellSize - 5, GameBoard.cellSize - 5);
        addActionListener(new GameActionListener(rowNum, cellNum, this));
    }
// ================================================================================================================== //
    /* Блок методов класса. */
// ------------------------------------------------------------------------------------------------------------------ //
    /** Описание: Метод для получения игровой области.
     *  Исходящие данные:
     *      @return getBoard GameBoard - текущая игровая область.
     */
    public GameBoard getBoard() {
        return board;
    }
}
// ================================================================================================================== //
