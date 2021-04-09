package nl.tudelft.oopp.app.views;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import nl.tudelft.oopp.app.communication.ServerCommunication;
import nl.tudelft.oopp.app.data.Quiz;

public class QuizCell extends ListCell<Quiz> {

    private String quizId;

    @FXML
    private TextArea content;

    @FXML
    private Button toggleStatus;

    public QuizCell() {
        loadFxml();
    }

    /**
     * Getter for quiz ID.
     *
     * @returns the Id of the quiz in the current cell.
     */
    public String getQuizId() {
        return quizId;
    }

    /**
     * Loads the fxml QuizCell file.
     */
    private void loadFxml() {
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
     * Updates the cell with the current properties of the quiz.
     *
     * @param q     The question contained in the cell
     * @param empty - is empty
     */
    @Override
    protected void updateItem(Quiz q, boolean empty) {
        super.updateItem(q, empty);

        if (empty || q == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            if (q.isOpen()) {
                toggleStatus.setText("Close");
            } else {
                toggleStatus.setText("Open");
            }
            quizId = q.getId();
            content.setText(q.toString());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }

    /**
     * Toggle button pressed.
     */
    @FXML
    public void toggleButtonPressed() {
        ServerCommunication.quizToggleOpenStatus(quizId);
        if (toggleStatus.getText().equals("Open")) {
            toggleStatus.setText("Close");
        } else {
            toggleStatus.setText("Open");
        }
    }

    /**
     * Toggle answer distribution ready pressed.
     */
    @FXML
    public void toggleAnswerDistributionReadyPressed() {
        ServerCommunication.quizToggleAnswerDistributionReadyStatus(quizId);
    }
}