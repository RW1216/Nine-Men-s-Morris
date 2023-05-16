package src.Actions;

import src.Board;
import src.MillDetector;
import src.Players.Player;
import src.Position;

public class RemoveAction extends Action{
    Player opponent;
    Position position;

    public RemoveAction(Player opponent, Position position) {
        this.opponent = opponent;
        this.position = position;
    }

    @Override
    public boolean execute(Board board) {
        boolean success;
        MillDetector millDetector = MillDetector.getInstance();
        if (!board.isPositionEmpty(position) && board.getToken(position).getOwner() == opponent &&
                !millDetector.isMill(position)) {
            success = true;
            board.removeToken(position);
            opponent.removeTokenCount();
        } else {
            success = false;
        }

        System.out.println(description(success));

        return success;
    }

    @Override
    public String description(boolean success) {
        if (success) {
            return "Remove token " + opponent.getTokenColor() + " from " + position;
        } else {
            return "Invalid move";
        }
    }
}
