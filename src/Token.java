package src;

public class Token {

    private TokenState tokenState;
    private TokenState placing;
    private TokenState moving;
    private TokenState flying;

    private int id;
    private ActionList allowableActions;

    public Token(int id) {
        this.id = id;
        placing = new Placing(this);
        moving =  new Moving(this);
        flying = new Flying(this);
    }

    public TokenState getTokenState() { return tokenState; }

    public TokenState getPlacing() { return placing; }

    public TokenState getMoving() { return moving; }

    public TokenState getFlying() { return flying; }

    public int getId() { return id; }

    public void setTokenState(TokenState newTokenState) {
        this.tokenState = newTokenState;
    }

    public ActionList getAllowableActions() {
        return tokenState.getAllowableActions();
    }

    public void addAction(Action action) {
        if(action == null){
            throw new NullPointerException("Unable to add a null action!");
        }
        this.allowableActions.add(action);
    }

    public void removeAction(Action action){
        if(action == null){
            throw new NullPointerException("Unable to add a null action!");
        }
        this.allowableActions.remove(action);
    }

    public void clearActions(){
        this.allowableActions = new ActionList();
    }


}
