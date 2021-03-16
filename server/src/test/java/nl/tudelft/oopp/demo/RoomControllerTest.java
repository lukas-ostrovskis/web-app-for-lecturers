package nl.tudelft.oopp.demo;

import nl.tudelft.oopp.demo.controllers.RoomController;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoomControllerTest {

    private RoomController sut;

    @Test
    public void createRoomTest() {
        assertNotNull(sut.createRoom());
    }
}
