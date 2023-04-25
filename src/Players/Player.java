package src.Players;

import src.Position;
import src.Token;

import java.util.ArrayList;

public abstract class Player {

    private String name;
    private String tokenColor;
    private ArrayList<Token> tokens;
    private PlayerState playerState;
    private PlayerState placingState;
    private PlayerState MovingState;
    private PlayerState FlyingState;

    public Player(String name, String tokenColor) {
        this.name = name;
        this.tokenColor = tokenColor;
        tokens = new ArrayList<>();
        playerState = new PlacingState();
        placingState = new PlacingState();
        MovingState = new MovingState();
        FlyingState = new FlyingState();
    }

    public String getName() {
        return name;
    }

    public String getTokenColor() {
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
}

