package src.Players;

import src.Position;
import src.Token;

import java.util.ArrayList;

public interface PlayerState {
    public void setTokensAllowableActions(ArrayList<Token> tokens, ArrayList<Position> emptyPositions);
}
