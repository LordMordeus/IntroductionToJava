package Game;

import java.util.Random;

public class GamePlayer {
    private char playerSigh;
    private boolean realPlayer = true;
    private int row;
    private int cell;
    private GameButton button;

    public GamePlayer (boolean isRealPlayer, char playerSigh){
        this.realPlayer = isRealPlayer;
        this.playerSigh = playerSigh;
    }

    public boolean isRealPlayer(){
        return realPlayer;
    }

    public char getPlayerSigh(){
        return playerSigh;
    }
    void updateByPlayersData(GameButton button){
        int row = button.getButtonIndex() / GameBoard.dimension;
        int cell = button.getButtonIndex() % GameBoard.dimension;

        button.getBoard().updateGameField(row, cell);

        button.setText(Character.toString(button.getBoard().getGame().getCurrentPlayer().getPlayerSigh()));

        if (button.getBoard().checkWin()){
            button.getBoard().getGame().showMessage("You win");
            button.getBoard().emptyField();
        }else {
            button.getBoard().getGame().passTurn();
        }
    }

    void updateByAiData(GameBoard board){
        int x, y;
        Random rnd = new Random();

        do{
            x = rnd.nextInt(GameBoard.dimension);
            y = rnd.nextInt(GameBoard.dimension);
        }while (!board.isTurnable(x, y));

        board.updateGameField(x, y);

        int cellIndex = GameBoard.dimension * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSigh()));

        if (board.checkWin()){
            button.getBoard().getGame().showMessage("Opponent win");
            board.emptyField();
        }else {
            board.getGame().passTurn();
        }
    }

}
