package nl.tudelft.oopp.demo.controllers;


import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
