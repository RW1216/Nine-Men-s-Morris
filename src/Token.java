package src;

import src.Actions.Action;
import src.Actions.ActionList;

public class Token {



    private ActionList allowableActions;
    public boolean isPlaced = false;

    public Token() {

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


}
