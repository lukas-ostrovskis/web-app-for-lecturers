package nl.tudelft.oopp.app.repositories;

import nl.tudelft.oopp.app.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, String> {
    /**
     * Find all quizzes by room id.
     *
     * @param roomId the room id.
     * @return a List of Quizzes.
     */
    @Query("SELECT q FROM Quiz q WHERE q.roomId = ?1")
    List<Quiz> findAllQuizzesByRoomId(String roomId);

    /**
     * Find an open quiz by room id.
     *
     * @param roomId the room id.
     * @return an Optional object of a Quiz (one might not exist).
     */
    @Query("SELECT q FROM Quiz q WHERE q.roomId = ?1 AND q.open = true")
    Quiz findOpenQuizByRoomId(String roomId);

    /**
     * Find a quiz with answer distribution ready to be published by room id.
     *
     * @param roomId the room id.
     * @return an Optional object of a Quiz (one might not exist).
     */
    @Query("SELECT q FROM Quiz q WHERE q.roomId = ?1 AND q.answerDistributionReady = true")
    Quiz findQuizByRoomIdWhereAnswerDistributionIsReady(String roomId);
}
