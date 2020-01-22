package Game;

public class GamePlayer {
    private char playerSigh;
    private boolean realPlayer = true;

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
}
