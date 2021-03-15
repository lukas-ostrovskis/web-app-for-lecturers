package nl.tudelft.oopp.demo.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("room")
public class RoomController {

    private Random id = new Random(System.currentTimeMillis());

    @GetMapping
    public String createRoom() {
        return Long.toString(id.nextLong(), 36);
    }

    @GetMapping(path = "{roomId}")
    public String joinRoom(@PathVariable("roomId") String roomId) {
        return "Joined successfully!";
    }
}
