package nl.tudelft.oopp.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import nl.tudelft.oopp.app.views.MainView;

public class RoomViewController {

    @FXML
    private ListView<String> questionsListView;

    /**
     * Handles pressing the leaveRoomButton
     * Loads the menu layout into the scene, passes that scene into the stage.
     */
    @FXML
    public void leaveRoomButtonPressed() throws Exception {
        /**
         * Create a new scene and load the layout from MainView.fxml into the scene graph.
         */
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
        Scene mainScene = new Scene(root, 400, 400);

        /**
         * Load the roomScene into the primaryStage of MainView.
         */
        MainView.getPrimaryStage().setScene(mainScene);

        /**
         * Recenters the stage on screen.
         */
        MainView.getPrimaryStage().centerOnScreen();
    }

    @FXML
    public void endLectureButtonPressed() throws Exception {
        /**
         * Create a new scene and load the layout from MainView.fxml into the scene graph.
         */
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
        Scene mainScene = new Scene(root, 400, 400);

        /**
         * Load the roomScene into the primaryStage of MainView.
         */
        MainView.getPrimaryStage().setScene(mainScene);

        /**
         * Recenters the stage on screen.
         */
        MainView.getPrimaryStage().centerOnScreen();
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
