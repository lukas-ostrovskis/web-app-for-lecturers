package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, String> {}