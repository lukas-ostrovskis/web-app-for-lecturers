package nl.tudelft.oopp.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.app.entities.Room;
import nl.tudelft.oopp.app.entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class RoomTest {

    private static Room room;
    private static final String ownerId = "XYZ789";
    private static final boolean status = true;
    private static final int time = 360;
    private static final List<User> roomUsers = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        String id = "ABC123";
        room = new Room(id, ownerId, status, time);
    }

    @Test
    void constructorTest() {
        assertNotNull(room);
    }

    @Test
    void equalsTest() {
        assertEquals(room, room);
    }

    @Test
    void equalsNotTest() {
        String id2 = "BCD234";
        Room room2 = new Room(id2, ownerId, status, time);

        assertNotEquals(room2, room);
    }
}
