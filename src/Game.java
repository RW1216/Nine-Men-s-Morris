package src;

import src.Display.Board_UI;

import java.util.concurrent.CountDownLatch;

public class Game {

    private final int PLACING = 0;
    private final int MOVING = 1;
    private final int FLYING = 2;
    private final int PLAYERRED = 0;
    private final int PLAYERYELLOW = 1;

    private int currentPhase;
    private int turn;
    private int currentPlayer;

    private final Board board;
    private Board_UI board_ui;
    CountDownLatch latch = new CountDownLatch(1);

    public Game(Board_UI board_ui) {
        this.board_ui = board_ui;
        board = Board.getInstance();

        currentPhase = PLACING;
        turn = 0;
    }

    public void start(){
        System.out.println("Game started");
        int selectedPosition;

        while (gameActive()) {
            //Initialize CountDownLatch
            latch = new CountDownLatch(1);

            if (turn % 2 == 0) {
                currentPlayer = PLAYERRED;
                System.out.println("red's turn");

                //Wait until board picks its position
                try {
                    latch.await();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }


            } else {
                currentPlayer = PLAYERYELLOW;
                System.out.println("yellow's turn");

                //Wait until board picks its position
                try {
                    latch.await();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
            //Get the selected position
            selectedPosition = board_ui.getSelectedPosition();

            System.out.println(selectedPosition);

            turn++;
        }


        /*while (gameActive()) {
            if (turn % 2 == 0) {
                currentPlayer = PLAYERRED;
                System.out.println("red's turn");

            } else {
                currentPlayer = PLAYERYELLOW;
                System.out.println("yellow's turn");
            }

            if (currentPhase == PLACING) {
                System.out.println("Placing phase");
//                todo: place token action

                currentPhase = MOVING;
            } else if (currentPhase == MOVING) {
                System.out.println("Moving phase");
//                todo: move token action

                currentPhase = FLYING;
            } else if (currentPhase == FLYING) {
                System.out.println("Flying phase");
//                todo: fly token
                currentPhase = PLACING;
            }

            turn++;
        }*/
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

    public int getCurrentPhase() {
        return currentPhase;
    }

    public boolean gameActive() {
//        todo: check if game is active
        return true;
    }

    public void printTesting(){
        System.out.println("TESTING");
    }

}
