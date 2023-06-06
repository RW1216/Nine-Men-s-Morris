package com.corgi.ninemensmorris;

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

    public void btnPlayAgainstPlayerClicked(ActionEvent actionEvent) throws IOException {
        //Load the UI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardUI.fxml"));
        Parent boardUI = loader.load();

        //Update the title
        BoardUI boardUI1 = loader.getController();
        boardUI1.setTitle("Player vs Player");
        boardUI1.setMode(1);

        //Set scene
        Scene scene = rootPane.getScene();
        scene.setRoot(boardUI);
    }

    public void btnPlayAgainstAIClicked(ActionEvent actionEvent) throws IOException {
        //Load the UI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayAgainstAIUI.fxml"));
        Parent boardUI = loader.load();

        //Set scene
        Scene scene = rootPane.getScene();
        scene.setRoot(boardUI);
    }

    public void btnTutorialClicked(ActionEvent actionEvent) throws IOException {
        //Load the UI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardUI.fxml"));
        Parent boardUI = loader.load();

        //Update the title
        BoardUI boardUI1 = loader.getController();
        boardUI1.setTitle("Tutorial Mode");
        boardUI1.setMode(0);

        //Set scene
        Scene scene = rootPane.getScene();
        scene.setRoot(boardUI);
    }
}