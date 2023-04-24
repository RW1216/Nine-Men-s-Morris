package src;

import java.util.ArrayList;

public class Board {
    private static Board instance = null;
    protected int turns;
//    protected ArrayList<src.Token> tokens = new ArrayList<src.Token>();

    private Board() {
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
            instance.turns = 0;
        }

        return instance;
    }

}
