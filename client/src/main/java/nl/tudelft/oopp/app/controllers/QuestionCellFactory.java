package nl.tudelft.oopp.app.controllers;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import nl.tudelft.oopp.app.data.Question;

public class QuestionCellFactory implements Callback<ListView<Question>, ListCell<Question>> {

    @Override
    public ListCell<Question> call(ListView<Question> param) {
        return new QuestionCell();
    }
}