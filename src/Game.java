package src;

public class Game {

    private final int PLACING = 0;
    private final int MOVING = 1;
    private final int FLYING = 2;

    private int currentPhase;

    private Board board;

    public Game(Board board) {
        this.board = board;
        currentPhase = PLACING;
    }

    public void start() {
        System.out.println("Game started");

        while (gameActive()) {
            if (currentPhase == PLACING) {
                System.out.println("Placing phase");
                currentPhase = MOVING;
            } else if (currentPhase == MOVING) {
                System.out.println("Moving phase");
                currentPhase = FLYING;
            } else if (currentPhase == FLYING) {
                System.out.println("Flying phase");
                currentPhase = PLACING;
            }
        }
    }

    public static void main(String[] args) {
        Board board = Board.getInstance();

        Game game = new Game(board);
        game.start();
    }

    public Board getBoard() {
        return board;
    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    private void setCurrentPhase(int currentPhase) {
        this.currentPhase = currentPhase;
    }

    private boolean gameActive() {
        return true;
    }
}
