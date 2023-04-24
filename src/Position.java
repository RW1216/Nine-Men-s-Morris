package src;

public class Position {
    private boolean hasToken;
    private final int x;
    private final int y;
    private src.Token occupyingToken;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.hasToken = false;
        this.occupyingToken = null;
    }

    public boolean hasToken() {
        return hasToken;
    }

    public void setHasToken(boolean hasToken) {
        this.hasToken = hasToken;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public src.Token getOccupyingToken() {
        return occupyingToken;
    }

    public void setOccupyingToken(src.Token occupyingToken) {
        this.occupyingToken = occupyingToken;
    }

    public void removeToken() {
        this.hasToken = false;
        this.occupyingToken = null;
    }

    public void placeToken(src.Token token) {
        if (this.hasToken) {
            System.out.println("There is already a token on this position");
        } else {
            this.hasToken = true;
            this.occupyingToken = token;
        }
    }

    public String toString() {
        return "Position: " + x + ", " + y;
    }
}
