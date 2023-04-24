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
    Circle[] UIPosition = new Circle[3];

    //Color variable
    private static final String White = "#ffffff";
    private static final String Red = "#ff0000";
    private static final String Yellow = "#fffd00";
    private static final String LightBlue = "#68aafc";
    private static final String Black = "#000000";

    private int selectedPosition;

    Game game;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new Game(this);

        selectedPosition = -1;
        UIPosition[0] = Pos_1;
        UIPosition[1] = Pos_2;
        UIPosition[2] = Pos_3;
    }

    @FXML
    void positionClicked(MouseEvent event) {
        Circle clickedCircle = (Circle) event.getSource();
        selectedPosition = Integer.valueOf(clickedCircle.getId());
        game.positionSelected();
    }

    public void pickPosition(){
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

    public int getSelectedPosition(){
        return selectedPosition;
    }

    @FXML
    void btnTestingClicked(ActionEvent event) {
        Pos_1.setStroke(Paint.valueOf(LightBlue));
    }

    @FXML
    void btnTesting2Clicked(ActionEvent event) throws InterruptedException {
        Thread gameThread = new Thread(() -> {
            game.start();
        });
        gameThread.start();
    }
}
