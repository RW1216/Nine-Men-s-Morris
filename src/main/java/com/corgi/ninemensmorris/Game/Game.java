package com.corgi.ninemensmorris.Game;

import com.corgi.ninemensmorris.Actions.*;
import com.corgi.ninemensmorris.BoardUI;
import com.corgi.ninemensmorris.Enum.Color;
import com.corgi.ninemensmorris.Enum.PlayerState;
import com.corgi.ninemensmorris.Players.Human;
import com.corgi.ninemensmorris.Players.Player;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 *
 * This is the class to get the actual game started and determines the current phase of each Player
 *
 */

public class Game {

    private int turn;
    private final Player playerRed;
    private final Player playerYellow;
    private final Board board;
    private final BoardUI board_ui;
    CountDownLatch latch;

    private static final String White = "#ffffff";
    private static final String Red = "#ff0000";
    private static final String Yellow = "#fffd00";
    private static final String LightBlue = "#68aafc";
    private static final String Green = "#00ff00";


    /**
     * Constructor for a game.
     * @param board_ui The board UI.
     */
    public Game(BoardUI board_ui) {
        this.board_ui = board_ui;
        board = Board.getInstance();
        playerRed = new Human(Color.RED);
        playerYellow = new Human(Color.YELLOW);
        turn = 0;
    }

    public void resetBoard(){
        board.resetInstance();
        MillDetector.getInstance().resetInstance();
    }

    public void startTutorial(){
        MillDetector millDetector = MillDetector.getInstance();
        System.out.println("Tutorial started");

        //Disable all positions
        for (int i = 0; i < board.getPositions().length; i++) {
            for (int j = 0; j < board.getPositions()[i].length; j++) {
                if (board.getPosition(i, j) != null){
                    board_ui.disablePosition(i, j);
                }
            }
        }

        //Welcome
        board_ui.updateTextBox("Welcome to tutorial mode. Click next to continue");
        startLatch();
        //Start as red
        board_ui.updateTextBox("You will start as red! Click next to continue");
        startLatch();

        //Placing phase, user places their first token
        board_ui.disableNextBtn();
        board_ui.updateTextBox("It is placing phase now, click on the highlighted position to place your token!");
        board_ui.highlightPosition(0, 0, LightBlue);
        board_ui.enablePosition(0, 0);
        startLatch();
        board_ui.unhighlightPosition(0, 0);
        board_ui.disablePosition(0, 0);
        //Update boardUI
        Position selectedPos = getClickedPosition();
        PlaceAction placeAction = new PlaceAction(playerRed, selectedPos);
        placeAction.execute(board);
        updateBoardUI();
        board_ui.updateTextBox("You have successfully placed your first token! Click next to continue");
        board_ui.enableNextBtn();
        startLatch();
        //Opponent places their token
        board_ui.updateTextBox("Now your opponent will place their own token. Click next to continue");
        startLatch();
        selectedPos = board.getPosition(0, 6);
        placeAction = new PlaceAction(playerYellow, selectedPos);
        placeAction.execute(board);
        updateBoardUI();
        board_ui.updateTextBox("Your opponent has placed their token at row 0 col 6. Now, lets talk about mill. Click next to continue");
        startLatch();

        //Mill, Remove opponents token
        board_ui.updateTextBox("Mill is formed after aligning 3 same tokens. After forming mill, you can remove one opponent token that " +
                "is not a mill. Click next to continue");
        startLatch();
        //Update board
        selectedPos = board.getPosition(3, 0);
        placeAction = new PlaceAction(playerRed, selectedPos);
        placeAction.execute(board);
        selectedPos = board.getPosition(3, 6);
        placeAction = new PlaceAction(playerYellow, selectedPos);
        placeAction.execute(board);
        updateBoardUI();
        //Asking user to click on position 6, 0
        board_ui.updateTextBox("Here is an example. Click on the highlighted position to form a mill");
        board_ui.disableNextBtn();
        board_ui.highlightPosition(6, 0, LightBlue);
        board_ui.enablePosition(6, 0);
        startLatch();
        board_ui.unhighlightPosition(6, 0);
        board_ui.disablePosition(6, 0);
        //Update board
        selectedPos = board.getPosition(6, 0);
        placeAction = new PlaceAction(playerRed, selectedPos);
        placeAction.execute(board);
        updateBoardUI();
        board_ui.updateTextBox("You have formed a mill, you can remove one of your opponents token that is not a mill. Click " +
                "on the highlighted position to remove a token");
        //Highlight and enable yellow tokens/position
        board_ui.disableNextBtn();
        board_ui.highlightPosition(0, 6, LightBlue);
        board_ui.highlightPosition(3, 6, LightBlue);
        board_ui.enablePosition(0, 6);
        board_ui.enablePosition(3, 6);
        //Ask user to click on yellow tokens
        latch = new CountDownLatch(1);
        selectedPos = getClickedPosition();
        RemoveAction removeAction = new RemoveAction(playerYellow, selectedPos);
        removeAction.execute(board);
        updateBoardUI();
        board_ui.unhighlightPosition(0, 6);
        board_ui.unhighlightPosition(3, 6);
        board_ui.disablePosition(0, 6);
        board_ui.disablePosition(3, 6);
        board_ui.updateTextBox("You have removed the opponents token! Now lets move on to flying phase. Click " +
                "next to continue");
        board_ui.enableNextBtn();
    }

    /**
     * Starts the game.
     */
    public void start(){
        MillDetector millDetector = MillDetector.getInstance();
        PositionFinder positionFinder = PositionFinder.getInstance();
        System.out.println("Game started");

        ArrayList<Position> highlightedPos;

        while (gameActive()) {
            // Initialize local variables
            boolean moveMade = false;
            Position moveMadePos = null;
            PlayerState currentPhase;
            Player currentPlayer;
            Player opponent;

            //Initialize CountDownLatch
            latch = new CountDownLatch(1);

            //Update UI
            String turnColor;

            // Determine whose turn it is
            if (turn % 2 == 0) {
                currentPlayer = playerRed;
                opponent = playerYellow;

                currentPhase = currentPlayer.getPlayerState();
                turnColor = Red;
                System.out.println("Red's turn");

            } else {
                currentPlayer = playerYellow;
                opponent = playerRed;

                currentPhase = currentPlayer.getPlayerState();
                turnColor = Yellow;
                System.out.println("Yellow's turn");
            }

            // Update UI with current phase
            String phaseText = "";
            if (currentPhase.equals(PlayerState.PLACING)){
                phaseText = "Place a token";
            } else if (currentPhase.equals(PlayerState.MOVING)){
                phaseText = "Move a token";
            } else if (currentPhase.equals(PlayerState.FLYING)){
                phaseText = "Fly a token";
            }

            //Update UI Components
            board_ui.updateTurnCircle(turnColor);
            board_ui.updateTextBox(phaseText);

            board_ui.updateRedPiecesLeft(9 - playerRed.getTokensPlaced());
            board_ui.updateYellowPiecesLeft(9 - playerYellow.getTokensPlaced());

            // PLACING PHASE =====================================================
            Token selectedToken;
            if (currentPhase == PlayerState.PLACING) {
                System.out.println("Select a position to place your token");

                Position selectedPos = getClickedPosition();

                PlaceAction placeAction = new PlaceAction(currentPlayer, selectedPos);
                moveMade = placeAction.execute(board);
                moveMadePos = selectedPos;

            // MOVING PHASE =====================================================
            } else if (currentPhase == PlayerState.MOVING) {
                System.out.println("Select a token to move");

                Position selectedPos1 = getClickedPosition();



                selectedToken = selectedPos1.getOccupyingToken();
                if (selectedToken == null || selectedToken.getOwner() != currentPlayer) {
                    System.out.println("Please select your token");
                    System.out.println("Selected token at " + selectedPos1);
                    continue;
                } else {
                    board_ui.unhighlightAllPositions();

                    //Highlight circle in the UI
                    board_ui.highlightPosition(selectedPos1.getX(), selectedPos1.getY(), LightBlue);
                    highlightedPos = positionFinder.getRemovablePos(board, currentPlayer, selectedPos1);
                    for (Position pos : highlightedPos){
                        board_ui.highlightPosition(pos.getX(), pos.getY(), Green);
                    }

                    System.out.println("Selected token at " + selectedPos1);
                }

                latch = new CountDownLatch(1);

                Position selectedPos2 = getClickedPosition();

                MoveAction moveAction = new MoveAction(selectedToken, selectedPos1, selectedPos2);
                moveMade = moveAction.execute(board);
                moveMadePos = selectedPos2;

                //Unhighlight circle in the UI
                board_ui.unhighlightPosition(selectedPos1.getX(), selectedPos1.getY());
                board_ui.unhighlightAllPositions();

            // FLYING PHASE =====================================================
            } else if (currentPhase == PlayerState.FLYING) {
                System.out.println("Select a token to fly");

                Position selectedPos1 = getClickedPosition();

                selectedToken = selectedPos1.getOccupyingToken();
                if (selectedToken == null || selectedToken.getOwner() != currentPlayer) {
                    System.out.println("Please select your token");
                    System.out.println("Selected token at " + selectedPos1);
                    continue;
                } else {
                    board_ui.unhighlightAllPositions();

                    //Highlight circle in the UI
                    board_ui.highlightPosition(selectedPos1.getX(), selectedPos1.getY(), LightBlue);
                    highlightedPos = positionFinder.getRemovablePos(board, currentPlayer, selectedPos1);
                    for (Position pos : highlightedPos){
                        board_ui.highlightPosition(pos.getX(), pos.getY(), Green);
                    }
                    System.out.println("Selected token at " + selectedPos1);
                }

                latch = new CountDownLatch(1);

                Position selectedPos2 = getClickedPosition();

                FlyAction flyAction = new FlyAction(selectedToken, selectedPos1, selectedPos2);
                moveMade = flyAction.execute(board);
                moveMadePos = selectedPos2;

                //Unhighlight circle in the UI
                board_ui.unhighlightPosition(selectedPos1.getX(), selectedPos1.getY());
                board_ui.unhighlightAllPositions();
            }

            updatePlayerState();

            // Update the board UI
            updateBoardUI();

            // If a move was made, check if a mill was formed
            if (moveMade) {
                System.out.println("token placed/moved at " + moveMadePos);
                if (millDetector.isMill(moveMadePos)) {
                    System.out.println("Mill formed");

                    // highlight all opponent tokens
                    highlightedPos = positionFinder.getRemovablePos(board, opponent);
                    for (Position pos : highlightedPos){
                        board_ui.highlightPosition(pos.getX(), pos.getY(), Green);
                    }

                    boolean removeMade = false;

                    while (!removeMade) {
                        System.out.println("Select a token to remove");
                        board_ui.updateTextBox("Remove a token");
                        latch = new CountDownLatch(1);
                        Position selectedPos = getClickedPosition();
                        RemoveAction removeAction = new RemoveAction(opponent, selectedPos);
                        removeMade = removeAction.execute(board);
                    }

                    // Unhighlight all positions
                    board_ui.unhighlightAllPositions();

                    // Update the board UI
                    updatePlayerState();
                    updateBoardUI();
                }
                turn++;
                System.out.println("Player Red tokens: " + playerRed.getTokenCount() + " Player Yellow tokens: " + playerYellow.getTokenCount());
            }
            System.out.println("Red state: " + playerRed.getPlayerState());
            System.out.println("Yellow state: " + playerYellow.getPlayerState());
        }
    }


    /**
     * Updates the UI to reflect the board state.
     */
    private void updateBoardUI(){
        for (int i = 0; i < board.getPositions().length; i++) {
            for (int j = 0; j < board.getPositions()[i].length; j++) {
                if (board.getPosition(i, j) != null){
                    Token token = board.getPosition(i, j).getOccupyingToken();
                    if (token == null){
                        board_ui.updatePositionFill(i, j, White);
                    }
                    else if (token.getOwner().getTokenColor().equals(Color.RED)){
                        board_ui.updatePositionFill(i, j, Red);
                    }
                    else if (token.getOwner().getTokenColor().equals(Color.YELLOW)){
                        board_ui.updatePositionFill(i, j, Yellow);
                    }
                }
            }
        }
    }

    /**
     * Gets the position that the user clicks on.
     */
    public void positionSelected(){
        // If a latch exists, count down by 1 to release the blocked thread
        if (latch != null) {
            latch.countDown();
        }
    }


    /**
     * Checks if the game is active.
     * @return true if the game is active, false otherwise.
     */
    public boolean gameActive() {
        boolean active = true;

        if (playerRed.getPlayerState() != PlayerState.PLACING)
            if (playerRed.getTokenCount() < 3) {
                System.out.println("Game ended, Yellow wins");
                board_ui.showWinner("YELLOW WON");
                active = false;
            } else if (playerYellow.getPlayerState() != PlayerState.PLACING)
            if (playerYellow.getTokenCount() < 3) {
                System.out.println("Game ended, Red wins");
                board_ui.showWinner("RED WON");
                active = false;
            }

        if (playerRed.cannotMove()) {
            System.out.println("Game ended, Yellow wins");
            board_ui.showWinner("YELLOW WON");
            active = false;
        } else if (playerYellow.cannotMove()) {
            System.out.println("Game ended, Red wins");
            board_ui.showWinner("RED WON");
            active = false;
        }

        return active;
    }

    /**
     * Updates Red and Yellow player states.
     */
    public void updatePlayerState() {
        playerRed.updateSelfState();
        playerYellow.updateSelfState();
    }

    /**
     * Gets the position that the user clicks on.
     * @return the position that the user clicks on.
     */
    private Position getClickedPosition(){

        try {
            latch.await();

        } catch (InterruptedException e){
            e.printStackTrace();
        }

        int selectedCol = board_ui.getSelectedCol();
        int selectedRow = board_ui.getSelectedRow();

        return board.getPosition(selectedRow, selectedCol);
    }

    private void startLatch(){
        //Wait for the user to click on a button
        latch = new CountDownLatch(1);

        try {
            latch.await();

        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
