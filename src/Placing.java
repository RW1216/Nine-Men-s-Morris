package src;

public class Placing extends Action implements TokenState {

    public Token token;
    public Position placeAt;


    public Placing(Token token,Position placeAt) {
        this.token = token;
        this.placeAt = placeAt;
    }


    @Override
    public String execute(Token token, Board board) {
//        board.addToken(token, moveToPosition);
        return null;
    }

//    public ActionList getAllowableActions() {
//
//    }
}
