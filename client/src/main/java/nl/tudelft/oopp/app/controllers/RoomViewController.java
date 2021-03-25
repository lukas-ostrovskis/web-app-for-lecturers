package nl.tudelft.oopp.app.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import nl.tudelft.oopp.app.data.Question;
import nl.tudelft.oopp.app.views.MainView;
import nl.tudelft.oopp.app.views.QuestionCell;

public class RoomViewController implements Initializable {

    @FXML
    private ListView<Question> questionsListView;

    private ObservableList<Question> studentObservableList;

    public RoomViewController(){
        studentObservableList = FXCollections.observableArrayList();

        studentObservableList.addAll(
            new Question("1232asd",
                "41224as",
                "1212ads",
                "What is the time",
                10,
                3,
                false,
                ""),
            new Question("1232asd",
                "41224as",
                "1212ads",
                "What is the time",
                10,
                3,
                false,
                ""),
            new Question("123u0j",
                "n123n",
                "zma2m",
                "Is this going to be on the exam?",
                15,
                0,
                false,
                "")
        );
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        questionsListView.setItems(studentObservableList);
        questionsListView.setCellFactory(studentListView -> new QuestionCell());
        System.out.println("damn boid questions");
    }

//    @FXML
//    private HBox question;

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
//        questionsListView.getItems().add("Test " + (int) (Math.random() * 100));
    }

    @FXML
    public void removeQuestionButtonPressed() {
        int selectedIndex = questionsListView.getSelectionModel().getSelectedIndex();
        questionsListView.getItems().remove(selectedIndex);
    }

    @FXML
    public void loadQuestion() throws Exception {
        questionsListView.setItems(studentObservableList);
        questionsListView.setCellFactory(studentListView -> new QuestionCell());
        System.out.println("Damn bois im loading questions");
//        for (int i = 0; i < 10; i++) {
////            questionsListView.getItems().add(FXMLLoader.load(getClass().getResource("/fxml" + "/
//            questionsListView.getItems().add("da fuck");
//
//        }
    }

}
