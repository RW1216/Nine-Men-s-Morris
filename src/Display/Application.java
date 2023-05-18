package src.Display;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * The driver class to start and run the game
 *
 */

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts after running this application. It creates and shows the main UI of the Nine Men's Morris.
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainUI.class.getResource("MainUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 660, 700);
        Stage stage = new Stage();
        stage.setTitle("Nine Men's Morris");
        stage.setScene(scene);
        stage.show();
    }
}