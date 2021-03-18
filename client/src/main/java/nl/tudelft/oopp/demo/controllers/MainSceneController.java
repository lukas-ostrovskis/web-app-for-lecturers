package nl.tudelft.oopp.demo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.views.MenuDisplay;

import java.io.IOException;

public class MainSceneController {

    @FXML
    private TextField roomID;

    private static String roomId;

    public static String getRoomId() {
        return roomId;
    }

    /**
     * Handles clicking the button.
     */
    public void buttonClicked() {
        String result = ServerCommunication.getRoomId();
        roomId = result;
        roomID.setText(result);
        if(ServerCommunication.joinRoom(result).equals("Joined successfully!")) {
            try{
                roomScene();
            } catch(Exception e) {
                System.out.println("Can't load scene");
            }
        }
    }

    public void joinClicked() {
        roomId = roomID.getText();
        if(ServerCommunication.joinRoom(roomId).equals("Joined successfully!")) {
            try{
                roomScene();
            } catch(Exception e) {
                System.out.println("Can't load scene");
            }
        }

    }


    private void roomScene() throws IOException {
        Stage primaryStage = MenuDisplay.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("/roomScene.fxml"));
        primaryStage.getScene().setRoot(root);

        System.out.println("Scene loaded");
    }
}
