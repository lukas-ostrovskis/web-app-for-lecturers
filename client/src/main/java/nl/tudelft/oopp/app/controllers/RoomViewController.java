package nl.tudelft.oopp.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import nl.tudelft.oopp.app.views.MainView;

public class RoomViewController {

    /**
     * Handles pressing the leaveRoomButton
     * Loads the menu layout into the scene, passes that scene into the stage.
     */
    @FXML
    public void leaveRoomButtonPressed() throws Exception {
        /**
         * Create a new scene and load the layout from MenuView.fxml into the scene graph.
         */
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MenuView.fxml"));
        Scene menuScene = new Scene(root, 400, 400);

        /**
         * Load the menuScene into the primaryStage of MainView.
         */
        //MainView.getPrimaryStage().setScene(menuScene);
    }
}