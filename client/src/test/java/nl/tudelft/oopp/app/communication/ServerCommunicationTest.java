package nl.tudelft.oopp.app.communication;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


public class ServerCommunicationTest {

    @Test
    public void testRoomCreate() {
        assertNotNull(ServerCommunication.getRoomId());
    }
}
