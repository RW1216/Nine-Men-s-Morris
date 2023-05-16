package src.Display;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main_UI.class.getResource("Main_UI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 660, 700);
        Stage stage = new Stage();
        stage.setTitle("Nine Men's Morris");
        stage.setScene(scene);
        stage.show();
    }
}