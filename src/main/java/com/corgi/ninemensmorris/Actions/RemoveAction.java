package com.corgi.ninemensmorris.Actions;

import com.corgi.ninemensmorris.Game.Board;
import com.corgi.ninemensmorris.Game.MillDetector;
import com.corgi.ninemensmorris.Game.Position;
import com.corgi.ninemensmorris.Game.Token;
import com.corgi.ninemensmorris.Players.Player;


/**
 * This class represents a Remove action.
 */
public class RemoveAction extends Action{
    Player opponent;
    Position position;

    /**
     * Constructor for a Remove action.
     * @param opponent The opponent of the player.
     * @param position The position of the token to be removed.
     */
    public RemoveAction(Player opponent, Position position) {
        this.opponent = opponent;
        this.position = position;
    }

    /**
     * Executes the Remove action.
     * @param board The board on which the action is executed.
     * @return True if the action was executed successfully, false otherwise.
     */
    @Override
    public boolean execute(Board board) {
//        boolean success;
//        MillDetector millDetector = MillDetector.getInstance();
//        if (!board.isPositionEmpty(position) && board.getToken(position).getOwner() == opponent &&
//                !millDetector.isMill(position)) {
//            success = true;
//            Token tokenRemoved = position.getOccupyingToken();
//            board.removeToken(position);
//            opponent.removeToken(tokenRemoved);
//        } else {
//            success = false;
//        }
//
//        // Prints the description of the action
//        System.out.println(description(success));
//
//        return success;
        boolean success;
        success = isValid(board);
        if (success) {
            Token tokenRemoved = position.getOccupyingToken();
            board.removeToken(position);
            opponent.removeToken(tokenRemoved);
        }

        return success;
    }

    /**
     * Checks if the Remove action is valid.
     * @param board The board on which the action is executed.
     * @return True if the action is valid, false otherwise.
     */
    @Override
    public boolean isValid(Board board) {
        boolean success;
        MillDetector millDetector = MillDetector.getInstance();
        success = !board.isPositionEmpty(position) && board.getToken(position).getOwner() == opponent &&
                !millDetector.isMill(position);

        return success;
    }

    /**
     * Returns a description of the Remove action.
     * @param success True if the action was executed successfully, false otherwise.
     * @return A description of the Remove action.
     */
    @Override
    public String description(boolean success) {
        if (success) {
            return "Remove token " + opponent.getTokenColor() + " from " + position;
        } else {
            return "Invalid move";
        }
    }
}
