package src;

import src.Actions.FlyAction;
import src.Actions.MoveAction;
import src.Actions.PlaceAction;
import src.Actions.RemoveAction;
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
        playerRed = new Human(Color.RED);
        playerYellow = new Human(Color.YELLOW);
        turn = 0;
    }

    public void start(){
        MillDetector millDetector = MillDetector.getInstance();
        System.out.println("Game started");

        while (gameActive()) {
            //Initialize CountDownLatch
            latch = new CountDownLatch(1);
            boolean moveMade = false;
            Position moveMadePos = null;

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

            // PLACING PHASE =====================================================
            if (currentPhase == PlayerState.Placing) {
                System.out.println("Select a position to place your token");

                Position selectedPos = getClickedPosition();

                PlaceAction placeAction = new PlaceAction(currentPlayer, selectedPos);
                moveMade = placeAction.execute(board);
                moveMadePos = selectedPos;

            // MOVING PHASE =====================================================
            } else if (currentPhase == PlayerState.Moving) {
                System.out.println("Select a token to move");

                Position selectedPos1 = getClickedPosition();
                selectedToken = selectedPos1.getOccupyingToken();
                if (selectedToken == null || selectedToken.getOwner() != currentPlayer) {
                    System.out.println("Please select your token");
                    System.out.println("Selected token at " + selectedPos1);
                    continue;
                } else {
                    System.out.println("Selected token at " + selectedPos1);
                }

                latch = new CountDownLatch(1);

                Position selectedPos2 = getClickedPosition();

                MoveAction moveAction = new MoveAction(selectedToken, selectedPos1, selectedPos2);
                moveMade = moveAction.execute(board);
                moveMadePos = selectedPos2;
            } else if (currentPhase == PlayerState.Flying) {
                System.out.println("Select a token to fly");

                Position selectedPos1 = getClickedPosition();
                selectedToken = selectedPos1.getOccupyingToken();
                if (selectedToken == null || selectedToken.getOwner() != currentPlayer) {
                    System.out.println("Please select your token");
                    System.out.println("Selected token at " + selectedPos1);
                    continue;
                } else {
                    System.out.println("Selected token at " + selectedPos1);
                }

                latch = new CountDownLatch(1);

                Position selectedPos2 = getClickedPosition();

                FlyAction flyAction = new FlyAction(selectedToken, selectedPos1, selectedPos2);
                moveMade = flyAction.execute(board);
                moveMadePos = selectedPos2;
            }

            updatePlayerState();

            // Update the board UI
            updateBoardUI();

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

                    updatePlayerState();
                    updateBoardUI();
                }
                turn++;
                System.out.println("Player Red tokens: " + playerRed.getTokenCount() + " Player Yellow tokens: " + playerYellow.getTokenCount());
            }
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
