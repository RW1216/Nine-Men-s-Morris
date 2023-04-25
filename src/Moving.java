package src;

public class Moving extends Action implements TokenState {
    public Token token;
    public Position moveToPosition;


    public Moving(Token token,Position moveToPosition) {
        this.token = token;
        this.moveToPosition = moveToPosition;
    }

    @Override
    public String execute(Token token, Board board) {
//        board.moveTo(token, moveToPosition);
        return null;
    }

//    public ActionList getAllowableActions() {
//
//    }
}
