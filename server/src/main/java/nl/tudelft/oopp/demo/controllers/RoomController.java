package nl.tudelft.oopp.demo.controllers;


import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.services.RoomService;
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
    public String createRoom() {
        String roomId = Long.toString(id.nextLong(), 36);

        //TODO: pass a real ownerId and figure out a way to track time somehow
        Room room = new Room(roomId, "1", true, 0);
        roomService.addRoom(room);

        return roomId;
    }


    /**
     * Allows a client to join an existing room with a specific id.
     * @param roomId - the id of the room.
     * @return
     */
    @GetMapping(path = "{roomId}")
    public String joinRoom(@PathVariable("roomId") String roomId) {
        roomService.joinRoom(roomId);
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
