package src.Players;

import src.Position;
import src.Token;

import java.util.ArrayList;

/**
 *
 * This class represents a player.
 *
 */
public abstract class Player {

    private final Color tokenColor;
    private final ArrayList<Token> tokens;
    private PlayerState playerState;
    private int tokensPlaced;
    private int tokenCount;

    /**
     * Constructor for a player.
     * @param tokenColor The color of the player.
     */
    public Player(Color tokenColor) {
        this.tokenColor = tokenColor;
        tokens = new ArrayList<>();
        playerState = PlayerState.PLACING;
        tokensPlaced = 0;
        tokenCount = 0;
    }

    /**
     * Returns the color of the player.
     * @return The color of the player.
     */
    public int getTokenCount() {
        return tokenCount;
    }

    /**
     * Returns the color of the player.
     * @return The color of the player.
     */
    public Color getTokenColor() {
        return tokenColor;
    }

    /**
     * Returns the player's tokens.
     * @return The player's tokens.
     */
    public int getTokensPlaced() { return tokensPlaced; }

    /**
     * Adds a token to the player's tokens.
     */
    public void addToken(Token token) {
        if (token == null) {
            throw new NullPointerException("Unable to add a null token!");
        }
        tokens.add(token);
        tokensPlaced++;
    }

    /**
     * Removes a token from the player's tokens.
     */
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

    /**
     * Adds one to the token count.
     */
    public void addTokenCount() {
        tokenCount++;
    }

    public boolean cannotMove() {
        if (playerState == PlayerState.PLACING) {
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


    /**
     * Updates the player's state
     */
    public void updateSelfState() {
        if (playerState == PlayerState.PLACING && tokensPlaced == 4) {
            playerState = PlayerState.MOVING;
        } else if (playerState == PlayerState.MOVING && tokenCount == 3) {
            playerState = PlayerState.FLYING;
        }
    }

    /**
     * Returns the player's state.
     */
    public PlayerState getPlayerState() {
        return playerState;
    }
}



