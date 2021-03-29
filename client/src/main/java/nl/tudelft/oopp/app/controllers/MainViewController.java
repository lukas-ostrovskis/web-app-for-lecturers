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
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import nl.tudelft.oopp.app.communication.ServerCommunication;
import nl.tudelft.oopp.app.data.User;
import nl.tudelft.oopp.app.views.MainView;

import java.io.IOException;

import static nl.tudelft.oopp.app.communication.ServerCommunication.createRoom;

public class MainViewController {

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
    private Label emailLabel;

    @FXML
    private TextField emailTextField;

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
    private Button joinRoomButton;

    @FXML
    private Button createRoomButton;

    @FXML
    private Label identityLabel;

    /**
     * Loads the layout for the specified identity.
     */
    public void setIdentity(int identityConstant) {
        /*
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

            dataFieldsGridPane.setVgap(25);

            joinRoomButton.setVisible(true);
            joinRoomButton.setManaged(true);

            createRoomButton.setVisible(false);
            createRoomButton.setManaged(false);
        }
        /*
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

            joinRoomButton.setVisible(true);
            joinRoomButton.setManaged(true);

            createRoomButton.setVisible(false);
            createRoomButton.setManaged(false);
        }
        /*
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

            dataFieldsGridPane.setVgap(12.5);

            joinRoomButton.setVisible(false);
            joinRoomButton.setManaged(false);

            createRoomButton.setVisible(true);
            createRoomButton.setManaged(true);
        }
    }

    /**
     * Changes the identity & updates the layout.
     */
    public void identityButtonPressed(ActionEvent event) {
        /*
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

        /*
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

        /*
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
     * It loads the MainView scene.
     * @throws IOException if it cant load correctly
     */
    public void loadMainView() throws IOException {

        /* Create a new scene and load the layout from MainView.fxml into the scene graph.
         */
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
        Scene mainViewScene = new Scene(root);

        /* Load the roomScene into the primaryStage of MainView.
         */
        MainView.getPrimaryStage().setScene(mainViewScene);

        /* Re-centers the stage on screen.
         */
        MainView.getPrimaryStage().centerOnScreen();

    }

    /**
     * It loads the RoomView scene.
     * @throws IOException if it cant load correctly
     */
    public void loadRoomView() throws IOException {

        /* Create a new scene and load the layout from RoomView.fxml into the scene graph.
         */
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RoomView.fxml"));

        /* Load the roomScene into the primaryStage of RoomView.
         */
        Scene roomScene = new Scene(root);

        /* Re-centers the stage on screen.
         */
        MainView.getPrimaryStage().setScene(roomScene);
        MainView.getPrimaryStage().centerOnScreen();

    }

    /**
     * Handles pressing the createRoomButton
     * Communicates to the server to create a new room.
     * It requires the E-Mail and the password of a lecturer.
     */
    public void createRoomButtonPressed() throws Exception {



        // Add user server-side
        User user = ServerCommunication.findUsers(emailTextField.getText(), passwordField.getText(), currentIdentity, roomIdTextField.getText());

        // User returned by server is invalid, meaning they weren't added.
        if (user == null) {
            System.out.printf("User was not added:%s\n", user);
            return;
        }
        System.out.printf("User %s (%s) was added, %s\n", user.getId(), user.getName(), user.getRole());

        // Add the user client-side
        MainView.setUser(user);

        String id = user.getId();
        if(user.getEmail() != null){

            // For lecturers we create the room
            if(currentIdentity == 3){
                ServerCommunication.createRoom(id);
            }

            // Make user join room server-side
            String response = ServerCommunication.joinRoom(roomIdTextField.getText(), user);
            if (response == null) {
                System.out.printf("User could not be added to roomn %s", roomIdTextField.getText());
                return;
            }

            loadRoomView();
        }
        else{
            /* error message here
                    */
        }
    }

    /**
     * Handles pressing the joinRoomButton
     * Communicates to the server to join to an existing room.
     * It requires the E-Mail and the roomId that the user wants to get in.
     */
    public void joinRoomButtonPressed() throws Exception {

        // Add user server-side
        User user = ServerCommunication.findUsers(emailTextField.getText(), null, 1, roomIdTextField.getText());

        // User returned by server is invalid, meaning they weren't added.
        if (user == null) {
            System.out.printf("User was not added:%s", user);
            return;
        }

        // Create the user client-side
        MainView.setUser(user);

        String id = user.getId();
        if(user.getEmail() != null){

            // Make user join room server-side
            String response = ServerCommunication.joinRoom(roomIdTextField.getText(), user);
            if (response == null) {
                System.out.printf("User could not be added to roomn %s", roomIdTextField.getText());
                return;
            }

            loadRoomView();
        }

    }
}
