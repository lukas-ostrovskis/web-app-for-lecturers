package nl.tudelft.oopp.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Main JavaFX application launched from within App.java
 */
public class MainView extends Application {

    /**
     * Used for the getPrimaryStage() getter.
     */
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        /**
         * Creates a scene and loads the layout from MenuView.fxml into the scene graph.
         */
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MenuView.fxml"));
        Scene menuScene = new Scene(root, 400, 400);

        /**
         * Sets the title of the stage,
         * loads the icon of the stage,
         * makes the stage non-resizable.
         */
        primaryStage.setTitle("App");
        primaryStage.getIcons().add(new Image("img/TUDelftFlameSolid.png"));
        primaryStage.setResizable(false);

        /**
         * Loads the menuScene and displays the primaryStage.
         *
         * Stage = the window of the application
         * Scene = a container for UI content
         */
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    /**
     * The primaryStage getter is used to load scenes into the stage from within the controllers.
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * "... it is useful to include the main() method so you can run JAR files that were created without
     * the JavaFX Launcher, such as when using an IDE in which the JavaFX tools are not fully integrated.""
     *
     * Source: JavaFX 2.2.40 Documentation
     */
    public static void main(String[] args) {
        launch(args);
    }
}