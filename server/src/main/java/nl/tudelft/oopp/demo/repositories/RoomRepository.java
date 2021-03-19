package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Room repository.
 */
public interface RoomRepository extends JpaRepository<Room, String> {
}
