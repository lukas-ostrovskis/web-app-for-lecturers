package nl.tudelft.oopp.demo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import java.io.IOException;
import nl.tudelft.oopp.demo.views.MenuDisplay;

public class MainSceneController {

    @FXML
    private TextField roomID;

    private static String roomId;

    public static String getRoomId() {
        return roomId;
    }

    /**
     * Clicking the 'create room' button sends a request to the server to create a new room.
     */
    public void buttonClicked() {
        String result = ServerCommunication.getRoomId();
        roomId = result;
        roomID.setText(result);
    }

}
