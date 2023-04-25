package src.Players;

import src.Actions.PlaceAction;
import src.Position;
import src.Token;

import java.util.ArrayList;

public class PlacingState implements PlayerState {

    public PlacingState() {

    }

    public void setTokensAllowableActions(ArrayList<Token> tokens, ArrayList<Position> emptyPositions) {
        for(Token token: tokens) {
            for (Position position: emptyPositions) {
                if (!token.isPlaced)
                    token.getAllowableActions().add(new PlaceAction(token, position));
            }
        }
    }
}
