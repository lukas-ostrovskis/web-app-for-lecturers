package nl.tudelft.oopp.app.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import nl.tudelft.oopp.app.entities.Room;
import nl.tudelft.oopp.app.entities.User;

import nl.tudelft.oopp.app.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("room")
public class RoomController {

    private final Random id = new Random(System.currentTimeMillis());
    private final RoomService roomService;

    /**
     * Constructor for the RoomController.
     *
     * @param roomService service layer class for room actions.
     */
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Create a new room on the server and save it in a database.
     *
     * @return roomId - the id of the created room.
     */
    @GetMapping("/create")
    public String createRoom(@RequestParam String userId, @RequestParam String password) {
        return roomService.createRoom(userId, password);
    }


    /**
     * Allows a client to join an existing room with a specific id.
     *
     * @param roomId - the id of the room.
     * @return to the client the response of the server of joining a room
     */
    @PutMapping("/join")
    public String joinRoom(@RequestParam String roomId, @RequestParam String userId) {

        //System.out.println("vlizame v room controller-a ?");
        //System.out.println("test we: room id: " + roomId);
        //System.out.println("test we: user id: " + userId);

        // Check whether the room is expired
        // if it is, return empty string.

        roomService.joinRoom(roomId, userId);
        return "Joined successfully!";
    }

    /**
     * Delete the room from the database.
     *
     * @param userId - the id of the room.
     */
    @GetMapping("/delete")
    public String deleteRoom(@RequestParam String userId) {
        return roomService.deleteRoomByOwnerId(userId);
    }


    /**
     * Archive the room by changing its status to offline while keeping it on the database.
     *
     * @param id - the id of the room.
     */
    @PutMapping("archive")
    public void archiveRoom(@RequestParam String id) {
        roomService.archiveRoom(id);
    }

}
