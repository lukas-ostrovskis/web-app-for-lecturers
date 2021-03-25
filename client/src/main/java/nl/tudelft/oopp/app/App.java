package nl.tudelft.oopp.app;

import nl.tudelft.oopp.app.views.MainView;

/**
 * A workaround to avoid the gradle-related error:
 *      Error: JavaFX runtime components are missing, and are required to run this application
 *
 * Source: https://github.com/openjfx/javafx-gradle-plugin/issues/31
 */
public class App {
    public static void main(String[] args) {
        MainView.main(new String[0]);
    }
}

