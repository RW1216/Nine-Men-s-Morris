package src;

public class FlyingState implements PlayerState {

    public Token token;
    public Position moveToPosition;


    public FlyingState(Token token, Position moveToPosition) {
        this.token = token;
        this.moveToPosition = moveToPosition;
    }



//    public ActionList getAllowableActions() {
//    }
}
