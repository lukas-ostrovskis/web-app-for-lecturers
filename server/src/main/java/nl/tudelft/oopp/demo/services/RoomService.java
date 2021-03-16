package nl.tudelft.oopp.demo.services;

import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    private RoomRepository roomRepository;
    private UserRepository userRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    /**
     * Saves a room in the database.
     * @param room - the room to save.
     */
    public void addRoom(Room room) {
        roomRepository.save(room);
    }


    /**
     * Allows the client to join a room with a specific id if it exists in the database.
     * @param roomId - id of the room that the client is trying to access.
     */
    public void joinRoom(String roomId) {
        Optional<Room> roomById = roomRepository.findById(roomId);

        User user = new User("1", "TestName", "test@testmail.com", "student", "24.241.241.21.24");

        if(roomById.isPresent()) {
            roomById.get().addUser(user);
        }
        else throw new IllegalStateException("Room doesn't exist");
    }
}
