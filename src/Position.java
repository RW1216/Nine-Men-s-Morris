package src;

public class Position {
    private boolean hasToken;
    private int x;
    private int y;
//    private Token occupyingToken;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.hasToken = false;
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

//    public Token getOccupyingToken() {
//        return occupyingToken;
//    }

//    public void setOccupyingToken(Token occupyingToken) {
//        this.occupyingToken = occupyingToken;
//    }

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
}
