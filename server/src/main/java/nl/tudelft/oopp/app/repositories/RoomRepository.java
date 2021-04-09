package nl.tudelft.oopp.app.repositories;

import java.util.Optional;
import nl.tudelft.oopp.app.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * The interface Room repository.
 */
public interface RoomRepository extends JpaRepository<Room, String> {
    Optional<Room> findByOwnerId(String userId);
}
