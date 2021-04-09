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
    private TextField userNameTextField;
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

    @FXML
    private Label usernameLabel;
    private User user;

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

            usernameLabel.setVisible(true);
            usernameLabel.setManaged(true);

            userNameTextField.setVisible(true);
            userNameTextField.setManaged(true);
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

            usernameLabel.setVisible(false);
            usernameLabel.setManaged(false);

            userNameTextField.setVisible(false);
            userNameTextField.setManaged(false);

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
        System.out.println(MainView.getRoomId() + " is the id of the joined room."); // returns 0 for User

    }


    /**
     * Handles pressing the createRoomButton
     * Communicates to the server to create a new room.
     * It requires the E-Mail and the password of a lecturer.
     */
    public void createRoomButtonPressed() throws Exception {

        try {

            // Try creating a user
            user=createUser();

            // Try creating room
            String roomId = ServerCommunication.createRoom(passwordField.getText());
            System.out.printf(" > Created server-side room (id: %s)\n", roomId);
            MainView.setRoomId(roomId);

            loadRoomView();

        } catch (ServerCommunication.UserNotAddedException e){
            // If user could not be created server-side
            if(emailTextField.getText().trim().isEmpty())
                presentError("Error!", "You have not entered an email address.");
            // First we check if the password field is empty.
            else if(passwordField.getText().trim().isEmpty())
                presentError("Error!", "You have not entered a password.");

            // if that is not the case, the only remaining reason is that the password is wrong
            else presentError("Error!", e.getMessage());

        }
          catch (ServerCommunication.RoomNotAddedException e) {
              // If room could not be created server-side
              presentError("Error!", e.getMessage());
        }
          catch(ServerCommunication.EmptyPasswordFieldException e){
            presentError("Error", e.getMessage());
          }

    }

    /**
     * Handles pressing the joinRoomButton
     * Communicates to the server to join to an existing room.
     * It requires the E-Mail and the roomId that the user wants to get in.
     */
    public void joinRoomButtonPressed() throws Exception {

        try {

            // Try creating the user
            user = createUser();

            MainView.setRoomId(roomIdTextField.getText());

            ServerCommunication.joinRoom(MainView.getRoomId(), user);

            loadRoomView();


        } catch (ServerCommunication.UserNotAddedException e) {

            // if user could not be created server-side
           presentError("Error!", e.getMessage());

        }
    }


    private User createUser() throws ServerCommunication.UserNotAddedException, ServerCommunication.UserBannedByIpExtension, ServerCommunication.EmptyEmailFieldException, ServerCommunication.EmptyPasswordFieldException {

        // Parse identity
        String identity;
        switch (currentIdentity) {
            case IDENTITY_LECTURER: identity = "lecturer"; break;
            case IDENTITY_MODERATOR: identity = "moderator"; break;
            case IDENTITY_STUDENT: identity = "student"; break;
            default: throw new ServerCommunication.UserNotAddedException("invalid role.");
        }

        // Client-side user
        User user = new User(emailTextField.getText(), identity, userNameTextField.getText());
        System.out.printf(" > Created client-side user (%s)\n", currentIdentity);
        try{
            if(identity.equals("lecturer") || identity.equals("moderator")){
                if(emailTextField.getText().trim().isEmpty())
                    throw new ServerCommunication.EmptyEmailFieldException("");
                if(passwordField.getText().trim().isEmpty())
                    throw new ServerCommunication.EmptyPasswordFieldException("");
        }

        } catch (ServerCommunication.EmptyEmailFieldException e) {
            presentError("Error", "You have not given an email address.");
            return null;
        }
          catch (ServerCommunication.EmptyPasswordFieldException e){
            presentError("Error", "You have not given a password.");
            return null;
          }


        // Create the server-side user
        try{
            user = ServerCommunication.addUser(user, passwordField.getText());
        }
         catch(ServerCommunication.UserBannedByIpExtension e)
         {
             presentError("Error", "You have been banned.");
             return null;
         }
        catch(ServerCommunication.UserNotAddedException e)
        {
            presentError("Error", e.getMessage());
            return null;
        }

        System.out.printf(" > Created server-side user (id: %s)\n", user.getId());

        // And update the client-side user with the new user (containing id)
        MainView.setUser(user);

        return user;
    }

    /**
     * Helper method for displaying errors.
     * If the error message is about being banned, the application will be terminated.
     * @param err error type
     * @param msg error message
     */
    private void presentError(String err, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(err);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
        if(msg.equals("You have been banned."))
            System.exit(0);
    }
}
