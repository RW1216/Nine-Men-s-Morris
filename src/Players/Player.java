package src.Players;

import src.Position;
import src.Token;

import java.util.ArrayList;

public abstract class Player {

    private Color tokenColor;
    private ArrayList<Token> tokens;
    private PlayerState playerState;
    private int tokensPlaced;
    private int tokenCount;

    public Player(Color tokenColor) {
        this.tokenColor = tokenColor;
        tokens = new ArrayList<>();
        playerState = PlayerState.Placing;
        tokensPlaced = 0;
        tokenCount = 0;
    }

    public Color getTokenColor() {
        return tokenColor;
    }

    public void addToken(Token token) {
        if (token == null) {
            throw new NullPointerException("Unable to add a null token!");
        }
        tokens.add(token);
        tokensPlaced++;
    }

    public int getTokensPlaced(){
        return tokensPlaced;
    }

    //    todo: fix logic
    public void removeTokenCount() {
//        boolean tokenInTokens = false;
//        for (Token thisToken: tokens)
//            if (thisToken == token) {
//                tokenInTokens = true;
//            }
//        if (tokenInTokens)
//            tokens.remove(token);
        tokenCount--;
    }

    public void addTokenCount() {
        tokenCount++;
    }

    public void setPlayerState(PlayerState newPlayerState) {
        this.playerState = newPlayerState;
    }


    public void updateSelfState() {
        if (playerState == PlayerState.Placing && tokensPlaced == 9) {
            playerState = PlayerState.Moving;
        } else if (playerState == PlayerState.Moving && tokenCount == 3) {
            playerState = PlayerState.Flying;
        }
    }

    public int getTokenCount() {
        return tokenCount;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }
}



