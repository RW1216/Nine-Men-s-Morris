package com.corgi.ninemensmorris;

import com.corgi.ninemensmorris.Game.GameMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Random;

public class PlayAgainstAIUI {

    @FXML
    private AnchorPane rootPane;

    public void btnStartAsRedClicked(ActionEvent actionEvent) throws IOException {
        GameMode.getInstance().setStartAsRed(true);
        //Load the UI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardUI.fxml"));
        Parent boardUI = loader.load();
        //Update the title
        BoardUI boardUI1 = loader.getController();
        boardUI1.setTitle("Player vs AI");

        //Set scene
        Scene scene = rootPane.getScene();
        scene.setRoot(boardUI);
    }

    public void btnStartAsYellowClicked(ActionEvent actionEvent) throws IOException {
        GameMode.getInstance().setStartAsRed(false);
        //Load the UI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardUI.fxml"));
        Parent boardUI = loader.load();
        //Update the title
        BoardUI boardUI1 = loader.getController();
        boardUI1.setTitle("Player vs AI");

        //Set scene
        Scene scene = rootPane.getScene();
        scene.setRoot(boardUI);
    }

    public void btnStartAsRandomClicked(ActionEvent actionEvent) throws IOException {
        Random random = new Random();
        boolean randomBoolean = random.nextBoolean();

        //Update the GameMode
        GameMode.getInstance().setStartAsRed(randomBoolean);

        //Load the UI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardUI.fxml"));
        Parent boardUI = loader.load();
        //Update the title
        BoardUI boardUI1 = loader.getController();
        boardUI1.setTitle("Player vs AI");

        //Set scene
        Scene scene = rootPane.getScene();
        scene.setRoot(boardUI);
    }

    public void btnBackClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
        Parent boardUI = loader.load();

        Scene scene = rootPane.getScene();
        scene.setRoot(boardUI);
    }
}
