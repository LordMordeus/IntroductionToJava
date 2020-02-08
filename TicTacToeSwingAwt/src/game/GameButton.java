package game;

import javax.swing.*;

public class GameButton extends JButton {

	private int buttonIndex;  // ������ ����� ������
	private GameBoard board;  // ������ �� ������� ����
	
	public GameButton(int gameButtonIndex, GameBoard currentGameBoard) {  //����� �� ������� ���� ������, �� � ������������ ��������� ������, ������� ����� ������������ ��� � ��������, �� � ������ �� ������� ����
		buttonIndex = gameButtonIndex;
		board = currentGameBoard;
		
		int rowNum = buttonIndex / GameBoard.DIMENSION; 				  // ����������� ����� ���� (������������� �������)
		int cellNum = buttonIndex % GameBoard.DIMENSION; 				  // ����������� ����� ������ � ������� (������������� �������)
		
		setSize(GameBoard.CELLSIZE - 5, GameBoard.CELLSIZE - 5);		  // ������ setSize ��� ������ GameBoard, -5 �������� ��� ����, ����� ���� ������ �� �������, � ���� � ����� �����, � ����� � �������� ���� ������, ��� �������� ������ ����
		addActionListener(new GameActionListener(rowNum, cellNum, this)); // �� � ���������� ��� listener, GameActionListener � ������� �������� � ������� � ������ �� ������� ������
	}
	/*
	 * Getter
	 */
	public GameBoard getBoard() { return board; }
}
