package src.Players;

import src.Position;
import src.Token;

import java.util.ArrayList;

public abstract class Player {

    private Color tokenColor;
    private ArrayList<Token> tokens;
    private PlayerState playerState;
    private PlayerState placingState;
    private PlayerState MovingState;
    private PlayerState FlyingState;
    private int tokensPlaced;
    private int tokenCount;

    public Player(Color tokenColor) {
        this.tokenColor = tokenColor;
        tokens = new ArrayList<>();

        placingState = new PlacingState(this);
        MovingState = new MovingState(this);
        FlyingState = new FlyingState(this);

        playerState = placingState;
        tokensPlaced = 0;
        tokenCount = 0;
    }

    public Color getTokenColor() {
        return tokenColor;
    }

    public void addToken(Token token){
        if (token == null){
            throw new NullPointerException("Unable to add a null token!");
        }
        tokens.add(token);
        tokensPlaced++;
    }

//    todo: fix logic
    public void removeTokenCount(){
//        boolean tokenInTokens = false;
//        for (Token thisToken: tokens)
//            if (thisToken == token) {
//                tokenInTokens = true;
//            }
//        if (tokenInTokens)
//            tokens.remove(token);
        tokenCount--;
    }

    public void addTokenCount(){
        tokenCount++;
    }

    public void setPlayerState(PlayerState newPlayerState) {
        this.playerState = newPlayerState;
    }

    public void setTokensAllowableActions(ArrayList<Position> emptyPositions, Object ArrayList) {
        for(Token token: tokens) {
            token.getAllowableActions().clear();
        }
        playerState.setTokensAllowableActions(this.tokens, emptyPositions);
    }

    public void updateSelfState(){
        if (playerState == placingState && tokensPlaced == 9){
            playerState = MovingState;
        } else if (playerState == MovingState && tokenCount == 3){
            playerState = FlyingState;
        }
    }

    public int getTokenCount(){
        return tokenCount;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public PlayerState getPlacingState() {
        return placingState;
    }

    public PlayerState getMovingState() {
        return MovingState;
    }

    public PlayerState getFlyingState() {
        return FlyingState;
    }
}

