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
	
	public static final int DIMENSION = 3;			// размерность
	public static final int CELLSIZE = 150;			// размер одной клетки
	private char[][] gameField;			// матрица игры		//меняем на с private на static, чтобы видеть её в GameActionListener
	private GameButton[] gameButtons;	// массив кнопок
	
	private Game game;					// ссылка на игру
	
	static char nullSymbol = '\u0000';	// null символ

	public GameBoard(Game currentGame) {
		this.game = currentGame;
		initField(); // инициализация поля
	}
	
	/*
	 *  Метод инициализации и отрисовки игрового поля
	 */
	
	private void initField() {
		
		// Задаем основные настройки игрового поля
		setBounds(CELLSIZE * DIMENSION, CELLSIZE * DIMENSION, 400, 300); // cellSize - размер клетки; dimension - размерность игры
		setTitle("Крестики-нолики");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		JPanel controlPanel = new JPanel(); //панель управления игрой
		
		// кнопка "Новая игра" и её функционал
		JButton newGameButton = new JButton("Новая игра"); 
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				emptyField();  	// метод очистки нашего поля и матрицы игры
			}
		});
		
		// задаем менеджер размещения нашей контрольной панели
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		// добавляем нашу кнопку
		controlPanel.add(newGameButton);
		// указываем размеры
		controlPanel.setSize(CELLSIZE * DIMENSION, 150);
		
		//Панель самой игры
		JPanel gameFieldPanel = new JPanel();
		gameFieldPanel.setLayout(new GridLayout(DIMENSION, DIMENSION)); 	//GridLayout - так как напрашивается табличное размещение наших кнопок на панели управления
		// размер панель
		gameFieldPanel.setSize(CELLSIZE * DIMENSION, CELLSIZE * DIMENSION);
		
		//матрица нашей игры(двумерный массив)
		gameField = new char[DIMENSION][DIMENSION];
		//массив кнопок нашей игры
		gameButtons = new GameButton[DIMENSION * DIMENSION];
		
		// инициализируем игровое поле
		for (int i = 0; i < (DIMENSION * DIMENSION); i++) {     // обходим его один раз
			GameButton fieldButton = new GameButton(i, this);	// создаем новые экземпляры класса GameButton, указывая индекс кнопки и передаем наше текущее поле(this)
			gameFieldPanel.add(fieldButton);				    // добавляем кнопку на игровое поле
			gameButtons[i] = fieldButton; 						//  в массив gameButtons под индексом i также добавляем полученную в результате работы ссылку
		}
		
		
		// Теперь две полученные модели нужно добавить на основное окно
		 getContentPane().add(controlPanel, BorderLayout.NORTH);
		 getContentPane().add(gameFieldPanel, BorderLayout.CENTER);
		 
		 //делаем окно видимым
		 setVisible(true);
	}	 
		 /*
		  * Метод очистки поля и матрицы игры
		  */
		 void emptyField() {
			 for(int i = 0; i < (DIMENSION * DIMENSION); i++) {
				 gameButtons[i].setText("");
				 
				 int x = i / GameBoard.DIMENSION;   // обнуляет значения
				 int y = i % GameBoard.DIMENSION;	// внутри наших кнопок
				 									
				 gameField[x][y] = nullSymbol;		// присваиваем пустой символ кнопке при очистке поля
			 }
		 
		 }
		 
		 
		 Game getGame() {
			 return game;
		 }
		 
		 /*
		  * Метод проверки доступности клетки для хода
		  * @param x - по горизонтали
		  * @param у - по вертикали
		  * @return boolean
		  */
		 
		 boolean isTurnable(int x, int y) { 
			 boolean result = false;	       // нельзя пойти в клетку
			 
			 if(gameField[y][x] == nullSymbol) // обходим наше игровое поле, если поле по данным координатам содержит кнопку с пустым символом, то 
				 result = true;				   // в нашу клетку можно пойти(true)
			 
			 
				 return result;
		 }
		 
		 /*
		  * Обновление матрицы игры после хода
		  * @param x - по горизонтали
		  * @param у - по вертикали 
		  */
		 void updateGameField(int x, int y) { // мы приняли 2 координаты, мы должны проставить в нашу клетку символ игрока
			 gameField[y][x] = game.getCurrentPlayer().getPlayerSign(); //у нас есть ссылка на объект игры game, у неё мы можем узнать, кто же сейчас текущий игрок getCurrentPlayer, но текущего игрока не хватит, мы должны поставить его символ, а не его самого -> getPlayerSign
		 }
		 
		 /*
		  * Проверка победы по солбцам и линиям
		  * @return флаг победы
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
		  * Метод проверки заполненности поля, если заполненно -> ничья
		  * @return boolean
		  */
		 boolean isFull() {
			 boolean result = true;
			 
			 for (int i = 0; i < DIMENSION; i++) {
				 for (int j = 0; j < DIMENSION; j++) {
					 if(gameField[i][j] == nullSymbol) //смотрим наличие пустых клеток
						 result = false;
				 }
			 }
			
			 return result;
		 }
		 
		 
		 /*
		  * Проверка победы по столбцам и линиям
		  * @return флаг победы
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
				 
				 //Это условие после каждой проверки колонки и столбца
				 // позволяет остановить дальнейшее выполнение, без проверки
				 // всех остальных столбцов и строк
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
		  * Проверка выигрышных комбинаций на диагоналях
		  * @return флаг победы
		  */
		 private boolean cheakWinDiagonals(char playerSymbol) {
			 boolean leftRight, rightLeft, result;

			    leftRight = true;
			    rightLeft = true;
			    result = false;

			    for(int i = 0; i < DIMENSION; i++){
			        leftRight &= (gameField[i][i] == playerSymbol); //оптимизация кода, чтобы не было через if/else
			        rightLeft &= (gameField[DIMENSION - i - 1][i] == playerSymbol);
			    }

			    if(leftRight || rightLeft){
			        result = true;
			    }

			    return result;
			}
		 
		 public GameButton getButton(int buttonIndex) { 	//buttonIndex - номер кнопки
			 return gameButtons[buttonIndex];				//@return ссылку на неё из массива gameButtons
		 }
}
