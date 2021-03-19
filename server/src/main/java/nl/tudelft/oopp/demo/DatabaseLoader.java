package nl.tudelft.oopp.demo;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Database loader.
 */
@Service
public class DatabaseLoader {
    private final QuestionService questionService;

    /**
     * Database loader.
     *
     * @param questionService the question service
     */
    @Autowired
    public DatabaseLoader(QuestionService questionService) {
        this.questionService = questionService;
//        loadQuestions();
    }

    /**
     * Load the test question to the database
     */
    public void loadQuestions() {
        Question q1 = new Question("1",
                "1",
                "First q",
                3,
                3,
                true,
                "yes");
        Question q2 = new Question("1",
                "1",
                "Second q",
                5,
                5,
                false,
                "yes smth");
        Question q3 = new Question("2",
                "2",
                "Third q",
                6,
                3,
                false,
                "my man");
        Question q4 = new Question("3",
                "3",
                "Forth q",
                7,
                7,
                true,
                "ma boi");
        Question q5 = new Question("4",
                "4",
                "Fifth q",
                8,
                1,
                true,
                "sup girl??/");
        questionService.addQuestion(q1);
        questionService.addQuestion(q2);
        questionService.addQuestion(q3);
        questionService.addQuestion(q4);
        questionService.addQuestion(q5);
    }
}
