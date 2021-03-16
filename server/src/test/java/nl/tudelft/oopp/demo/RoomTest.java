package nl.tudelft.oopp.demo;


import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private Room room;
    private Room room2;
    private String id = "ABC123";
    private String ownerId = "XYZ789";
    private boolean status = true;
    private int time = 360;
    private List<User> roomUsers = new ArrayList<User>();

    @BeforeEach
    void SetUp() {
        room = new Room(id,ownerId,status,time);
    }

    @Test
    void constructorTest() {
        assertNotNull(room);
    }

    @Test
    void equalsTest() {
        assertTrue(room.equals(room));
    }

    @Test
    void equalsNotTest() {
        String id2 = "BCD234";
        room2 = new Room(id2,ownerId,status,time);

        assertFalse(room.equals(room2));
    }
}
