package nl.tudelft.oopp.demo;

import nl.tudelft.oopp.demo.entities.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void constructorTest(){
        String id = "1234AB";
        String name = "John Doe";
        String email = "abc@gmail.com";
        String role = "lecturer";
        String ip = "123.456.678";
        User user = new User(id,name,email,role,ip);
        assertNotNull(user);
    }
    @Test
    public void getIDTest(){
        String id = "1234AB";
        String name = "John Doe";
        String email = "abc@gmail.com";
        String role = "lecturer";
        String ip = "123.456.678";
        User user = new User(id,name,email,role,ip);
        assertEquals("1234AB", user.getId());
    }
    @Test
    public void getNameTest(){
        String id = "1234AB";
        String name = "John Doe";
        String email = "abc@gmail.com";
        String role = "lecturer";
        String ip = "123.456.678";
        User user = new User(id,name,email,role,ip);
        assertEquals("John Doe", user.getName());
    }
    @Test
    public void getEmailTest(){
        String id = "1234AB";
        String name = "John Doe";
        String email = "abc@gmail.com";
        String role = "lecturer";
        String ip = "123.456.678";
        User user = new User(id,name,email,role,ip);
        assertEquals("abc@gmail.com", user.getEmail());
    }
    @Test
    public void getRoleTest(){
        String id = "1234AB";
        String name = "John Doe";
        String email = "abc@gmail.com";
        String role = "lecturer";
        String ip = "123.456.678";
        User user = new User(id,name,email,role,ip);
        assertEquals("lecturer", user.getRole());
    }
    @Test
    public void getIPTest(){
        String id = "1234AB";
        String name = "John Doe";
        String email = "abc@gmail.com";
        String role = "lecturer";
        String ip = "123.456.678";
        User user = new User(id,name,email,role,ip);
        assertEquals("123.456.678", user.getIp());
    }

    @Test
    public void equalsTest(){
        String id = "1234AB";
        String name = "John Doe";
        String email = "abc@gmail.com";
        String role = "lecturer";
        String ip = "123.456.678";
        User user = new User(id,name,email,role,ip);
        String id1 = "1234AB";
        String name1 = "John Doe";
        String email1 = "abc@gmail.com";
        String role1 = "lecturer";
        String ip1 = "123.456.678";
        User user1 = new User(id,name,email,role,ip);
        assertTrue(user.equals(user1));
    }

}
