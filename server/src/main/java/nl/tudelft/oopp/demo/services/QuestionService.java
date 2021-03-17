package nl.tudelft.oopp.demo.services;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Question service.
 */
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    /**
     * Instantiates a new Question service.
     *
     * @param questionRepository the question repository
     */
    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Add question.
     *
     * @param question the question
     */
    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    /**
     * Gets all question by room id.
     *
     * @param roomId the room id
     * @return the all question by room id
     */
    public List<Question> getAllQuestionByRoomId(String roomId) {
        return questionRepository.findAllByRoomId(roomId);
    }

    /**
     * Gets all question by owner id.
     *
     * @param ownerId the owner id
     * @return the all question by owner id
     */
    public List<Question> getAllQuestionByOwnerId(String ownerId) {
        return questionRepository.findAllByOwnerId(ownerId);
    }

    /**
     * Upvote question by id.
     *
     * @param questionId the question id
     */
    public void upvoteQuestionById(String questionId) {
        Question question = questionRepository.getOne(questionId);
        question.upvote();
        questionRepository.save(question);
    }

    /**
     * Downvote question by id.
     *
     * @param questionId the question id
     */
    public void downvoteQuestionById(String questionId) {
        Question question = questionRepository.getOne(questionId);
        question.downvote();
        questionRepository.save(question);
    }

    /**
     * Toggle question status.
     *
     * @param questionId the question id
     */
    public void toggleQuestionStatus(String questionId) {
        Question question = questionRepository.getOne(questionId);
        if (question.isStatus()) {
            question.setStatusFalse();
        } else {
            question.setStatusTrue();
        }
        questionRepository.save(question);
    }

    /**
     * Delete question.
     *
     * @param questionId the question id
     */
    public void deleteQuestion(String questionId) {
        questionRepository.deleteById(questionId);
    }
}
