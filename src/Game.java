package src;

public class Game {

    private final int PLACING = 0;
    private final int MOVING = 1;
    private final int FLYING = 2;
    private final int PLAYERRED = 0;
    private final int PLAYERYELLOW = 1;

    private int currentPhase;
    private int turn;
    private int currentPlayer;

    private Board board;

    public Game(Board board) {
        this.board = board;
        currentPhase = PLACING;
        turn = 0;
    }

    private void start() {
        System.out.println("Game started");

        while (gameActive()) {
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
        }
    }

    public static void main(String[] args) {
        Board board = Board.getInstance();

        Game game = new Game(board);
        game.start();
        board.printBoard();
    }

    public Board getBoard() {
        return board;
    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    private boolean gameActive() {
//        todo: check if game is active
        return true;
    }
}
