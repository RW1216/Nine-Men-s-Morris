package src.Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Main_UI {
    @FXML
    private Label welcomeText;

    @FXML
    private AnchorPane rootPane;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    void btnStartClicked(ActionEvent event) throws IOException {
        System.out.println("btnStartClicked");

        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Board_UI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/

        AnchorPane pane = FXMLLoader.load(getClass().getResource("Board_UI.fxml"));
        rootPane.getChildren().setAll(pane);

    }
}