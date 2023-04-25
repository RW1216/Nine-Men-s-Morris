package src;

public class PlaceAction extends Action {

    public Token token;
    public Position placeAt;


    public PlaceAction(Token token,Position placeAt) {
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
