package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {

	private int row; 			// ����� ����
	private int cell; 			// ����� � �������
	private GameButton button;  // ������ �� ������
	
	public GameActionListener(int row, int cell, GameButton gButton) {  // GameButton gButton - ������ �� ������, � �������� �� ����������� ��� GameActionListener
		this.row = row;
		this.cell = cell;
		this.button = gButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GameBoard board = button.getBoard(); // ����� button �� ����� �������� ������ �� GameBoard
											 // ��� �����, �.�. �� ������ ���������, ����� �� ����� � ������ ������ �������� ������
		if(board.isTurnable(row, cell)) {
			updateByPlayersData(board); // ��� ��������
			
			if(board.isFull()) { //���������, �� ����������� �� ����� �� ����� ����
				board.getGame().showMessage("�����!");
				board.emptyField(); // ������ ����
			} else {
				if (!board.getGame().getCurrentPlayer().isRealPlayer()) {		// ���������� �������� � ���, ��� ����� ��������� ����(������/��������/�����) ��� ��� ��� �����
				updateByAiData(board);
				}
			}
		} else {
			board.getGame().showMessage("������������ ���!");
		}
	}
	
	/*
	 * ��� ��������
	 * @param board GameBoard - ������ �� ������� ����
	 */
	private void updateByPlayersData(GameBoard board) {
		//�������� ������� ����
		board.updateGameField(row, cell);
		
		//�������� ���������� ������
		button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign())); // Character - ����� ������� ��� ������ char // �������� ������ �������� ������(char ��������)
	
		//��������� ��������� ������
		if(board.cheakWin()) {
			button.getBoard().getGame().showMessage("�� ��������!");
			board.emptyField(); 											//������� ����
		} else { 															// ����� �������� ���
			board.getGame().passTurn();
		}
	}
	
	/*
	 * ��� ����������
	 * @param board GameBoard - ������ �� ������� ����
	 */
	private void updateByAiData(GameBoard board) {
		//��������� ��������� ���� ����������
	//	int x; 
	//	int y;
		Random rnd = new Random();
		
		//------------------------------------------------------------------------------------------------------------------------------
		/*
		 * ��� � ��� ����� ����, ���������� � ��������� �����(�� ���������� ��� ������, ��������� ��� ��� ���������� �����, �� ���������� �� x y, ��� ������� ����� ������� ���-�� �����)
		 */
		int maxScoreFieldX = -1;	// ���������� X = -1 �.�. ��� �������� ������ 
		int maxScoreFieldY = -1;	// ���������� Y = -1 �.�. ��� �������� ������ 
		int maxScore = 0; 			// ������������ ���������� ����� ��� ������ ����
		
		for(int i = 0; i < board.DIMENSION; i++) {
			for(int j = 0; j < board.DIMENSION; j++) {
			int fieldScore = 0;		// ������� �������� �����
			
			if(board.getGameField(i, j) == board.nullSymbol) {
				//��������� �����������

				// ���� ����
				if(i - 1 >= 0 && j - 1 >= 0 && board.getGameField(i-1, j-1) == board.getGame().getCurrentPlayer().getPlayerSign()) {
					fieldScore++;
				}
			
				// ����
				if (i - 1 >= 0 && board.getGameField(i-1,j) == 'O') {
					fieldScore++;
				}
				
				// ����� ����
				if (i - 1 >= 0 && j + 1 < board.DIMENSION && board.getGameField(i - 1,j + 1) == board.getGame().getCurrentPlayer().getPlayerSign()) {
					fieldScore++;
				}
				
				//�����
				if (j + 1 < board.DIMENSION && board.getGameField(i,j + 1) == 'O') {
					fieldScore++;
				}
				
				//����� ���
				if (i + 1 < board.DIMENSION && j + 1 < board.DIMENSION && board.getGameField(i + 1,j + 1) == board.getGame().getCurrentPlayer().getPlayerSign()) {
					fieldScore++;
				}
				
				//���
				if (i + 1 < board.DIMENSION && board.getGameField(i + 1,j) == board.getGame().getCurrentPlayer().getPlayerSign()) {
					fieldScore++;
				}
				
				//���� ���
				if (j - 1 >= 0 && board.getGameField(i, j - 1) == board.getGame().getCurrentPlayer().getPlayerSign()) {
					fieldScore++;
				}
				
				// ����
				if (j - 1 >= 0 && board.getGameField(i,j - 1) == board.getGame().getCurrentPlayer().getPlayerSign()) {
					fieldScore++;
				}
			}
			
			if (fieldScore > maxScore) {	// ���� �� ����� max �������� ������ ��������, �� ���������� ���������� ���
				maxScore = fieldScore;
				maxScoreFieldX = j;
				maxScoreFieldY = i;
			}
		}
	}
		
	//���� � ����� ������� ��������� ������, �� ��������� ���������� x � y
	//	if(maxScoreFieldX != -1) {
	//		x = maxScoreFieldX;
	//		y = maxScoreFieldY;
	//	}
	
	// ���� ������ �� �����, �� ������ ������ ���
	if(maxScoreFieldX == -1) {
		//------------------------------------------------
		do {
			maxScoreFieldX = rnd.nextInt(GameBoard.DIMENSION);
			maxScoreFieldY = rnd.nextInt(GameBoard.DIMENSION);
		} while (!board.isTurnable(maxScoreFieldX, maxScoreFieldY));
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
		//�������� ������� ����
		board.updateGameField(maxScoreFieldX, maxScoreFieldY);
		
		//�������� ��������� ������
		int cellIndex = GameBoard.DIMENSION * maxScoreFieldX + maxScoreFieldY; // ����������� ������ ���� * ����� ���� + ����� �������(�������� ������� � ���������� ����)
		board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign())); //�������� ���� ������ �� � �������(cellIndex) � ����������� � ������ �������� ������� �������� ������(�����)
		
		//��������� ������
		if(board.cheakWin()) {
			button.getBoard().getGame().showMessage("��������� �������!��� ����� ������!");
			board.emptyField();   // �������� ������ ����
		} else {
			//�������� ���
			board.getGame().passTurn();
		}
	}
}
