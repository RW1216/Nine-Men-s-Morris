package src.Actions;

import java.util.ArrayList;

public class ActionList {
    private ArrayList<Action> actions = new ArrayList<Action>();
    private boolean bool = false;

    public ActionList(){};

    public boolean add(Action action){

        if (action != null){
            actions.add(action);
        }
        return true;
    }

    public boolean remove(Action action){
        if (action != null){
            actions.remove(action);
        }
        return true;
    }

}
