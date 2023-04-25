package src.Actions;

import src.Board;
import src.Position;
import src.Token;

public class PlaceAction extends Action {

    public Token token;
    public Position placeAt;


    public PlaceAction(Token token,Position placeAt) {
        this.token = token;
        this.placeAt = placeAt;
    }


    @Override
    public String execute(Token token, Board board) {
        token.isPlaced = true; //so the token cannot be placed again
//        board.addToken(token, moveToPosition);
        return null;
    }

//    public ActionList getAllowableActions() {
//
//    }
}
