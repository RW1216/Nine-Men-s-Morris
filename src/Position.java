package src;

import java.util.ArrayList;

public class Position {
    private boolean hasToken;
    private final int x;
    private final int y;
    private Token occupyingToken;
    private ArrayList<Position> adjacentPositions;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.hasToken = false;
        this.occupyingToken = null;
        adjacentPositions = new ArrayList<>();
    }

    public boolean hasToken() {
        return hasToken;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Token getOccupyingToken() {
        return occupyingToken;
    }

    public void removeToken() {
        this.hasToken = false;
        this.occupyingToken = null;
    }

    public void placeToken(Token token) {
        if (this.hasToken) {
            System.out.println("There is already a token on this position");
        } else {
            this.hasToken = true;
            this.occupyingToken = token;
        }
    }

    public ArrayList<Position> getAdjacentPositions() {
        return adjacentPositions;
    }

    public void addAdjacentPosition(Position pos) {
        if (!adjacentPositions.contains(pos)) {
            adjacentPositions.add(pos);
        } else {
            System.out.println("Position already adjacent");
        }
    }

    public boolean isAdjacentTo(Position position) {
        return adjacentPositions.contains(position);
    }

    public String toString() {
        return "Position: " + x + ", " + y;
    }
}
