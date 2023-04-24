package src;

import src.Display.Board_UI;

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

    public Game(Board_UI board_ui) {
        this.board_ui = board_ui;
        board = Board.getInstance();

        currentPhase = PLACING;
        turn = 0;
    }

    public void start() {
        System.out.println("Game started");
        int position;

        while (gameActive()) {
            if (turn % 2 == 0) {
                currentPlayer = PLAYERRED;
                System.out.println("red's turn");

                board_ui.chooseCircle();
                //Wait until board_ui picks its position
                while(board_ui.selectingStatus()){

                }
                //Get the picked position
                position = board_ui.getPickedPosition();

            } else {
                currentPlayer = PLAYERYELLOW;
                System.out.println("yellow's turn");

                board_ui.chooseCircle();
                //Wait until board_ui picks its position
                while(board_ui.selectingStatus()){

                }
                //Get the picked position
                position = board_ui.getPickedPosition();
            }

            System.out.println(position);

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
