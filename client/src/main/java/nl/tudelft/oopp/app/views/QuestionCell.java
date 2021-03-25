package nl.tudelft.oopp.app.views;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.text.Text;

import nl.tudelft.oopp.app.data.Question;

public class QuestionCell extends ListCell<Question> {
    @FXML
    private Label questionText;

    @FXML
    private Label questionRating;

    @FXML
    private Button downvoteBtn;

    @FXML
    private Button upvoteBtn;

    public QuestionCell(){
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/QuestionListCell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Question item, boolean empty) {
        super.updateItem(item, empty);

        if(empty || item == null) {
            setText(null);
//            setGraphic(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            questionText.setText(item.getContent());
            questionRating.setText(
                String.valueOf(item.getNumberOfUpvotes() - item.getNumberOfDownvotes()));

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
