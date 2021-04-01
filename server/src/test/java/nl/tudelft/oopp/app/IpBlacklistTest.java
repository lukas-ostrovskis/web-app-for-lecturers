package nl.tudelft.oopp.app;

import nl.tudelft.oopp.app.entities.IpBlacklist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IpBlacklistTest {
    @Test
    void getIp() {
        IpBlacklist ip = new IpBlacklist("2","51.21.513.212");
        assertEquals(ip.getIp(), "51.21.513.212");
    }
}