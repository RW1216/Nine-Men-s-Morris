package src.Actions;

import src.Board;
import src.Position;
import src.Token;

public class FlyAction extends Action {
    public Token token;
    public Position moveToPosition;


    public FlyAction(Token token,Position moveToPosition) {
        this.token = token;
        this.moveToPosition = moveToPosition;
    }

    @Override
    public String execute(Token token, Board board) {
//        if (token.getTokenState() == Flying{
//            board.moveTo(token, moveToPosition);
//        }
//
        return null;
    }

}
