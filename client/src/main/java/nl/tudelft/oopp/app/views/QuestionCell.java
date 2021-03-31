package nl.tudelft.oopp.app.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import nl.tudelft.oopp.app.communication.ServerCommunication;
import nl.tudelft.oopp.app.controllers.RoomViewController;
import nl.tudelft.oopp.app.data.Question;

import java.io.IOException;

public class QuestionCell extends ListCell<Question> {


    private String questionId;

    @FXML
    private Label user;

    @FXML
    private Label content;

    @FXML
    private Label rating;

    @FXML
    private Button toggleStatus;

    @FXML
    private Button upvote;

    @FXML
    private Button downvote;

    @FXML
    private Button deleteButton;

    @FXML
    private Label answered;

    public QuestionCell() {
        loadFXML();
    }

    /**
     * @returns the Id of the question une the current cell
     */

    public String getQuestionId() {
        return questionId;
    }

    /**
     * Loads the fxml QuestionCell file
     */

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/QuestionCell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the cell with the current properties of the question
     * @param q The question contained in the cell
     * @param empty
     */

    @Override
    protected void updateItem(Question q, boolean empty) {
        super.updateItem(q, empty);

        if (empty || q == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            if (q.isStatus()){
                toggleStatus.setText("Mark as not answered");
                answered.setVisible(true);
            }
            else {
                toggleStatus.setText("Mark as answered");
                answered.setVisible(false);
            }
            questionId = q.getId();
            content.setText(q.getContent());
            user.setText(q.getOwnerId());
            rating.setText(String.valueOf(q.getNumberOfUpvotes() - q.getNumberOfDownvotes()));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }

    /**
     * Handles clicking the upvote button
     */

    @FXML
    public void upvoteButtonPressed() {
        ServerCommunication.upvoteQuestionById(questionId, RoomViewController.getCurrentUser().getId());
    }

    /**
     * Handles clicking the downvote button
     */

    @FXML
    public void downvoteButtonPressed() {
        ServerCommunication.downvoteQuestionById(questionId, RoomViewController.getCurrentUser().getId());
    }

    /**
     * Handles clicking the delete button
     */

    @FXML
    public void deleteButtonPressed() {
        ServerCommunication.deleteQuestion(questionId);
    }

    /**
     * Handles clicking the "mark as answered" button
     */

    @FXML
    public void toggleButtonPressed() {
        ServerCommunication.toggleStatus(questionId);
    }
}