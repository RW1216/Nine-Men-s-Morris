package src;

import src.Actions.FlyAction;
import src.Actions.MoveAction;
import src.Actions.PlaceAction;
import src.Actions.RemoveAction;
import src.Display.Board_UI;
import src.Players.*;

import java.util.concurrent.CountDownLatch;

public class Game {

    private int turn;
    private final Player playerRed;
    private final Player playerYellow;
    private final Board board;
    private final Board_UI board_ui;
    CountDownLatch latch;

    private static final String White = "#ffffff";
    private static final String Red = "#ff0000";
    private static final String Yellow = "#fffd00";


    /**
     * Constructor for a game.
     * @param board_ui The board UI.
     */
    public Game(Board_UI board_ui) {
        this.board_ui = board_ui;
        board = Board.getInstance();
        playerRed = new Human(Color.RED);
        playerYellow = new Human(Color.YELLOW);
        turn = 0;
    }


    /**
     * Starts the game.
     */
    public void start(){
        MillDetector millDetector = MillDetector.getInstance();
        System.out.println("Game started");

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
                    //Highlight circle in the UI
                    board_ui.highlightPosition(selectedPos1.getX(), selectedPos1.getY());

                    System.out.println("Selected token at " + selectedPos1);
                }

                latch = new CountDownLatch(1);

                Position selectedPos2 = getClickedPosition();

                MoveAction moveAction = new MoveAction(selectedToken, selectedPos1, selectedPos2);
                moveMade = moveAction.execute(board);
                moveMadePos = selectedPos2;

                //Unhighlight circle in the UI
                board_ui.unhighlightPosition(selectedPos1.getX(), selectedPos1.getY());

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
                    //Highlight circle in the UI
                    board_ui.highlightPosition(selectedPos1.getX(), selectedPos1.getY());
                    System.out.println("Selected token at " + selectedPos1);
                }

                latch = new CountDownLatch(1);

                Position selectedPos2 = getClickedPosition();

                FlyAction flyAction = new FlyAction(selectedToken, selectedPos1, selectedPos2);
                moveMade = flyAction.execute(board);
                moveMadePos = selectedPos2;

                //Unhighlight circle in the UI
                board_ui.unhighlightPosition(selectedPos1.getX(), selectedPos1.getY());
            }

            updatePlayerState();

            // Update the board UI
            updateBoardUI();

            // If a move was made, check if a mill was formed
            if (moveMade) {
                System.out.println("token placed/moved at " + moveMadePos);
                if (millDetector.isMill(moveMadePos)) {
                    System.out.println("Mill formed");
                    boolean removeMade = false;

                    while (!removeMade) {
                        System.out.println("Select a token to remove");
                        latch = new CountDownLatch(1);
                        Position selectedPos = getClickedPosition();
                        RemoveAction removeAction = new RemoveAction(opponent, selectedPos);
                        removeMade = removeAction.execute(board);
                    }

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

}
