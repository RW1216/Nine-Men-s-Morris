package src.Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import src.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class Board_UI implements Initializable {
    //UI Position
    @FXML private Circle Pos_1;
    @FXML private Circle Pos_2;
    @FXML private Circle Pos_3;
    @FXML private Circle Pos_4;
    @FXML private Circle Pos_5;
    @FXML private Circle Pos_6;
    @FXML private Circle Pos_7;
    @FXML private Circle Pos_8;
    @FXML private Circle Pos_9;
    @FXML private Circle Pos_10;
    @FXML private Circle Pos_11;
    @FXML private Circle Pos_12;
    @FXML private Circle Pos_13;
    @FXML private Circle Pos_14;
    @FXML private Circle Pos_15;
    @FXML private Circle Pos_16;
    @FXML private Circle Pos_17;
    @FXML private Circle Pos_18;
    @FXML private Circle Pos_19;
    @FXML private Circle Pos_20;
    @FXML private Circle Pos_21;
    @FXML private Circle Pos_22;
    @FXML private Circle Pos_23;
    @FXML private Circle Pos_24;

    private Circle[][] UIPositions = new Circle[7][7];

    //Color variable
    private static final String White = "#ffffff";
    private static final String Red = "#ff0000";
    private static final String Yellow = "#fffd00";
    private static final String LightBlue = "#68aafc";
    private static final String Black = "#000000";

    private int selectedRow;
    private int selectedCol;
    private String positionColor;

    Game game;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new Game(this);

        // Row 0
        UIPositions[0][0] = Pos_1;
        UIPositions[0][3] = Pos_2;
        UIPositions[0][6] = Pos_3;

        // Row 1
        UIPositions[1][1] = Pos_4;
        UIPositions[1][3] = Pos_5;
        UIPositions[1][5] = Pos_6;

        // Row 2
        UIPositions[2][2] = Pos_7;
        UIPositions[2][3] = Pos_8;
        UIPositions[2][4] = Pos_9;

        // Row 3
        UIPositions[3][0] = Pos_10;
        UIPositions[3][1] = Pos_11;
        UIPositions[3][2] = Pos_12;
        UIPositions[3][4] = Pos_13;
        UIPositions[3][5] = Pos_14;
        UIPositions[3][6] = Pos_15;

        // Row 4
        UIPositions[4][2] = Pos_16;
        UIPositions[4][3] = Pos_17;
        UIPositions[4][4] = Pos_18;

        // Row 5
        UIPositions[5][1] = Pos_19;
        UIPositions[5][3] = Pos_20;
        UIPositions[5][5] = Pos_21;

        // Row 6
        UIPositions[6][0] = Pos_22;
        UIPositions[6][3] = Pos_23;
        UIPositions[6][6] = Pos_24;

        //Start the game
        Thread gameThread = new Thread(() -> {
            game.start();
        });
        gameThread.start();
    }

    @FXML
    void positionClicked(MouseEvent event) {
        Circle clickedCircle = (Circle) event.getSource();

        for (int i = 0; i < UIPositions.length; i++){
            for (int j = 0; j < UIPositions[i].length; j++){
                if (UIPositions[i][j] != null){
                    if (UIPositions[i][j].equals(clickedCircle)){
                        selectedRow = i;
                        selectedCol = j;
                    }
                }
            }
        }

        game.positionSelected();
    }

    public int getSelectedRow(){
        return selectedRow;
    }

    public int getSelectedCol(){
        return selectedCol;
    }

    public void updatePositionFill(int row, int column, String color){
        UIPositions[row][column].setFill(Paint.valueOf(color));
    }

    @FXML
    void btnTestingClicked(ActionEvent event) {
        Pos_1.setStroke(Paint.valueOf(LightBlue));
    }

    @FXML
    void btnTesting2Clicked(ActionEvent event) throws InterruptedException {

    }

    public void pickPosition(){
        //Enable all circles
    }

    public void disableCircle(){
        //Disable all circles
    }
}
