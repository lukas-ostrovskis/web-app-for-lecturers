package nl.tudelft.oopp.app.controllers;

import java.io.File;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import nl.tudelft.oopp.app.communication.ServerCommunication;
import nl.tudelft.oopp.app.data.Question;
import nl.tudelft.oopp.app.data.Quiz;
import nl.tudelft.oopp.app.data.User;
import nl.tudelft.oopp.app.views.MainView;
import nl.tudelft.oopp.app.views.QuestionCell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class RoomViewController implements Initializable {

    @FXML
    private Label identityLabel;

    @FXML
    private Label roomId;

    @FXML
    private Button endLectureButton;

    @FXML
    private ListView<Question> questionsListView;

    private final ObservableList<Question> questions;

    private boolean quizOpen = false;

    private static Quiz openedQuiz;

    @FXML
    private TextField questionText;
    @FXML
    private Button quizzesButton;

    private static User currentUser = new User("1", "William", "email", "lecturer", "1234");

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
        this.currentUser = MainView.getUser();
        this.identityLabel.setText("You're a " + currentUser.getRole());
        this.roomId.setText(MainView.getRoomId());
        if(currentUser.getRole().equals("student")){
            endLectureButton.setVisible(false);
        }
        try {
            fetchQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        questionsListView.setItems(questions);
        questionsListView.setCellFactory(questionList -> new QuestionCell());

        if(currentUser.getRole().equals("student")) {
            quizzesButton.setVisible(false);
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
            try {
                fetchQuestions();
                isBanned();         // checks whether the user has been banned
            } catch (IOException e) {
                e.printStackTrace();
            }
            /**
             *
             *  If the user has been banned
             *  First an error message is being displayed.
             *  After that the application will close itself and the user
             *  will not be able to enter a room because his IP is in the
             *  server's blacklist.
             *
             * */
            catch (ServerCommunication.UserBannedByIpExtension e) {
                 System.exit(0);
             } catch (InterruptedException e) {
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

        startCheckingQuizOpen(MainView.getRoomId());
    }

    /**
     * Handles pressing the leaveRoomButton
     * Loads the menu layout into the scene, passes that scene into the stage.
     */
    @FXML
    public void leaveRoomButtonPressed() {

        try {

            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
            Scene mainScene = new Scene(root, 400, 400);
            MainView.getPrimaryStage().setScene(mainScene);
            MainView.getPrimaryStage().centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void endLectureButtonPressed() {

        ServerCommunication.deleteQuizFromServerMemory(MainView.getRoomId());

        try {
//            ServerCommunication.exportQuestionsToCsv(MainView.getRoomId());
            // Delete room from server
            ServerCommunication.deleteRoom();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Room successfully deleted.");
            alert.showAndWait();

            leaveRoomButtonPressed();

        } catch (ServerCommunication.RoomNotDeletedException e) {

            // Alert user room wasn't deleted
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void quizzesButtonPressed() throws IOException{
        System.out.println("bbb");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/QuizMenuView.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Quizzes Menu");
        stage.setScene(new Scene(root, 770,620));
        stage.show();
    }

    /**
     * @returns the current user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * @returns the opened quiz.
     */
    public static Quiz getOpenedQuiz() {
        return openedQuiz;
    }

    /**
     * Handles pressing the ask question button
     */

    @FXML
    public void askQuestionButtonPressed() {
        ServerCommunication.askQuestion(new Question(currentUser.getId(),
                currentUser.getName(),
                MainView.getRoomId(),
                questionText.getText(),
                0,
                0,
                false,
                ""));
    }

    /**
     * Fetches all questions from the server and displays them
     * @throws IOException
     */

    @FXML
    public void fetchQuestions() throws IOException {
        questions.removeAll();
        questionsListView.getItems().clear();
        questions.addAll(ServerCommunication.fetchQuestionsByRoomId(MainView.getRoomId()));
    }

    /**
     * Checks whether a student has been recently banned
     * If he has, an error message will be displayed.
     * It is going to disappear after 3 seconds and the
     * application will close itself.
     * @throws ServerCommunication.UserBannedByIpExtension if true
     */
    @FXML
    public void isBanned() throws ServerCommunication.UserBannedByIpExtension, InterruptedException {

            if(ServerCommunication.isUserBanned(currentUser)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("You have been banned.");
                alert.show();
                TimeUnit.SECONDS.sleep(3);
                alert.close();
                System.exit(0);
            }

    }

    @FXML
    public void exportRoomButtonPressed() throws IOException {
        JFileChooser jfc =
            new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());

        int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            ServerCommunication
                .exportQuestionsToCsv(MainView.getRoomId(), selectedFile.getAbsolutePath());
        }
    }

    public void startCheckingQuizOpen(String roomId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                    while(true) {
                        Quiz currentQuiz = ServerCommunication.checkQuizOpen(roomId);
                        System.out.println(currentQuiz);
                        openedQuiz = currentQuiz;
                        if(currentQuiz != null) {
                            if(!quizOpen) {
                                quizOpen = true;
                                Platform.runLater(new Runnable() {
                                    @Override public void run() {
                                        try {
                                            loadQuizView();
                                        } catch(IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                        else {
                            if(quizOpen) {
                                quizOpen = false;
                                //remove quiz view
                            }
                        }
                    }
            }
        }).start();
    }

    public void loadQuizView() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/QuizView.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Quiz");
        stage.setScene(new Scene(root, 620,620));
        stage.show();
    }
}

