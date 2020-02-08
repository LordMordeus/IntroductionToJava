package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener {

    private GameButton button;

    public GameActionListener(int rowNum, int cellNum, GameButton gButton) {
        button = gButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();

        int row = button.getButtonIndex() / GameBoard.dimension;
        int cell = button.getButtonIndex() % GameBoard.dimension;

        if (board.isTurnable(row, cell)) {
            board.getGame().getCurrentPlayer().updateByPlayersData(button);

            if (board.isFull()) {
                button.getBoard().getGame().showMessage("Draw");
                board.emptyField();
            } else {
                board.getGame().showMessage("Incorrect turn");
            }
        }
    }
}
