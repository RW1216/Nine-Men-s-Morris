package src.Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainUI {

    @FXML
    private AnchorPane rootPane;

    /**
     * This method will be invoked when player presses the start button.
     * @param event
     * @throws IOException
     */
    @FXML
    void btnStartClicked(ActionEvent event) throws IOException {
        System.out.println("btnStartClicked");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardUI.fxml"));
        Parent boardUI = loader.load();

        Scene scene = rootPane.getScene();
        scene.setRoot(boardUI);
    }

}