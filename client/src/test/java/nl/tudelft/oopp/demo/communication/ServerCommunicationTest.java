package nl.tudelft.oopp.demo.communication;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import nl.tudelft.oopp.app.communication.ServerCommunication;
import org.junit.jupiter.api.Test;


public class ServerCommunicationTest {

    @Test
    public void testRoomCreate() {
        assertNotNull(ServerCommunication.getRoomId());
    }
}
