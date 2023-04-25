package src;

import java.util.ArrayList;

public class Board {
    private static Board instance = null;
    protected ArrayList<Token> tokens = new ArrayList<Token>();
    private Position[][] positions = new Position[7][7];

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

    public void placeToken(Token token, Position position) {
        position.setOccupyingToken(token);
        tokens.add(token);
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
