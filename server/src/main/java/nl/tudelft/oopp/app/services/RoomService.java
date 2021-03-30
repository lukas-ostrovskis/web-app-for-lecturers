package nl.tudelft.oopp.app.services;

import nl.tudelft.oopp.app.entities.Room;
import nl.tudelft.oopp.app.entities.User;
import nl.tudelft.oopp.app.repositories.RoomRepository;
import nl.tudelft.oopp.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomService {

    private RoomRepository roomRepository;
    private UserRepository userRepository;

    public List<String> passwords = List.of("12345");

    @Autowired
    public RoomService(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    /**
     * Saves a room in the database.
     * @param userId - the room to save
     * @param password of the user
     */
    public String createRoom(String userId, String password) {

        // Check if password is a valid password
        if (!passwords.contains(password)) {
            return null;
        }

        // Generate random id
        String randomId;
        do {
            randomId = Double.toString(Math.floor(Math.random() * 100000)).replace(".0", "");
        } while (roomRepository.findById(userId).isPresent());

        // Create room from id
        Room room = new Room(randomId, userId, true, 0);
        room.addUser(userRepository.findById(userId).get());

        // Add the room to DB and return its id to client
        roomRepository.save(room);
        System.out.println("Saved room: " + room.getId());
        return randomId;

    }


    /**
     * Allows the client to join a room with a specific id if it exists in the database.
     * @param roomId - id of the room that the client is trying to access.
     */
    public void joinRoom(String roomId, User user) {
        Optional<Room> roomById = roomRepository.findById(roomId);

        if(roomById.isPresent()) {
            //TODO: pass real user info to the database
            Random id = new Random(System.currentTimeMillis());

            roomById.get().addUser(user);
            roomRepository.save(roomById.get());
        }
        else throw new IllegalStateException("Room doesn't exist");
    }

    /**
     * Deletes a room from the database.
     * @param userId - the id of the room to delete.
     */
    public String deleteRoomByOwnerId(String userId) {

        Optional<Room> roomById = roomRepository.findByOwnerId(userId);

        if(roomById.isEmpty()) {
            return null;
        }

        roomRepository.delete(roomById.get());
        return "Deleted successfully!";
    }


    /**
     * Archives a room by changing its status to offline and updating the status on the database.
     * @param roomId - the id of the room to archive.
     */
    public void archiveRoom(String roomId) {
        Optional<Room> roomById = roomRepository.findById(roomId);

        if(roomById.isPresent()) {
            roomById.get().setStatus(false);
            roomRepository.save(roomById.get());
        }
        else throw new IllegalStateException("Room doesn't exist");
    }
}
