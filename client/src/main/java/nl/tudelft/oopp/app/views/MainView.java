package nl.tudelft.oopp.app.views;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;
import nl.tudelft.oopp.app.data.User;

public class MainView extends Application {

    /**
     * Used for the getPrimaryStage() getter.
     */
    private static Stage primaryStage;

    private static User user;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        /*Creates a scene and loads the layout from MainView.fxml into the scene graph.
         */
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
        Scene mainScene = new Scene(root, 400, 400);

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
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

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

    /*
     * "... it is useful to include the main() method so you can run JAR files that were created without
     * the JavaFX Launcher, such as when using an IDE in which the JavaFX tools are not fully integrated.""
     *
     * Source: JavaFX 2.2.40 Documentation
     */
    public static void main(String[] args) {
        launch(args);
    }
}