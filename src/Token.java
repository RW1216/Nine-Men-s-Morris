package src;

import src.Actions.Action;
import src.Actions.ActionList;
import src.Players.Player;

public class Token {


    private Player owner;
    private ActionList allowableActions;
    private Position position;
    public boolean isPlaced;

    public Token(Player owner) {
        this.owner = owner;
        this.allowableActions = new ActionList();
    }

    public ActionList getAllowableActions() {
        return this.getAllowableActions();
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
//        this.allowableActions.remove(action);
    }

    public void clearActions(){
        this.allowableActions = new ActionList();
    }

    public Player getOwner() {
        return owner;
    }
}
