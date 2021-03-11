package nl.tudelft.oopp.demo;

import nl.tudelft.oopp.demo.entities.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private static User user;
    private static User user1;
    private static String id;
    private static String name;
    private static String email;
    private static String role;
    private static String ip;

    @BeforeAll
    static void init() {
        id = "1234AB";
        name = "John Doe";
        email = "abc@gmail.com";
        role = "lecturer";
        ip = "123.456.678";
        user = new User(id, name, email, role, ip);
        user1 = new User(id, name, email, role, ip);
    }


    @Test
    public void constructorTest(){
        assertNotNull(user);
    }
    @Test
    public void getIDTest(){
        assertEquals("1234AB", user.getId());
    }
    @Test
    public void getNameTest(){
        assertEquals("John Doe", user.getName());
    }
    @Test
    public void getEmailTest(){
        assertEquals("abc@gmail.com", user.getEmail());
    }
    @Test
    public void getRoleTest(){
        assertEquals("lecturer", user.getRole());
    }
    @Test
    public void getIPTest(){
        assertEquals("123.456.678", user.getIp());
    }

    @Test
    public void equalsTest(){
        assertTrue(user.equals(user1));
    }

}
