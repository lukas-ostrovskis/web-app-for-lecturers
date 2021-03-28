package nl.tudelft.oopp.app.services;

import nl.tudelft.oopp.app.entities.Room;
import nl.tudelft.oopp.app.entities.User;
import nl.tudelft.oopp.app.repositories.RoomRepository;
import nl.tudelft.oopp.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

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
     * @param roomId - the id of the room to delete.
     */
    public void deleteRoom(String roomId) {
        Optional<Room> roomById = roomRepository.findById(roomId);

        if(roomById.isPresent()) {
            roomRepository.delete(roomById.get());
        }
        else throw new IllegalStateException("Room doesn't exist");
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
