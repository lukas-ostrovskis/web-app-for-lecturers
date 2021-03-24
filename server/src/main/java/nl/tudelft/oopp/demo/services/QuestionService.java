package nl.tudelft.oopp.demo.services;

import javax.transaction.Transactional;
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
        return questionRepository.findAllQuestionsByRoomId(roomId);
    }

    /**
     * Gets all question by owner id.
     *
     * @param ownerId the owner id
     * @return the all question by owner id
     */
    public List<Question> getAllQuestionByOwnerId(String ownerId) {
        return questionRepository.findAllQuestionsByOwnerId(ownerId);
    }

    /**
     * Upvote question by id.
     *
     * @param questionId the question id
     */
    public Question upvoteQuestionById(String questionId) {
        Question question = questionRepository.findById(questionId).get();
        question.upvote();
        questionRepository.save(question);
        return question;
    }

    /**
     * Downvote question by id.
     *
     * @param questionId the question id
     */
    public Question downvoteQuestionById(String questionId) {
        Question question = questionRepository.findById(questionId).get();
        question.downvote();
        questionRepository.save(question);
        return question;
    }

    /**
     * Toggle question status.
     *
     * @param questionId the question id
     */
    public Question toggleQuestionStatus(String questionId) {
        Question question = questionRepository.findById(questionId).get();
        if (question.isStatus()) {
            question.setStatusFalse();
        } else {
            question.setStatusTrue();
        }
        questionRepository.save(question);
        return question;
    }

    public Question getOneQuestionById(String questionId){
        return questionRepository.findById(questionId).get();

    }

    /**
     * Delete question.
     *
     * @param questionId the question id
     */
    public void deleteQuestion(String questionId) {
        questionRepository.deleteById(questionId);
    }

    public void deleteAllQuestionByRoomId(String roomId){
        questionRepository.removeByRoomId(roomId);
    }
}
