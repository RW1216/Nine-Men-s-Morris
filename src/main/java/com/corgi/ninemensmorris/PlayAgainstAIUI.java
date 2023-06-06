package com.corgi.ninemensmorris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PlayAgainstAIUI {

    @FXML
    private AnchorPane rootPane;

    public void btnStartAsRedClicked(ActionEvent actionEvent) {
    }

    public void btnStartAsYellowClicked(ActionEvent actionEvent) {
    }

    public void btnStartAsRandomClicked(ActionEvent actionEvent) {
    }

    public void btnBackClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
        Parent boardUI = loader.load();

        Scene scene = rootPane.getScene();
        scene.setRoot(boardUI);
    }
}
