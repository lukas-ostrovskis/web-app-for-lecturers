package nl.tudelft.oopp.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import nl.tudelft.oopp.app.communication.ServerCommunication;
import nl.tudelft.oopp.app.views.MainView;

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
    public void leaveRoomButtonPressed() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
        Scene mainScene = new Scene(root, 400, 400);

        MainView.getPrimaryStage().setScene(mainScene);

        MainView.getPrimaryStage().centerOnScreen();
    }

    @FXML
    public void endLectureButtonPressed() {

        // Delete room from server
        if (ServerCommunication.deleteRoom()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Room successfully deleted.");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Something went wrong when deleting room.");
            alert.showAndWait();
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
