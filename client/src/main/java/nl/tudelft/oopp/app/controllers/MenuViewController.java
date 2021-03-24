package nl.tudelft.oopp.app.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import nl.tudelft.oopp.app.views.MainView;

public class MenuViewController {

    /**
     * Identity constants are used for loading the respective layouts for a student/moderator/lecturer.
     */
    public static final int IDENTITY_STUDENT = 1;
    public static final int IDENTITY_MODERATOR = 2;
    public static final int IDENTITY_LECTURER = 3;

    /**
     * The default identity is set to student.
     */
    private int currentIdentity = IDENTITY_STUDENT;

    @FXML
    private Button identityButton;

    @FXML
    private Label roomIdLabel;

    @FXML
    private TextField roomIdTextField;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private GridPane dataFieldsGridPane;

    @FXML
    private Button actionButton;

    /**
     * Loads the layout for the specified identity.
     */
    public void setIdentity(int identityConstant) {
        /**
         * Load the layout for student.
         */
        if (identityConstant == IDENTITY_STUDENT) {
            identityButton.setText("Student");

            passwordLabel.setVisible(false);
            passwordLabel.setManaged(false);
            passwordField.setVisible(false);
            passwordField.setManaged(false);

            roomIdLabel.setVisible(true);
            roomIdLabel.setManaged(true);
            roomIdTextField.setVisible(true);
            roomIdTextField.setManaged(true);

            dataFieldsGridPane.setVgap(0);

            actionButton.setText("Join Room");
        }
        /**
         * Load the layout for moderator.
         */
        else if (identityConstant == IDENTITY_MODERATOR) {
            identityButton.setText("Moderator");

            passwordLabel.setVisible(true);
            passwordLabel.setManaged(true);
            passwordField.setVisible(true);
            passwordField.setManaged(true);

            roomIdLabel.setVisible(true);
            roomIdLabel.setManaged(true);
            roomIdTextField.setVisible(true);
            roomIdTextField.setManaged(true);

            dataFieldsGridPane.setVgap(25);

            actionButton.setText("Join Room");
        }
        /**
         * Load the layout for lecturer.
         */
        else {
            identityButton.setText("Lecturer");

            passwordLabel.setVisible(true);
            passwordLabel.setManaged(true);
            passwordField.setVisible(true);
            passwordField.setManaged(true);

            roomIdLabel.setVisible(false);
            roomIdLabel.setManaged(false);
            roomIdTextField.setVisible(false);
            roomIdTextField.setManaged(false);

            dataFieldsGridPane.setVgap(0);

            actionButton.setText("Create Room");
        }
    }

    /**
     * Changes the identity & updates the layout.
     */
    @FXML
    public void identityButtonPressed(ActionEvent event) {
        /**
         * Change the current identity.
         */
        if (currentIdentity == IDENTITY_STUDENT) {
            currentIdentity = IDENTITY_MODERATOR;
        }
        else if (currentIdentity == IDENTITY_MODERATOR) {
            currentIdentity = IDENTITY_LECTURER;
        }
        else {
            currentIdentity = IDENTITY_STUDENT;
        }

        /**
         * Update the layout.
         */
        setIdentity(currentIdentity);
    }

    /**
     * Initializes the scene by loading the layout for the default identity.
     * Animates the gradient in the background.
     */
    @FXML
    public void initialize() {
        setIdentity(currentIdentity);

        /**
         * Animates the background gradient in the menu.
         * Source: https://stackoverflow.com/questions/24587342/how-to-animate-lineargradient-on-javafx
         */
        ObjectProperty<Color> baseColor = new SimpleObjectProperty<>();

        KeyValue keyValue1 = new KeyValue(baseColor, Color.web("045AAF",1.0));
        KeyValue keyValue2 = new KeyValue(baseColor, Color.web("F48999",1.0));
        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(10000), keyValue2);
        Timeline timeline = new Timeline(keyFrame1, keyFrame2);

        baseColor.addListener((obs, oldColor, newColor) -> {
            MainView.getPrimaryStage().getScene().getRoot().setStyle(String.format("-gradient-variable: #%02x%02x%02x; ",
                    (int)(newColor.getRed()*255),
                    (int)(newColor.getGreen()*255),
                    (int)(newColor.getBlue()*255)));
        });

        timeline.setAutoReverse(true);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Handles pressing the actionButton
     * Loads the room layout into the scene, passes that scene into the stage.
     */
    @FXML
    public void actionButtonPressed() throws Exception {
        /**
         * Create a new scene and load the layout from RoomView.fxml into the scene graph.
         */
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RoomView.fxml"));
        Scene roomScene = new Scene(root);

        /**
         * Load the roomScene into the primaryStage of MainView.
         */
        MainView.getPrimaryStage().setScene(roomScene);
    }
}