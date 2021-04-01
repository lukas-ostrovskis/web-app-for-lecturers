package nl.tudelft.oopp.app.services;

import nl.tudelft.oopp.app.entities.Question;
import nl.tudelft.oopp.app.entities.User;
import nl.tudelft.oopp.app.repositories.QuestionRepository;
import nl.tudelft.oopp.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Question service.
 */
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    /**
     * Instantiates a new Question service.
     *
     * @param questionRepository the question repository
     * @param userRepository
     */
    @Autowired
    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
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
    public Question upvoteQuestionById(String questionId, String userId) {
        Question question = questionRepository.findById(questionId).get();
        if (contains(question.getDownvoters(),userId)){
            question.getDownvoters().remove(userRepository.getOne(userId));
            question.setNumberOfDownvotes(question.getNumberOfDownvotes()-1);
        }
        if (!contains(question.getUpvoters(),userId)){
            question.upvote();
            question.getUpvoters().add(userRepository.getOne(userId));
            questionRepository.save(question);
        } else {question.setNumberOfUpvotes(question.getNumberOfUpvotes()-1);}
        return question;
    }

    public boolean contains(final List<User> list, final String userId){
        return list.stream().filter(o -> o.getId().equals(userId)).findFirst().isPresent();
    }

    /**
     * Downvote question by id.
     *
     * @param questionId the question id
     */
    public Question downvoteQuestionById(String questionId, String userId) {
        Question question = questionRepository.findById(questionId).get();
        if (contains(question.getUpvoters(),userId)){
            question.getUpvoters().remove(userRepository.getOne(userId));
            question.setNumberOfUpvotes(question.getNumberOfUpvotes()-1);
        }
        if (!contains(question.getDownvoters(),userId)){
            question.downvote();
            question.getDownvoters().add(userRepository.getOne(userId));
            questionRepository.save(question);
        } else {question.setNumberOfDownvotes(question.getNumberOfDownvotes()-1);}
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
