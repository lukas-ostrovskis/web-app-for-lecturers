package nl.tudelft.oopp.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import nl.tudelft.oopp.app.communication.ServerCommunication;
import nl.tudelft.oopp.app.views.MainView;

import java.io.IOException;

public class RoomViewController {

    @FXML
    private ListView<String> questionsListView;

    @FXML
    private Label identityLabel;

    /**
     * Handles pressing the leaveRoomButton
     * Loads the menu layout into the scene, passes that scene into the stage.
     */
    @FXML
    public void leaveRoomButtonPressed() {

        try {

            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
            Scene mainScene = new Scene(root, 400, 400);
            MainView.getPrimaryStage().setScene(mainScene);
            MainView.getPrimaryStage().centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void endLectureButtonPressed() {

        try {

            // Delete room from server
            ServerCommunication.deleteRoom();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Room successfully deleted.");
            alert.showAndWait();

            leaveRoomButtonPressed();

        } catch (ServerCommunication.RoomNotDeletedException e) {

            // Alert user room wasn't deleted
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void askQuestionButtonPressed() {
        questionsListView.getItems().add("Test " + (int) (Math.random() * 100));
    }
    @FXML
    public void removeQuestionButtonPressed() {
        int selectedIndex = questionsListView.getSelectionModel().getSelectedIndex();
        questionsListView.getItems().remove(selectedIndex);
    }
}
