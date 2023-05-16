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

    public ActionList(Action action){
        add(action);
    }

    public void clear() {
        actions = new ArrayList<Action>();
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    //    public void addAction (ActionList actions){
//        for (Action action :actions){
//            add(action);
//        }
//    }
}
