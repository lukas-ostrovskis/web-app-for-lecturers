package nl.tudelft.oopp.app.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import nl.tudelft.oopp.app.communication.ServerCommunication;
import nl.tudelft.oopp.app.controllers.RoomViewController;
import nl.tudelft.oopp.app.data.Quiz;
import nl.tudelft.oopp.app.data.User;

import java.io.IOException;
import java.util.List;

public class QuizCell extends ListCell<Quiz> {

    private String quizId;

    @FXML
    private TextArea content;

    @FXML
    private Button toggleStatus;

    public QuizCell() {
        loadFXML();
    }

    /**
     * @returns the Id of the quiz in the current cell
     */

    public String getQuizId() {
        return quizId;
    }

    /**
     * Loads the fxml QuizCell file
     */

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/QuizCell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the cell with the current properties of the quiz
     * @param q The question contained in the cell
     * @param empty
     */

    @Override
    protected void updateItem(Quiz q, boolean empty) {
        super.updateItem(q, empty);

        if (empty || q == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            if (q.isOpen()){
                toggleStatus.setText("Close");
            }
            else {
                toggleStatus.setText("Open");
            }
            quizId = q.getId();
            content.setText(q.toString());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }

    @FXML
    public void toggleButtonPressed() {
        ServerCommunication.quizToggleOpenStatus(quizId);
        if(toggleStatus.getText().equals("Open")) {
            toggleStatus.setText("Close");
        }
        else toggleStatus.setText("Open");
    }

    @FXML
    public void toggleAnswerDistributionReadyPressed() {
        ServerCommunication.quizToggleAnswerDistributionReadyStatus(quizId);
    }
}