package nl.tudelft.oopp.app.views;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import nl.tudelft.oopp.app.communication.ServerCommunication;
import nl.tudelft.oopp.app.controllers.RoomViewController;
import nl.tudelft.oopp.app.data.Question;
import nl.tudelft.oopp.app.data.User;

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
    private Button upvoteButton;

    @FXML
    private Button downvoteButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button banButton;

    @FXML
    private Label answered;

    /**
     * Instantiates a new Question cell.
     */
    public QuestionCell() {
        loadFxml();
    }

    /**
     * Getter for the question ID.
     *
     * @returns the Id of the question une the current cell.
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * Loads the fxml QuestionCell file.
     */
    private void loadFxml() {
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
     * Updates the cell with the current properties of the question.
     *
     * @param q     The question contained in the cell
     * @param empty - is empty
     */
    @Override
    protected void updateItem(Question q, boolean empty) {
        super.updateItem(q, empty);

        if (empty || q == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            if (q.isStatus()) {
                toggleStatus.setText("Mark as not answered");
                answered.setVisible(true);
            } else {
                toggleStatus.setText("Mark as answered");
                answered.setVisible(false);
            }
            if (contains(q.getUpvoters(), RoomViewController.getCurrentUser().getId())) {
                this.upvoteButton.setDisable(true);
                this.downvoteButton.setDisable(false);
            } else if (contains(q.getDownvoters(), RoomViewController.getCurrentUser().getId())) {
                this.downvoteButton.setDisable(true);
                this.upvoteButton.setDisable(false);
            } else {
                this.downvoteButton.setDisable(false);
                this.upvoteButton.setDisable(false);
            }
            if (RoomViewController.getCurrentUser().getRole().equals("student")) {
                toggleStatus.setVisible(false);
            }
            questionId = q.getId();
            content.setText(q.getContent());
            if (q.getOwnerName().equals(RoomViewController.getCurrentUser().getName())) {
                user.setText(q.getOwnerName() + "  (You)");
            } else {
                user.setText(q.getOwnerName());
            }
            deleteButton.setVisible(false);
            banButton.setVisible(false);

            if (RoomViewController.getCurrentUser().getId().equals(q.getOwnerId())
                || !(RoomViewController.getCurrentUser().getRole().equals("student"))) {
                deleteButton.setVisible(true);
            }
            if (!(RoomViewController.getCurrentUser().getRole().equals("student"))) {
                banButton.setVisible(true);
            }

            rating.setText(String.valueOf(q.getNumberOfUpvotes() - q.getNumberOfDownvotes()));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }

    /**
     * Helper method to detect if the used has already upvoted/downvoted the question.
     *
     * @param list   the list
     * @param userId the user id
     * @return the boolean
     */
    public boolean contains(final List<User> list, final String userId) {
        return list.stream().anyMatch(o -> o.getId().equals(userId));
    }

    /**
     * Handles clicking the upvote button.
     */
    @FXML
    public void upvoteButtonPressed() {
        ServerCommunication
            .upvoteQuestionById(this.questionId, RoomViewController.getCurrentUser().getId());
    }

    /**
     * Handles clicking the downvote button.
     */
    @FXML
    public void downvoteButtonPressed() {
        ServerCommunication
            .downvoteQuestionById(this.questionId, RoomViewController.getCurrentUser().getId());
    }

    /**
     * Handles clicking the delete button.
     */
    @FXML
    public void deleteButtonPressed() {
        ServerCommunication.deleteQuestion(questionId);
    }

    /**
     * Ban button pressed.
     */
    @FXML
    public void banButtonPressed() {
        ServerCommunication.banUser(this.questionId);
    }

    /**
     * Handles clicking the "mark as answered" button.
     */
    @FXML
    public void toggleButtonPressed() {
        ServerCommunication.toggleStatus(questionId);
    }
}