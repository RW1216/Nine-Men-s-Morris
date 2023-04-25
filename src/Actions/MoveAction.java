package src.Actions;

import src.Board;
import src.Position;
import src.Token;

public class MoveAction extends Action {
    public Token token;
    public Position moveToPosition;


    public MoveAction(Token token,Position moveToPosition) {
        this.token = token;
        this.moveToPosition = moveToPosition;
    }

    @Override
    public String execute(Token token, Board board) {
//        board.moveTo(token, moveToPosition);
        return null;
    }
}
