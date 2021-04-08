package nl.tudelft.oopp.app;

import nl.tudelft.oopp.app.entities.Question;
import nl.tudelft.oopp.app.services.QuestionService;
import nl.tudelft.oopp.app.entities.Quiz;
import nl.tudelft.oopp.app.services.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.tudelft.oopp.app.entities.User;
import nl.tudelft.oopp.app.repositories.UserRepository;

/**
 * The type Database loader.
 */
@Service
public class DatabaseLoader {
    private final QuestionService questionService;
    private final QuizService quizService;
    private final UserRepository userRepository;

    /**
     * Database loader.
     *
     * @param questionService the question service
     */
    @Autowired
    public DatabaseLoader(QuestionService questionService, QuizService quizService, UserRepository userRepository) {
        this.questionService = questionService;
        this.quizService = quizService;
        this.userRepository = userRepository;
//        loadQuestions();
        loadUsers();
        loadQuizzes();
    }

    /**
     * Load the test quizzes to the database
     */
    private void loadQuizzes() {
        Quiz qz1 = new Quiz("aaaaaaa", "Question1", "AnswerA1", "AnswerA2", "AnswerA3", "AnswerA4", "AnswerA5", "AnswerA6", "AnswerA7", "AnswerA8", "AnswerA9", "AnswerA10", 'a');
        Quiz qz2 = new Quiz("bbbbbb", "Question2", "AnswerB1", "AnswerB2", "AnswerB3", "AnswerB4", "AnswerB5", "AnswerB6", "AnswerB7", "AnswerB8", "AnswerB9", "AnswerB10", 'b');
        Quiz qz3 = new Quiz("cccccccc", "Question3", "AnswerC1", "AnswerC2", "AnswerC3", "AnswerC4", "AnswerC5", "AnswerC6", "AnswerC7", "AnswerC8", "AnswerC9", "AnswerC10", 'c');
        Quiz qz4 = new Quiz("dddddd", "Question4", "AnswerD1", "AnswerD2", "AnswerD3", "AnswerD4", "AnswerD5", "AnswerD6", "AnswerD7", "AnswerD8", "AnswerD9", "AnswerD10", 'd');
        Quiz qz5 = new Quiz("eeeeee", "Question5", "AnswerE1", "AnswerE2", "AnswerE3", "AnswerE4", "AnswerE5", "AnswerE6", "AnswerE7", "AnswerE8", "AnswerE9", "AnswerE10", 'e');
        quizService.addQuiz(qz1);
        quizService.addQuiz(qz2);
        quizService.addQuiz(qz3);
        quizService.addQuiz(qz4);
        quizService.addQuiz(qz5);
    }

    /**
     * Load the test question to the database
     */
    public void loadQuestions() {
        Question q1 = new Question("1", "Petr","1", "First q", 3, 3, true, "yes");
        Question q2 = new Question("1","Athan", "1", "Second q", 5, 5, false, "yes smth");
        Question q3 = new Question("2", "Luka", "2", "Third q", 6, 3, false, "my man");
        Question q4 = new Question("3", "Lukas", "3", "Forth q", 7, 7, true, "ma boi");
        Question q5 = new Question("4", "David", "3", "Forth q", 7, 7, true, "ma boi");
        Question q6 = new Question("5","Martin", "4", "Fifth q", 8, 1, true, "sup girl??/");
        questionService.addQuestion(q1);
        questionService.addQuestion(q2);
        questionService.addQuestion(q3);
        questionService.addQuestion(q4);
        questionService.addQuestion(q5);
        questionService.addQuestion(q6);

    }

    public void loadUsers(){
        User u1 = new User("John", "john", "lecturer", "69.69.122.0"
        );
        User u2 = new User(
            "10", "Athan", "athan@gmail.com", "moderator", "69.69.122.0"
        );
        User u3 = new User("1", "William", "email", "lecturer", "1234");

        userRepository.save(u1);
        userRepository.save(u2);
    }
}
