package nl.tudelft.oopp.app.controllers;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.app.communication.ServerCommunication;
import nl.tudelft.oopp.app.data.Quiz;
import nl.tudelft.oopp.app.views.MainView;
import nl.tudelft.oopp.app.views.QuizCell;


public class QuizMenuViewController {

    private final ObservableList<Quiz> quizzes;
    @FXML
    private TextField question;
    @FXML
    private TextField optionA;
    @FXML
    private TextField optionB;
    @FXML
    private TextField optionC;
    @FXML
    private TextField optionD;
    @FXML
    private TextField optionE;
    @FXML
    private TextField optionF;
    @FXML
    private TextField optionG;
    @FXML
    private TextField optionH;
    @FXML
    private TextField optionI;
    @FXML
    private TextField optionJ;
    @FXML
    private TextField correctAnswer;
    @FXML
    private ListView<Quiz> quizzesListView;

    public QuizMenuViewController() {
        quizzes = FXCollections.observableArrayList();
    }

    /**
     * Initializes the list view for the quizzes.
     */
    @FXML
    public void initialize() {
        try {
            getAllQuizzes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        quizzesListView.setItems(quizzes);
        quizzesListView.setCellFactory(quizList -> new QuizCell());
    }

    @FXML
    public void getAllQuizzes() throws IOException {
        quizzes.addAll(ServerCommunication.getAllQuizzes(MainView.getRoomId()));
    }

    /**
     * Add the quiz.
     */
    @FXML
    public void addButtonPressed() {
        char correctAns =
            correctAnswer.getText().length() == 1 ? correctAnswer.getText().charAt(0) : '?';
        Quiz quiz = new Quiz(
            MainView.getRoomId(),
            question.getText(),
            optionA.getText(),
            optionB.getText(),
            optionC.getText(),
            optionD.getText(),
            optionE.getText(),
            optionF.getText(),
            optionG.getText(),
            optionH.getText(),
            optionI.getText(),
            optionJ.getText(),
            correctAns
        );
        ServerCommunication.addQuiz(quiz);

        question.clear();
        optionA.clear();
        optionB.clear();
        optionC.clear();
        optionD.clear();
        optionE.clear();
        optionF.clear();
        optionG.clear();
        optionH.clear();
        optionI.clear();
        optionJ.clear();
        correctAnswer.clear();

        try {
            getAllQuizzes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
