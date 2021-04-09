package nl.tudelft.oopp.app.views;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import nl.tudelft.oopp.app.data.User;

public class MainView extends Application {

    /**
     * Used for the getPrimaryStage() getter.
     */
    private static Stage primaryStage;

    private static User user;
    private static String roomId;

    /*
     * The primaryStage getter is used to load scenes into the stage from within the controllers.
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        MainView.user = user;
    }

    public static String getRoomId() {
        return roomId;
    }

    public static void setRoomId(String roomId) {
        MainView.roomId = roomId;
    }

    /*
     * "... it is useful to include the main() method
     *  so you can run JAR files that were created without
     * the JavaFX Launcher, such as when using an IDE
     * in which the JavaFX tools are not fully integrated."
     *
     * Source: JavaFX 2.2.40 Documentation
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainView.primaryStage = primaryStage;

        /*
         * Sets the title of the stage,
         * loads the icon of the stage,
         * makes the stage non-resizable.
         */
        primaryStage.setTitle("App");
        primaryStage.getIcons().add(new Image("img/TUDelftFlameSolid.png"));
        primaryStage.setResizable(false);

        /*
         * Loads the mainScene and displays the primaryStage.
         *
         * Stage = the window of the application
         * Scene = a container for UI content
         */
        /*Creates a scene and loads the layout from MainView.fxml into the scene graph.
         */
        Parent root = FXMLLoader.load(
            Objects.requireNonNull(getClass().getResource("/fxml/MainView.fxml")));
        Scene mainScene = new Scene(root, 400, 400);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}