package game;

public class GamePlayer {

	private char playerSign; 									//символ, которым играет игрок
	private boolean realPlayer = true; 							// признак того, что это реальный игрок или комп
	
	public GamePlayer(boolean isRealPlayer, char playerSign) {  // указываем наши переменные через конструктор
		this.realPlayer = isRealPlayer;
		this.playerSign = playerSign;
		
	}
	/*
	 * Создаем 2 getters, чтобы иметь доступ к приватным полям из вне
	 */
	public boolean isRealPlayer() { return realPlayer; }
	
	public char getPlayerSign() {
		return playerSign;
	}

}
