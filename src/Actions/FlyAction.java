package src.Actions;

import src.Actions.Action;
import src.Board;
import src.Position;
import src.Token;

public class FlyAction extends Action {
    private final Token token;
    private final Position moveToPosition;
    private final Position initialPosition;


    public FlyAction(Token token, Position initialPosition, Position moveToPosition) {
        this.token = token;
        this.moveToPosition = moveToPosition;
        this.initialPosition = initialPosition;
    }

    @Override
    public boolean execute(Board board) {
        boolean success;
        if (board.isPositionEmpty(moveToPosition)) {
            success = true;
            board.moveToken(token, initialPosition, moveToPosition);
        } else {
            success = false;
        }

        System.out.println(description(success));

        return success;
    }

    @Override
    public String description(boolean success) {
        if (success) {
            return "Move (fly) token " + token.getOwner().getTokenColor() + " from " + initialPosition + " to " + moveToPosition;
        } else {
            return "Invalid move";
        }
    }
}
