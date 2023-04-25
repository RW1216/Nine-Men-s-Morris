package src.Actions;

import src.Board;
import src.Token;

public abstract class Action {

    public abstract String execute(Token token , Board board);
}
