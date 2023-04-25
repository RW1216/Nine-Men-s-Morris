package src;

import java.util.ArrayList;

public abstract class Player {

    private String name;
    private String tokenColor;
    private ArrayList<Token> tokens;

    public Player(String name, String tokenColor) {
        this.name = name;
        this.tokenColor = tokenColor;
        tokens = new ArrayList<>();
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
//        if token not in tokens
        tokens.remove(token);
    }
}

