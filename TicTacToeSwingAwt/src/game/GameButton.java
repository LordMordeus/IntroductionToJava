package game;

import javax.swing.*;

public class GameButton extends JButton {

	private int buttonIndex;  // индекс нашей кнопки
	private GameBoard board;  // ссылка на игровое поле
	
	public GameButton(int gameButtonIndex, GameBoard currentGameBoard) {  //когда мы создаем нашу кнопку, мы в конструкторе указываем индекс, который будет расчитыватс€ при еЄ создании, ну и ссылка на игровое поле
		buttonIndex = gameButtonIndex;
		board = currentGameBoard;
		
		int rowNum = buttonIndex / GameBoard.DIMENSION; 				  // расчитываем номер р€да (целочисленное деление)
		int cellNum = buttonIndex % GameBoard.DIMENSION; 				  // расчитываем номер €чейки в столбце (целочисленное деление)
		
		setSize(GameBoard.CELLSIZE - 5, GameBoard.CELLSIZE - 5);		  // задаем setSize дл€ нашего GameBoard, -5 отнимаем дл€ того, чтобы наши кнопки не поехали, а были в своей сумме, в р€дах и столбцах чуть меньше, чем сумарна€ ширина пол€
		addActionListener(new GameActionListener(rowNum, cellNum, this)); // ну и навешываем наш listener, GameActionListener в который передаем еЄ индексы и ссылку на текущую кнопку
	}
	/*
	 * Getter
	 */
	public GameBoard getBoard() { return board; }
}
