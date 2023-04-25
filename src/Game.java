package src;

import src.Display.Board_UI;
import src.Players.Human;
import src.Players.PlacingState;
import src.Players.Player;
import src.Players.PlayerState;

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
        int selectedCol;
        int selectedRow;

        while (gameActive()) {
            //Initialize CountDownLatch
            latch = new CountDownLatch(1);

            if (turn % 2 == 0) {
                currentPlayer = playerRed;
                opponent = playerYellow;

                currentPhase = currentPlayer.getPlayerState();
                System.out.println("Red's turn");

                //Wait until board picks its position
                try {
                    latch.await();

                } catch (InterruptedException e){
                    e.printStackTrace();
                }


            } else {
                currentPlayer = playerYellow;
                opponent = playerRed;
                System.out.println("Yellow's turn");

                //Wait until board picks its position
                try {
                    latch.await();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
            //Get the selected row
            selectedRow = board_ui.getSelectedRow();
            selectedCol = board_ui.getSelectedCol();

            System.out.println("Selected row: " + selectedRow + " Selected col: " + selectedCol);

            // find the token that is in the selected position
            selectedToken = board.getToken(selectedRow, selectedCol);

//            System.out.println("Selected token: " + selectedToken);
            if (currentPhase instanceof PlacingState) {
                // If the selected token is not null, place it on the board
                if (selectedToken == null) {
                    Position selectedPosition = board.getPosition(selectedRow, selectedCol);
                    Token newToken = new Token(currentPlayer);
                    board.placeToken(newToken, selectedPosition);
                    System.out.println("Placed token at " + selectedPosition + " for " + currentPlayer.getTokenColor());
                    turn++;
                } else {
                    System.out.println("Invalid position");
                }
            }

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

    public void printTesting(){
        System.out.println("TESTING");
    }

}
