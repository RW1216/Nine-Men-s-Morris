package src;

import java.util.ArrayList;

public class Board {
    private static Board instance = null;
    protected ArrayList<Token> tokens = new ArrayList<Token>();
    private Position[][] positions = new Position[7][7];

    /**
     * Constructor for the board. Creates the board and populates
     * it with positions, then connects all adjacent positions.
     */
    private Board() {
        // Row 0
        positions[0][0] = new Position(0, 0);
        positions[0][3] = new Position(0, 3);
        positions[0][6] = new Position(0, 6);

        // Row 1
        positions[1][1] = new Position(1, 1);
        positions[1][3] = new Position(1, 3);
        positions[1][5] = new Position(1, 5);

        // Row 2
        positions[2][2] = new Position(2, 2);
        positions[2][3] = new Position(2, 3);
        positions[2][4] = new Position(2, 4);

        // Row 3
        positions[3][0] = new Position(3, 0);
        positions[3][1] = new Position(3, 1);
        positions[3][2] = new Position(3, 2);
        positions[3][4] = new Position(3, 4);
        positions[3][5] = new Position(3, 5);
        positions[3][6] = new Position(3, 6);

        // Row 4
        positions[4][2] = new Position(4, 2);
        positions[4][3] = new Position(4, 3);
        positions[4][4] = new Position(4, 4);

        // Row 5
        positions[5][1] = new Position(5, 1);
        positions[5][3] = new Position(5, 3);
        positions[5][5] = new Position(5, 5);

        // Row 6
        positions[6][0] = new Position(6, 0);
        positions[6][3] = new Position(6, 3);
        positions[6][6] = new Position(6, 6);

        // Connect adjacent positions
        connectPositions(positions[0][0], positions[0][3]);
        connectPositions(positions[0][0], positions[3][0]);
        connectPositions(positions[0][3], positions[0][6]);
        connectPositions(positions[0][3], positions[1][3]);
        connectPositions(positions[0][6], positions[3][6]);
        connectPositions(positions[1][1], positions[1][3]);
        connectPositions(positions[1][1], positions[3][1]);
        connectPositions(positions[1][3], positions[1][5]);
        connectPositions(positions[1][3], positions[2][3]);
        connectPositions(positions[1][5], positions[3][5]);
        connectPositions(positions[2][2], positions[2][3]);
        connectPositions(positions[2][2], positions[3][2]);
        connectPositions(positions[2][3], positions[2][4]);
        connectPositions(positions[2][4], positions[3][4]);
        connectPositions(positions[3][0], positions[3][1]);
        connectPositions(positions[3][0], positions[6][0]);
        connectPositions(positions[3][1], positions[3][2]);
        connectPositions(positions[3][1], positions[5][1]);
        connectPositions(positions[3][2], positions[3][4]);
        connectPositions(positions[3][2], positions[4][2]);
        connectPositions(positions[3][4], positions[3][5]);
        connectPositions(positions[3][4], positions[4][4]);
        connectPositions(positions[3][5], positions[3][6]);
        connectPositions(positions[3][5], positions[5][5]);
        connectPositions(positions[4][2], positions[4][3]);
        connectPositions(positions[4][2], positions[5][3]);
        connectPositions(positions[4][3], positions[4][4]);
        connectPositions(positions[4][3], positions[5][3]);
        connectPositions(positions[5][1], positions[5][3]);
        connectPositions(positions[5][1], positions[6][3]);
        connectPositions(positions[5][3], positions[5][5]);
        connectPositions(positions[5][3], positions[6][3]);
        connectPositions(positions[6][0], positions[6][3]);
        connectPositions(positions[6][3], positions[6][6]);
    }

    public Position[][] getPositions() {
        return positions;
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }

        return instance;
    }

    public Position getPosition(int x, int y) {
        return positions[x][y];
    }

    public ArrayList<Position> getEmptyPositions() {
        ArrayList<Position> emptyPositions = new ArrayList<Position>();
        for (Position[] position : positions) {
            for (Position value : position) {
                if (value != null && !value.hasToken()) {
                    emptyPositions.add(value);
                }
            }
        }
        return emptyPositions;
    }

    public Token getToken(int x, int y) {
        return positions[x][y].getOccupyingToken();
    }

    public Token getToken(Position position) {
        return position.getOccupyingToken();
    }

    public void placeToken(Token token, Position position) {
        position.placeToken(token);
        tokens.add(token);
    }

    public boolean moveToken(Token token, Position pos1, Position pos2) {
        if (pos1.getOccupyingToken() == token && isPositionEmpty(pos2)) {
            pos1.removeToken();
            pos2.placeToken(token);
            return true;
        }
        return false;
    }

    private void connectPositions(Position pos1, Position pos2) {
        pos1.addAdjacentPosition(pos2);
        pos2.addAdjacentPosition(pos1);
    }

    public boolean isPositionEmpty(Position position) {
        return position.getOccupyingToken() == null;
    }

    public void printBoard() {
        for (Position[] position : positions) {
            for (Position value : position) {
                if (value != null) {
                    System.out.print("0");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
}
