package nl.tudelft.oopp.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RoomSceneController {

    @FXML
    private TextField roomID;

    public void initialize() {
        roomID.setText(MainSceneController.getRoomId());
    }
}
