package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {

	private int row; 			// номер ряда
	private int cell; 			// номер в столбце
	private GameButton button;  // ссылка на кнопку
	
	public GameActionListener(int row, int cell, GameButton gButton) {  // GameButton gButton - ссылка на кнопку, к которому мы привязываем наш GameActionListener
		this.row = row;
		this.cell = cell;
		this.button = gButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GameBoard board = button.getBoard(); // через button мы можем получить ссылку на GameBoard
											 // это нужно, т.к. мы должны проверять, можно ли пойти в данную клетку текущему игроку
		if(board.isTurnable(row, cell)) {
			updateByPlayersData(board); // ход человека
			
			if(board.isFull()) { //проверяем, не закончились ли места на нашем поле
				board.getGame().showMessage("Ничья!");
				board.emptyField(); // чистим поле
			} else {
				if (!board.getGame().getCurrentPlayer().isRealPlayer()) {		// исправлена проблема с тем, что после окончания игры(победа/проигрыш/ничья) уже был ход компа
				updateByAiData(board);
				}
			}
		} else {
			board.getGame().showMessage("Некорректный ход!");
		}
	}
	
	/*
	 * Ход человека
	 * @param board GameBoard - ссылка на игровое поле
	 */
	private void updateByPlayersData(GameBoard board) {
		//обновить матрицу игры
		board.updateGameField(row, cell);
		
		//обновить содержимое кнопки
		button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign())); // Character - класс обертка для класса char // получаем символ текущего игрока(char значение)
	
		//проверяем состояние победы
		if(board.cheakWin()) {
			button.getBoard().getGame().showMessage("Вы выиграли!");
			board.emptyField(); 											//читстим поле
		} else { 															// иначе передаем ход
			board.getGame().passTurn();
		}
	}
	
	/*
	 * Ход компьютера
	 * @param board GameBoard - ссылка на игровое поле
	 */
	private void updateByAiData(GameBoard board) {
		//генерация координат хода компьютера
	//	int x; 
	//	int y;
		Random rnd = new Random();
		
		//------------------------------------------------------------------------------------------------------------------------------
		/*
		 * Тут у нас умный комп, реализация с подсчётом очков(мы перебераем все клетки, фиксируем для них количество очков, но запоминаем те x y, для которых самое большое кол-во очков)
		 */
		int maxScoreFieldX = -1;	// координаты X = -1 т.к. они заведомо ложные 
		int maxScoreFieldY = -1;	// координаты Y = -1 т.к. они заведомо ложные 
		int maxScore = 0; 			// максимальное количество очков для нашего поля
		
		for(int i = 0; i < board.DIMENSION; i++) {
			for(int j = 0; j < board.DIMENSION; j++) {
			int fieldScore = 0;		// текущее значение очков
			
			if(board.getGameField(i, j) == board.nullSymbol) {
				//проверяем направления

				// лево верх
				if(i - 1 >= 0 && j - 1 >= 0 && board.getGameField(i-1, j-1) == board.getGame().getCurrentPlayer().getPlayerSign()) {
					fieldScore++;
				}
			
				// верх
				if (i - 1 >= 0 && board.getGameField(i-1,j) == 'O') {
					fieldScore++;
				}
				
				// право верх
				if (i - 1 >= 0 && j + 1 < board.DIMENSION && board.getGameField(i - 1,j + 1) == board.getGame().getCurrentPlayer().getPlayerSign()) {
					fieldScore++;
				}
				
				//право
				if (j + 1 < board.DIMENSION && board.getGameField(i,j + 1) == 'O') {
					fieldScore++;
				}
				
				//право низ
				if (i + 1 < board.DIMENSION && j + 1 < board.DIMENSION && board.getGameField(i + 1,j + 1) == board.getGame().getCurrentPlayer().getPlayerSign()) {
					fieldScore++;
				}
				
				//низ
				if (i + 1 < board.DIMENSION && board.getGameField(i + 1,j) == board.getGame().getCurrentPlayer().getPlayerSign()) {
					fieldScore++;
				}
				
				//Лево низ
				if (j - 1 >= 0 && board.getGameField(i, j - 1) == board.getGame().getCurrentPlayer().getPlayerSign()) {
					fieldScore++;
				}
				
				// Лево
				if (j - 1 >= 0 && board.getGameField(i,j - 1) == board.getGame().getCurrentPlayer().getPlayerSign()) {
					fieldScore++;
				}
			}
			
			if (fieldScore > maxScore) {	// если мы нашли max значение больше текущего, то записываем координаты мах
				maxScore = fieldScore;
				maxScoreFieldX = j;
				maxScoreFieldY = i;
			}
		}
	}
		
	//если в цикле найдена наилучшая клетка, то обновляем координаты x и y
	//	if(maxScoreFieldX != -1) {
	//		x = maxScoreFieldX;
	//		y = maxScoreFieldY;
	//	}
	
	// если нечего не нашли, то делаем глупый ход
	if(maxScoreFieldX == -1) {
		//------------------------------------------------
		do {
			maxScoreFieldX = rnd.nextInt(GameBoard.DIMENSION);
			maxScoreFieldY = rnd.nextInt(GameBoard.DIMENSION);
		} while (!board.isTurnable(maxScoreFieldX, maxScoreFieldY));
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
		//обновить матрицу игры
		board.updateGameField(maxScoreFieldX, maxScoreFieldY);
		
		//обновить содежимое кнопки
		int cellIndex = GameBoard.DIMENSION * maxScoreFieldX + maxScoreFieldY; // размерность нешего поля * номер ряда + номер столбца(обратная формула к предыдущим двум)
		board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign())); //получаем нашу кнопку по её индексу(cellIndex) и присваиваем её тексту значение символа текущего игрока(компа)
		
		//проверить победу
		if(board.cheakWin()) {
			button.getBoard().getGame().showMessage("Компьютер выиграл!Эра машин пришла!");
			board.emptyField();   // добавили чистку поля
		} else {
			//передать ход
			board.getGame().passTurn();
		}
	}
}
