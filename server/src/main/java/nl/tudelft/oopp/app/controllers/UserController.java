package nl.tudelft.oopp.app.controllers;

import java.util.List;

import nl.tudelft.oopp.app.DatabaseLoader;
import nl.tudelft.oopp.app.entities.User;
import nl.tudelft.oopp.app.repositories.IpBlacklistRepository;
import nl.tudelft.oopp.app.repositories.QuestionRepository;
import nl.tudelft.oopp.app.services.RoomService;
import nl.tudelft.oopp.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RoomService roomService;

    private final DatabaseLoader dataLoader;
    public List<String> passwords = List.of("12345","0000");

    private QuestionRepository questionRepository;
    private IpBlacklistRepository ipBlacklistRepository;

    @Autowired
    public UserController(UserService userService, RoomService roomService, DatabaseLoader dataLoader) {
        this.userService = userService;
        this.roomService = roomService;
        this.dataLoader = dataLoader;
    }

    @GetMapping("/searchOrAdd")
    public User searchUsers(@RequestParam String email, @RequestParam String password, @RequestParam int role, @RequestParam String roomId) {

        dataLoader.loadUsers();
        User empty = new User(null, null, null, null);

        if (role == 1){

            if(userService.findAllByEmailContains(email).size() == 0) {
                User user = new User(null, email, "student", null);
                userService.save(user, null);
                roomService.joinRoom(roomId, user.getId());
                return user;
            }

            User user = userService.findByEmail(email);
            return user;
        }

        if(role == 2) {

            if(userService.findAllByEmailContains(email).size() != 0){

                if(passwords.contains(password)){

                    User user = userService.findByEmail(email);
                    return user;
                }
                else return empty;
            }
            User user = new User(null, email, "moderator", null);
            if(passwords.contains(password)){
                userService.save(user, password);
                return user;
            }
            else return empty;
        }

        if(role == 3) {
            if(userService.findAllByEmailContains(email).size() != 0){
                if(passwords.contains(password)){
                    User user = userService.findByEmail(email);
                    return user;
                }
                return empty;
            }
            return empty;
        }
        return empty;
    }

    @GetMapping("/isbanned")
    public boolean isBanned(@RequestParam String ipAddress) {
        return userService.isBanned(ipAddress);
    }


    @PostMapping ("/add")
    public User addUser(@RequestBody User user, @RequestParam String password) {
        return userService.save(user, password);
        //return "User Added Successfully";
    }

    @PostMapping("/ban")
    public String banUser(@RequestBody String questionId) {
        userService.banUser(questionId);
        return "User banned";
    }

}
