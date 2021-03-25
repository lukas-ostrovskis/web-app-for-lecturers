package nl.tudelft.oopp.demo.services;

import nl.tudelft.oopp.demo.entities.Quiz;
import nl.tudelft.oopp.demo.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizService {

    private QuizRepository quizRepository;


    /**
     * Instantiates a new Quiz Service.
     *
     * @param quizRepository the quiz repository.
     */
    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    /**
     * Add a quiz.
     *
     * @param quiz the quiz.
     */
    public void addQuiz(Quiz quiz) { quizRepository.save(quiz); }


    /**
     * Get all quizzes belonging to a certain room.
     *
     * @param roomId the room id.
     * @return a List of Quizzes.
     */
    public List<Quiz> getAllQuizzesByRoomId(String roomId) { return quizRepository.findAllQuizzesByRoomId(roomId); }

    /**
     * Get an open Quiz (if it exists) belonging to certain room.
     *
     * @param roomId the room id.
     * @return an open Quiz or null if it doesn't exist.
     */
    public Quiz getOpenQuizByRoomId(String roomId) {
        Quiz openQuizByRoomId = quizRepository.findOpenQuizByRoomId(roomId);
        return openQuizByRoomId;
    }

    /**
     * Count the client answer as a part of answer distribution.
     *
     * @param roomId the room id to which the quiz belongs.
     * @param answer the answer submitted by the user.
     */
    public void quizUserAnswer(String roomId, char answer) {
        Quiz quiz = getOpenQuizByRoomId(roomId);
        if(!quiz.answerDistribution.containsKey(answer)) {
            quiz.answerDistribution.put(answer, 1);
        }
        else {
            quiz.answerDistribution.put(answer, quiz.answerDistribution.get(answer)+1);
        }
    }

    /**
     * Get the answer distribution of a quiz in a certain room.
     *
     * @param roomId the room id to which the quiz belongs.
     * @return answerDistribution of a Quiz if there's one ready, null otherwise.
     */
    public Map<Character, Integer> getQuizAnswerDistribution(String roomId) {
        Quiz quiz = quizRepository.findQuizByRoomIdWhereAnswerDistributionIsReady(roomId);
        return quiz.answerDistribution;
    }

    /**
     * Toggle the open status of a Quiz.
     *
     * @param quizId the id of the Quiz.
     */
    public void toggleQuizOpenStatus(String quizId) {
        Quiz quizById = quizRepository.findById(quizId).get();
        Quiz openQuizByRoomId = quizRepository.findOpenQuizByRoomId(quizById.getRoomId());
        if(quizById != null && (openQuizByRoomId != null || openQuizByRoomId.equals(quizById))) {
                quizById.setOpen(!quizById.isOpen());
                quizRepository.save(quizById);
        }
    }
}
