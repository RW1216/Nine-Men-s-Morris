package src;

public class Flying extends Action implements TokenState {

    public Token token;
    public Position moveToPosition;


    public Flying(Token token,Position moveToPosition) {
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

//    public ActionList getAllowableActions() {
//    }
}
