package game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GameBoard extends JFrame{
	
	public static final int DIMENSION = 3;			// �����������
	public static final int CELLSIZE = 150;			// ������ ����� ������
	private char[][] gameField;			// ������� ����		//������ �� � private �� static, ����� ������ � � GameActionListener
	private GameButton[] gameButtons;	// ������ ������
	
	private Game game;					// ������ �� ����
	
	static char nullSymbol = '\u0000';	// null ������

	public GameBoard(Game currentGame) {
		this.game = currentGame;
		initField(); // ������������� ����
	}
	
	/*
	 *  ����� ������������� � ��������� �������� ����
	 */
	
	private void initField() {
		
		// ������ �������� ��������� �������� ����
		setBounds(CELLSIZE * DIMENSION, CELLSIZE * DIMENSION, 400, 300); // cellSize - ������ ������; dimension - ����������� ����
		setTitle("��������-������");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		JPanel controlPanel = new JPanel(); //������ ���������� �����
		
		// ������ "����� ����" � � ����������
		JButton newGameButton = new JButton("����� ����"); 
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				emptyField();  	// ����� ������� ������ ���� � ������� ����
			}
		});
		
		// ������ �������� ���������� ����� ����������� ������
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		// ��������� ���� ������
		controlPanel.add(newGameButton);
		// ��������� �������
		controlPanel.setSize(CELLSIZE * DIMENSION, 150);
		
		//������ ����� ����
		JPanel gameFieldPanel = new JPanel();
		gameFieldPanel.setLayout(new GridLayout(DIMENSION, DIMENSION)); 	//GridLayout - ��� ��� ������������� ��������� ���������� ����� ������ �� ������ ����������
		// ������ ������
		gameFieldPanel.setSize(CELLSIZE * DIMENSION, CELLSIZE * DIMENSION);
		
		//������� ����� ����(��������� ������)
		gameField = new char[DIMENSION][DIMENSION];
		//������ ������ ����� ����
		gameButtons = new GameButton[DIMENSION * DIMENSION];
		
		// �������������� ������� ����
		for (int i = 0; i < (DIMENSION * DIMENSION); i++) {     // ������� ��� ���� ���
			GameButton fieldButton = new GameButton(i, this);	// ������� ����� ���������� ������ GameButton, �������� ������ ������ � �������� ���� ������� ����(this)
			gameFieldPanel.add(fieldButton);				    // ��������� ������ �� ������� ����
			gameButtons[i] = fieldButton; 						//  � ������ gameButtons ��� �������� i ����� ��������� ���������� � ���������� ������ ������
		}
		
		
		// ������ ��� ���������� ������ ����� �������� �� �������� ����
		 getContentPane().add(controlPanel, BorderLayout.NORTH);
		 getContentPane().add(gameFieldPanel, BorderLayout.CENTER);
		 
		 //������ ���� �������
		 setVisible(true);
	}	 
		 /*
		  * ����� ������� ���� � ������� ����
		  */
		 void emptyField() {
			 for(int i = 0; i < (DIMENSION * DIMENSION); i++) {
				 gameButtons[i].setText("");
				 
				 int x = i / GameBoard.DIMENSION;   // �������� ��������
				 int y = i % GameBoard.DIMENSION;	// ������ ����� ������
				 									
				 gameField[x][y] = nullSymbol;		// ����������� ������ ������ ������ ��� ������� ����
			 }
		 
		 }
		 
		 
		 Game getGame() {
			 return game;
		 }
		 
		 /*
		  * ����� �������� ����������� ������ ��� ����
		  * @param x - �� �����������
		  * @param � - �� ���������
		  * @return boolean
		  */
		 
		 boolean isTurnable(int x, int y) { 
			 boolean result = false;	       // ������ ����� � ������
			 
			 if(gameField[y][x] == nullSymbol) // ������� ���� ������� ����, ���� ���� �� ������ ����������� �������� ������ � ������ ��������, �� 
				 result = true;				   // � ���� ������ ����� �����(true)
			 
			 
				 return result;
		 }
		 
		 /*
		  * ���������� ������� ���� ����� ����
		  * @param x - �� �����������
		  * @param � - �� ��������� 
		  */
		 void updateGameField(int x, int y) { // �� ������� 2 ����������, �� ������ ���������� � ���� ������ ������ ������
			 gameField[y][x] = game.getCurrentPlayer().getPlayerSign(); //� ��� ���� ������ �� ������ ���� game, � �� �� ����� ������, ��� �� ������ ������� ����� getCurrentPlayer, �� �������� ������ �� ������, �� ������ ��������� ��� ������, � �� ��� ������ -> getPlayerSign
		 }
		 
		 /*
		  * �������� ������ �� ������� � ������
		  * @return ���� ������
		  */
		 boolean cheakWin() {
			 boolean result = false;
			 
			 char playerSymbol = getGame().getCurrentPlayer().getPlayerSign();
			 
			 if (cheakWinDiagonals(playerSymbol) || cheakWinLines(playerSymbol)) {
				 result = true;
			 }
			 
			 return result;
		 }
		 
		 /*
		  * getter
		  */
		 
		 public char getGameField(int x, int y) {
			 return gameField[x][y];
		 }
		 /*
		  * ����� �������� ������������� ����, ���� ���������� -> �����
		  * @return boolean
		  */
		 boolean isFull() {
			 boolean result = true;
			 
			 for (int i = 0; i < DIMENSION; i++) {
				 for (int j = 0; j < DIMENSION; j++) {
					 if(gameField[i][j] == nullSymbol) //������� ������� ������ ������
						 result = false;
				 }
			 }
			
			 return result;
		 }
		 
		 
		 /*
		  * �������� ������ �� �������� � ������
		  * @return ���� ������
		  */
		 private boolean cheakWinLines(char playerSymbol) {
			 boolean cols, rows, result;
			 
			 result = false;
			 
			 for (int col = 0; col < DIMENSION; col++) {
				 cols = true;
				 rows = true;
				 
				 for (int row = 0; row < DIMENSION; row++) {
					 cols &= (gameField[col][row] == playerSymbol);
					 rows &= (gameField[row][col] == playerSymbol);
				 }
				 
				 //��� ������� ����� ������ �������� ������� � �������
				 // ��������� ���������� ���������� ����������, ��� ��������
				 // ���� ��������� �������� � �����
				 if (cols || rows) {
					 result = true;
					 break;
				 }
				 if(result) {
					 break;
				 }
			 }
			 
			 return result;
		 }
		 
		 /*
		  * �������� ���������� ���������� �� ����������
		  * @return ���� ������
		  */
		 private boolean cheakWinDiagonals(char playerSymbol) {
			 boolean leftRight, rightLeft, result;

			    leftRight = true;
			    rightLeft = true;
			    result = false;

			    for(int i = 0; i < DIMENSION; i++){
			        leftRight &= (gameField[i][i] == playerSymbol); //����������� ����, ����� �� ���� ����� if/else
			        rightLeft &= (gameField[DIMENSION - i - 1][i] == playerSymbol);
			    }

			    if(leftRight || rightLeft){
			        result = true;
			    }

			    return result;
			}
		 
		 public GameButton getButton(int buttonIndex) { 	//buttonIndex - ����� ������
			 return gameButtons[buttonIndex];				//@return ������ �� �� �� ������� gameButtons
		 }
}
