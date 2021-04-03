package nl.tudelft.oopp.app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.app.communication.ServerCommunication;
import nl.tudelft.oopp.app.data.Quiz;
import nl.tudelft.oopp.app.views.MainView;
import nl.tudelft.oopp.app.views.QuizCell;
import org.w3c.dom.Text;

import java.io.IOException;


public class QuizMenuViewController {

    @FXML
    private TextField question;
    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private TextField c;
    @FXML
    private TextField d;
    @FXML
    private TextField e;
    @FXML
    private TextField f;
    @FXML
    private TextField g;
    @FXML
    private TextField h;
    @FXML
    private TextField i;
    @FXML
    private TextField j;
    @FXML
    private TextField correctAnswer;


    @FXML
    private ListView<Quiz> quizzesListView;

    private final ObservableList<Quiz> quizzes;

    public QuizMenuViewController() {
        quizzes = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        try{
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

    @FXML
    public void addButtonPressed() {
        char correctAns = correctAnswer.getText().length() == 1? correctAnswer.getText().charAt(0) : '?';
        Quiz quiz = new Quiz(MainView.getRoomId(), question.getText(), a.getText(), b.getText(), c.getText(), d.getText(), e.getText(), f.getText(), g.getText(), h.getText(), i.getText(), j.getText(), correctAns);
        ServerCommunication.addQuiz(quiz);

        question.clear();
        a.clear();
        b.clear();
        c.clear();
        d.clear();
        e.clear();
        f.clear();
        g.clear();
        h.clear();
        i.clear();
        j.clear();
        correctAnswer.clear();

        try{
            getAllQuizzes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
