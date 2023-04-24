package src.Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import src.Board;
import src.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class Board_UI implements Initializable {
    //UI Position
    @FXML private Circle Pos_1;
    @FXML private Circle Pos_2;
    @FXML private Circle Pos_3;
    Circle[] UIPosition = new Circle[3];

    //Color variable
    private static final String White = "#ffffff";
    private static final String Red = "#ff0000";
    private static final String Yellow = "#fffd00";
    private static final String LightBlue = "#68aafc";
    private static final String Black = "#000000";

    private int turn = 0;
    Game game;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new Game(this);
        //game.start();

        UIPosition[0] = Pos_1;
        UIPosition[1] = Pos_2;
        UIPosition[2] = Pos_3;

        UpdateAllowedMove();
    }

    @FXML
    void positionClicked(MouseEvent event) {
        Circle circle = (Circle) event.getSource();

        if (turn <= 18){
            //Check if the position is empty
            if (circle.getFill().equals(Paint.valueOf(White))){
                //Even number, Red
                if (turn % 2 == 0){
                    circle.setFill(Paint.valueOf(Red));
                }
                //Odd number, Yellow
                else{
                    circle.setFill(Paint.valueOf(Yellow));
                }

                turn += 1;
            }
        }

        UpdateAllowedMove();
    }

    private void UpdateAllowedMove(){
        for (Circle circle: UIPosition){
            /*//If the circle color is red or yellow
            if (circle.getStroke().equals(Paint.valueOf(Red)) || circle.getStroke().equals(Paint.valueOf(Yellow))){
                circle.setStroke(Paint.valueOf(Black));
            }
            else{
                circle.setStroke(Paint.valueOf(LightBlue));
            }
            //System.out.println(circle.getStroke().toString());*/
            circle.setStroke(Paint.valueOf(Black));
        }
    }

    public void chooseCircle(){
        //Enable all circles
        for (Circle circle: UIPosition){
            circle.setDisable(false);
        }
    }
    
    public void disableCircle(){
        //Disable all circles
        for (Circle circle: UIPosition){
            circle.setDisable(true);
        }
    }

    @FXML
    void btnTestingClicked(ActionEvent event) {
        Pos_1.setStroke(Paint.valueOf(LightBlue));
    }

    @FXML
    void btnTesting2Clicked(ActionEvent event) {
        game.printTesting();
    }
}
