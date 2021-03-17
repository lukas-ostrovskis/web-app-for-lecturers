package nl.tudelft.oopp.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.data.User;

import java.util.List;

public class UserSceneController {

    @FXML
    private TextField searchOrAdd;

    @FXML
    private ListView<User> userList;

    /**
     * Handles clicking the button.
     */
    public void buttonClicked() {
        List<User> result = ServerCommunication.findUsers(searchOrAdd.getText());
        System.out.println(result);
        userList.getItems().clear();
        userList.getItems().addAll(result);

    }
}
