package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q WHERE q.roomId = ?1")
    List<Question> findAllByRoomId(String roomId);
}
