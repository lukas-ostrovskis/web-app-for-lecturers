package nl.tudelft.oopp.app.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import nl.tudelft.oopp.app.data.Question;
import java.io.IOException;

public class QuestionCell extends ListCell<Question> {

    @FXML
    private Label user;

    @FXML
    private Label content;

    @FXML
    private Label rating;

    @FXML
    private Button upvote;

    @FXML
    private Button downvote;

    public QuestionCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/QuestionCell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Question q, boolean empty) {
        super.updateItem(q, empty);

        if(empty || q == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            content.setText(q.getContent());
            user.setText(q.getOwnerId());
            rating.setText(String.valueOf(q.getNumberOfUpvotes()-q.getNumberOfDownvotes()));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}