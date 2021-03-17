package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface Question repository.
 */
public interface QuestionRepository extends JpaRepository<Question, String> {
    /**
     * Find all questions by room ID
     *
     * @param roomId the room id
     * @return the list
     */
    @Query("SELECT q FROM Question q WHERE q.roomId = ?1")
    List<Question> findAllQuestionsByRoomId(String roomId);

    /**
     * Find all questions by owner ID.
     *
     * @param ownerId the owner id
     * @return the list
     */
    @Query("SELECT q FROM Question q WHERE q.ownerId = ?1")
    List<Question> findAllQuestionsByOwnerId(String ownerId);
}
