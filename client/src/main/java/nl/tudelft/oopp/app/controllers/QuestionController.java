package nl.tudelft.oopp.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import nl.tudelft.oopp.app.data.Question;

import java.awt.*;
import java.io.IOException;

public class QuestionController extends ListCell<Question> {

    @FXML
    private Label titleLabel;

    @FXML
    private Label commentLabel;

    @FXML
    private Label descriptionLabel;

    public QuestionController() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("task_cell.fxml"));
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
            titleLabel.setText(q.getOwnerId());
            commentLabel.setText(q.getAnswer());
            descriptionLabel.setText(q.getContent());

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}