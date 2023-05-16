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

    public int getTokenCount() {
        return tokenCount;
    }

    public Color getTokenColor() {
        return tokenColor;
    }

    public int getTokensPlaced() { return tokensPlaced; }

    public void addToken(Token token) {
        if (token == null) {
            throw new NullPointerException("Unable to add a null token!");
        }
        tokens.add(token);
        tokensPlaced++;
    }

    public void removeToken(Token token) {
        if (token == null) {
            throw new NullPointerException("Unable to remove a null token!");
        }
        tokens.remove(token);
        tokenCount--;
    }

//    //
//    public void removeTokenCount() {
////        boolean tokenInTokens = false;
////        for (Token thisToken: tokens)
////            if (thisToken == token) {
////                tokenInTokens = true;
////            }
////        if (tokenInTokens)
////            tokens.remove(token);
//        tokenCount--;
//    }

    public void addTokenCount() {
        tokenCount++;
    }

    public boolean cannotMove() {
        if (playerState == PlayerState.Placing) {
            return false;
        }

        boolean hasMovesLeft = false;
        for (Token token: tokens) {
            if (hasMovesLeft) {
                hasMovesLeft = true;
            }
            for (Position position: token.getPosition().getAdjacentPositions()) {
                if (!position.hasToken()) {
                    hasMovesLeft = true;
                    break;
                }
            }
        }
        if (!hasMovesLeft) {
            System.out.println("Game ended, " + tokenColor.getLabel() + " loses");
        }
        return !hasMovesLeft;
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

    public PlayerState getPlayerState() {
        return playerState;
    }
}



