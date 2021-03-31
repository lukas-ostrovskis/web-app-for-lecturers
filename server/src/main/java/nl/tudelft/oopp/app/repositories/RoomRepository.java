package nl.tudelft.oopp.app.repositories;

import nl.tudelft.oopp.app.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Room repository.
 */
public interface RoomRepository extends JpaRepository<Room, String> {
    Optional<Room> findByOwnerId(String userId);
}
