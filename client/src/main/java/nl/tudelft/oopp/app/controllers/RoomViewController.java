package nl.tudelft.oopp.app.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import nl.tudelft.oopp.app.communication.ServerCommunication;
import nl.tudelft.oopp.app.data.Question;
import nl.tudelft.oopp.app.views.MainView;
import nl.tudelft.oopp.app.views.QuestionCell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RoomViewController implements Initializable {

    @FXML
    private ListView<Question> questionsListView;

    private final ObservableList<Question> questions;

    @FXML
    private TextField questionText;

    public RoomViewController() {
        questions = FXCollections.observableArrayList();

    }

    /**
     * Initializes the roomview  with the questions
     * Sets a timeline to fetch questions and order them every 2 seconds
     * @param location
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            fetchQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        questionsListView.setItems(questions);
        questionsListView.setCellFactory(questionList -> new QuestionCell());

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
            try {
                fetchQuestions();
            } catch (IOException e) {
                e.printStackTrace();
            }
            java.util.Collections.sort(questionsListView.getItems(), new java.util.Comparator<Question>() {
                @Override
                public int compare(Question q1, Question q2) {
                    int rating1 = q1.getNumberOfUpvotes() - q1.getNumberOfDownvotes();
                    int rating2 = q2.getNumberOfUpvotes() - q2.getNumberOfDownvotes();
                    return (rating2 - rating1);
                }
            });
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

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

    /**
     * Handles pressing the ask question button
     */

    @FXML
    public void askQuestionButtonPressed() {
        //TODO add the question to the specific room you're in. And pass the real ownerId
        ServerCommunication.askQuestion(new Question("1234", "1", questionText.getText(), 0, 0, false, ""));
    }

    /**
     * Fetches all questions from the server and displays them
     * @throws IOException
     */

    @FXML
    public void fetchQuestions() throws IOException {
        questions.removeAll();
        questionsListView.getItems().clear();
        //TODO pass real room id
        questions.addAll(ServerCommunication.fetchQuestionsByRoomId("1"));
    }

}
