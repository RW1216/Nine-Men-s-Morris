package src;

import src.Display.Board_UI;
import src.Players.*;

import java.util.concurrent.CountDownLatch;

public class Game {

    private PlayerState currentPhase;
    private int turn;
    private Player currentPlayer;
    private Player opponent;
    private Player playerRed;
    private Player playerYellow;
    private final Board board;
    private Board_UI board_ui;
    CountDownLatch latch;
    private Token selectedToken;

    private static final String White = "#ffffff";
    private static final String Red = "#ff0000";
    private static final String Yellow = "#fffd00";

    public Game(Board_UI board_ui) {
        this.board_ui = board_ui;
        board = Board.getInstance();
        playerRed = new Human("Red");
        playerYellow = new Human("Yellow");
        turn = 0;
    }

    public void start(){
        System.out.println("Game started");

        while (gameActive()) {
            //Initialize CountDownLatch
            latch = new CountDownLatch(1);

            if (turn % 2 == 0) {
                currentPlayer = playerRed;
                opponent = playerYellow;

                currentPhase = currentPlayer.getPlayerState();
                System.out.println("Red's turn");

            } else {
                currentPlayer = playerYellow;
                opponent = playerRed;

                currentPhase = currentPlayer.getPlayerState();
                System.out.println("Yellow's turn");
            }



            // PLACING PHASE
            if (currentPhase instanceof PlacingState) {
                System.out.println("Placing phase!");

                Position selectedPosition = getClickedPosition();
                // If the selected token is not null, place it on the board
                if (!selectedPosition.hasToken()) {
                    Token newToken = new Token(currentPlayer);
                    currentPlayer.addToken(newToken);
                    board.placeToken(newToken, selectedPosition);
                    System.out.println("Placed token at " + selectedPosition + " for " + currentPlayer.getTokenColor());
                    turn++;
                } else {
                    System.out.println("Invalid position");
                }


                // MOVING PHASE
            } else if (currentPhase instanceof MovingState) {
                System.out.println("Moving phase!");

                Position selectedPosition = getClickedPosition();
                selectedToken = selectedPosition.getOccupyingToken();
                if (selectedToken == null || selectedToken.owner != currentPlayer) {
                    System.out.println("Please select your token");
                    continue;
                } else {
                    System.out.println("Selected token at " + selectedPosition);
                }

                latch = new CountDownLatch(1);

                try {
                    latch.await();

                } catch (InterruptedException e){
                    e.printStackTrace();
                }


                Position selectedPosition2 = getClickedPosition();

                if (selectedPosition2.hasToken()) {
                    System.out.println("Invalid position");
                    continue;
                } else {
                    boolean suc = board.moveToken(selectedToken, selectedPosition, selectedPosition2);
                    if (suc) {
                        System.out.println("Moved token from " + selectedPosition + " to " + selectedPosition2);
                        turn++;
                    } else {
                        System.out.println("Invalid move");
                    }
                }
            }

            updatePlayerState();

            // Update the board UI
            updateBoardUI();
        }
    }

    private void updateBoardUI(){
        for (int i = 0; i < board.getPositions().length; i++) {
            for (int j = 0; j < board.getPositions()[i].length; j++) {
                if (board.getPosition(i, j) != null){
                    Token token = board.getPosition(i, j).getOccupyingToken();
                    if (token == null){
                        board_ui.updatePositionFill(i, j, White);
                    }
                    else if (token.owner.getTokenColor().equals("Red")){
                        board_ui.updatePositionFill(i, j, Red);
                    }
                    else if (token.owner.getTokenColor().equals("Yellow")){
                        board_ui.updatePositionFill(i, j, Yellow);
                    }
                }
            }
        }
    }

/*    public static void main(String[] args) {
        Board board = Board.getInstance();

        Game game = new Game(board);
//        game.start();
        board.printBoard();
    }*/

    public void positionSelected(){
        // If a latch exists, count down by 1 to release the blocked thread
        if (latch != null) {
            latch.countDown();
        }
    }

    public Board getBoard() {
        return board;
    }

    public boolean gameActive() {
//        todo: check if game is active
        return true;
    }

    public void updatePlayerState() {
        playerRed.updateSelfState();
        playerYellow.updateSelfState();
    }

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
