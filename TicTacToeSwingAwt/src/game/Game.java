package game;

import javax.swing.JOptionPane;

public class Game {

	private GameBoard board;							    //ссылка на игровое поле
	private GamePlayer[] gamePlayers = new GamePlayer[2];   // массив игроков
	private int playersTurn = 0;						    // индекс текущего игрока // отвечает за то, чей сейчас ход
	
	public Game() {
		this.board = new GameBoard(this); 					// передаем в него текущий экземпляр нашей игры "this"
	}
	
	public void initGame() {
		gamePlayers[0] = new GamePlayer(true, 'X');  		// реальный игрок ходит Х
		gamePlayers[1] = new GamePlayer(false, 'O'); 		// ИИ ходит О

	}
	
	/*
	 * Метод передачи хода
	 */
	void passTurn() {				
		if(playersTurn == 0)		// если сейчас ход реального игрока, то мы передаем его компьютеру
			playersTurn = 1;
		else
			playersTurn = 0;		// иначе комп передает ход реальному игроку
	}
	
	/*
	 * Получение объекта текущего игрока
	 * @return GamePlayer объект игрока
	 * Когда мы говорим нашей программе: дай объект текущего игрока
	 * Мы смотрим в массив gamePlayers и берем от туда объект с индексом
	 * который мы можем менять с помощью метода passTurn
	 */

	GamePlayer getCurrentPlayer() { return gamePlayers[playersTurn]; }
	
	/*
	 * Метод показа popup-a для пользователя
	 * @param messageText - текст сообщения
	 * Сообщение для пользователя
	 * 
	 */
	
	public void showMessage(String messageText) {			// модификатор исправлен на public, тк вызов board.getGame().showMessage("Ничья!"); не работал
		JOptionPane.showMessageDialog(board, messageText);  // board - привязывает к нашему игровому полю
	}
}
