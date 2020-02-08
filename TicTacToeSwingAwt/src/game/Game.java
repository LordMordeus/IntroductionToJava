package game;

import javax.swing.JOptionPane;

public class Game {

	private GameBoard board;							    //������ �� ������� ����
	private GamePlayer[] gamePlayers = new GamePlayer[2];   // ������ �������
	private int playersTurn = 0;						    // ������ �������� ������ // �������� �� ��, ��� ������ ���
	
	public Game() {
		this.board = new GameBoard(this); 					// �������� � ���� ������� ��������� ����� ���� "this"
	}
	
	public void initGame() {
		gamePlayers[0] = new GamePlayer(true, 'X');  		// �������� ����� ����� �
		gamePlayers[1] = new GamePlayer(false, 'O'); 		// �� ����� �

	}
	
	/*
	 * ����� �������� ����
	 */
	void passTurn() {				
		if(playersTurn == 0)		// ���� ������ ��� ��������� ������, �� �� �������� ��� ����������
			playersTurn = 1;
		else
			playersTurn = 0;		// ����� ���� �������� ��� ��������� ������
	}
	
	/*
	 * ��������� ������� �������� ������
	 * @return GamePlayer ������ ������
	 * ����� �� ������� ����� ���������: ��� ������ �������� ������
	 * �� ������� � ������ gamePlayers � ����� �� ���� ������ � ��������
	 * ������� �� ����� ������ � ������� ������ passTurn
	 */

	GamePlayer getCurrentPlayer() { return gamePlayers[playersTurn]; }
	
	/*
	 * ����� ������ popup-a ��� ������������
	 * @param messageText - ����� ���������
	 * ��������� ��� ������������
	 * 
	 */
	
	public void showMessage(String messageText) {			// ����������� ��������� �� public, �� ����� board.getGame().showMessage("�����!"); �� �������
		JOptionPane.showMessageDialog(board, messageText);  // board - ����������� � ������ �������� ����
	}
}
