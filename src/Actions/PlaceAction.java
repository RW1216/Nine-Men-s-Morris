package src.Actions;

import src.Actions.Action;
import src.Board;
import src.Players.Player;
import src.Position;
import src.Token;

public class PlaceAction extends Action {

    public Player owner;
    public Position placeAt;


    public PlaceAction(Player owner, Position placeAt) {
        this.owner = owner;
        this.placeAt = placeAt;
    }

    @Override
    public boolean execute(Board board) {
        boolean success;
        if (board.isPositionEmpty(placeAt)) {
            Token token = new Token(owner, placeAt);
            owner.addToken(token);
            success = true;
            board.placeToken(token, placeAt);
            owner.addTokenCount();
        } else {
            success = false;
        }

        System.out.println(description(success));

        return success;
    }

    @Override
    public String description(boolean success) {
        if (success) {
            return "Placed token at " + placeAt;
        } else {
            return "Invalid position";
        }
    }

//    public ActionList getAllowableActions() {
//
//    }
}
