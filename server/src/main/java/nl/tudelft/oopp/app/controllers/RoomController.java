package nl.tudelft.oopp.app.controllers;


import nl.tudelft.oopp.app.entities.Room;
import nl.tudelft.oopp.app.entities.User;
import nl.tudelft.oopp.app.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("room")
public class RoomController {

    private Random id = new Random(System.currentTimeMillis());
    private RoomService roomService;

    /**
     * Constructor for the RoomController.
     * @param roomService service layer class for room actions.
     */
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Create a new room on the server and save it in a database.
     * @return roomId - the id of the created room.
     */
    @GetMapping("create")
    public String createRoom(@RequestParam String userId) {
        String roomId = Long.toString(id.nextLong(), 36);

        //TODO: pass a real ownerId and figure out a way to track time somehow
        Room room = new Room(roomId, userId, true, 0);
        roomService.addRoom(room);

        return roomId;
    }


    /**
     * Allows a client to join an existing room with a specific id.
     * @param roomId - the id of the room.
     * @return to the client the response of the server of joining a room
     */
    @GetMapping(path = "{roomId}")
    public String joinRoom(@PathVariable("roomId") String roomId, @RequestParam User user) {

        System.out.printf("User %s trying to join room %s", user.getId(), roomId);
        // Check whether the room is expired
        // if it is, return empty string.

        roomService.joinRoom(roomId, user);
        return "Joined successfully!";
    }

    /**
     * Delete the room from the database.
     * @param id - the id of the room.
     */
    @DeleteMapping("close")
    public void deleteRoom(@RequestParam String id) {
        roomService.deleteRoom(id);
    }


    /**
     * Archive the room by changing its status to offline while keeping it on the database.
     * @param id - the id of the room.
     */
    @PutMapping("archive")
    public void archiveRoom(@RequestParam String id) {
        roomService.archiveRoom(id);
    }

}
