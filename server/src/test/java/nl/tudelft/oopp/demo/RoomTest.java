package nl.tudelft.oopp.demo.entities;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private Room room;
    private Room room2;
    private String Id = "ABC123";
    private String ownerId = "XYZ789";
    private boolean status = true;
    private int time = 360;
    private List<User> roomUsers = new ArrayList<User>();

    @BeforeEach
    void SetUp() {
        room = new Room(Id,ownerId,status,time,roomUsers);
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
        String Id2 = "BCD234";
        room2 = new Room(Id2,ownerId,status,time,roomUsers);

        assertFalse(room.equals(room2));
    }
}
