package nl.tudelft.oopp.app.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.tudelft.oopp.app.entities.IpBlacklist;
import org.junit.jupiter.api.Test;



class IpBlacklistTest {
    @Test
    void getIp() {
        IpBlacklist ip = new IpBlacklist("2", "51.21.513.212");
        assertEquals(ip.getIp(), "51.21.513.212");
    }
}