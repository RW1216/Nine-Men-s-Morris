package com.corgi.ninemensmorris.Players;


import com.corgi.ninemensmorris.Actions.PositionFinder;
import com.corgi.ninemensmorris.BoardUI;
import com.corgi.ninemensmorris.Enum.Color;
import com.corgi.ninemensmorris.Enum.PlayerState;
import com.corgi.ninemensmorris.Game.Board;
import com.corgi.ninemensmorris.Game.Position;
import com.corgi.ninemensmorris.Game.Token;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 *
 * This class represents a human player.
 *
 */
public class AI extends Player {

    /**
     * Constructor for a human player.
     * @param tokenColor The color of the player.
     */
    public AI(Color tokenColor) {
        super(tokenColor);
    }

    public Token getRandomToken() {
//        PositionFinder positionFinder = PositionFinder.getInstance();

        int tokenIndex = (int) (Math.random() * this.getTokenCount());

        for (Token token: this.getTokens()) {
            System.out.print(token.getPosition());
        }
        System.out.println();

        return this.getTokens().get(tokenIndex);
    }

    public Token getMovableToken() {
        ArrayList<Token> movableTokens = new ArrayList<>();

        for (Token token: this.getTokens()) {
            if (token.isMovable()) {
                movableTokens.add(token);
            }
        }

        int tokenIndex = (int) (Math.random() * movableTokens.size());

        System.out.println(movableTokens);

        return movableTokens.get(tokenIndex);
    }

    @Override
    public Position getClickedPosition(CountDownLatch latch, Board board, BoardUI board_UI) {

        PositionFinder positionFinder = PositionFinder.getInstance();
        ArrayList<Position> possiblePositions;

        if (this.getIsRemoving()){
            System.out.println("removing");
            possiblePositions = positionFinder.getRemovablePos(board, this.getOpponent());
        }
        else if (this.getPlayerState() == PlayerState.PLACING) {
            possiblePositions = board.getEmptyPositions();
        }
        else if (!this.getHasSelectedToken()) {
            System.out.println("AI selects token");
            Token selectedToken = null;
            if (this.getPlayerState() == PlayerState.MOVING) {
                System.out.println("AI is moving");
                selectedToken =  getMovableToken();
            }
            else if (this.getPlayerState() == PlayerState.FLYING) {
                System.out.println("AI is flying");
                selectedToken = getRandomToken();
            }
            this.setSelectedToken(selectedToken);


            assert selectedToken != null;
            return selectedToken.getPosition();
        }
        else {
            System.out.println("else");
            possiblePositions = positionFinder.getPositions(board, this, this.getSelectedToken().getPosition());
        }

        int positionIndex = (int) (Math.random() * possiblePositions.size());

//        System.out.println(possiblePositions.get(positionIndex));

        System.out.println(possiblePositions);

        return possiblePositions.get(positionIndex);
    }

}
