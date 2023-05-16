package src.Actions;

import src.Board;
import src.Token;

public abstract class Action {
    public abstract boolean execute(Board board);

    public abstract String description(boolean success);
}
