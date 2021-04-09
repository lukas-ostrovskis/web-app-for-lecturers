package nl.tudelft.oopp.app.repositories;

import nl.tudelft.oopp.app.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, String> {
}