package src.Players;

import java.util.ArrayList;

public interface PlayerState {
    public void setTokensAllowableActions(ArrayList<Token> tokens, ArrayList<Position> emptyPositions);
}
