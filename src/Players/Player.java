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

    public Player(Color tokenColor) {
        this.tokenColor = tokenColor;
        tokens = new ArrayList<>();

        placingState = new PlacingState(this);
        MovingState = new MovingState(this);
        FlyingState = new FlyingState(this);

        playerState = placingState;
    }

    public Color getTokenColor() {
        return tokenColor;
    }

    public void addToken(Token token){
        if (token == null){
            throw new NullPointerException("Unable to add a null token!");
        }
        tokens.add(token);
    }

//    todo: fix logic
    public void removeToken(Token token){
        boolean tokenInTokens = false;
        for (Token thisToken: tokens)
            if (thisToken == token) {
                tokenInTokens = true;
            }
        if (tokenInTokens)
            tokens.remove(token);
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
        if (tokens.size() == 9){
            playerState = MovingState;
        }
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

