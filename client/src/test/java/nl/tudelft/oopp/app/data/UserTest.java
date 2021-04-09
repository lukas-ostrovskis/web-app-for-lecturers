package nl.tudelft.oopp.app.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class UserTest {

    private static User user;
    private static User user1;

    @BeforeAll
    static void setUp() {
        String id = "1234AB";
        String name = "John Doe";
        String email = "abc@gmail.com";
        String role = "lecturer";
        String ip = "123.456.678";
        user = new User(id, name, email, role, ip);
        user1 = new User(id, name, email, role, ip);
    }


    @Test
    public void constructorTest() {
        assertNotNull(user);
    }

    @Test
    public void getIdTest() {
        assertEquals("1234AB", user.getId());
    }

    @Test
    public void getNameTest() {
        assertEquals("John Doe", user.getName());
    }

    @Test
    public void getEmailTest() {
        assertEquals("abc@gmail.com", user.getEmail());
    }

    @Test
    public void getRoleTest() {
        assertEquals("lecturer", user.getRole());
    }

    @Test
    public void getIpTest() {
        assertEquals("123.456.678", user.getIp());
    }

    @Test
    public void equalsTest() {
        assertTrue(user.equals(user1));
    }

}
