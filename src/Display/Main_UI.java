package src.Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_UI {

    @FXML
    private AnchorPane rootPane;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void btnStartClicked(ActionEvent event) throws IOException {
        /*Parent root = FXMLLoader.load(getClass().getResource("Board_UI.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/

        System.out.println("btnStartClicked");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Board_UI.fxml"));
        Parent boardUI = loader.load();

        Scene scene = rootPane.getScene();
        scene.setRoot(boardUI);
    }

}