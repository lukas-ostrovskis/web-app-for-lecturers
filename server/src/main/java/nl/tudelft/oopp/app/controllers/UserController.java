package nl.tudelft.oopp.app.controllers;

import java.util.*;
import java.util.List;

import nl.tudelft.oopp.app.DatabaseLoader;
import nl.tudelft.oopp.app.entities.User;
import nl.tudelft.oopp.app.repositories.UserRepository;
import nl.tudelft.oopp.app.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final RoomService roomService;
    private final DatabaseLoader dataLoader;

    public List<String> passwords = new ArrayList<String>(Arrays.asList("12345"));
    @Autowired
    private UserRepository repository;

    public UserController(RoomService roomService, DatabaseLoader dataLoader, UserRepository repository) {
        this.roomService = roomService;
        this.dataLoader = dataLoader;
        this.repository = repository;
    }

    @GetMapping("/searchOrAdd")
    public User searchUsers(@RequestParam String email, @RequestParam String password, @RequestParam int role, @RequestParam String roomId) {
        dataLoader.loadUsers();
        User empty = new User(null, null, null, null);
        if (role == 1){
            if(repository.findAllByEmailContains(email).size() == 0) {
                User user = new User(null, email, "student", null);
                repository.save(user);
                roomService.joinRoom(roomId, user);
                return user;
            }
            User user = repository.findByEmail(email);
            return user;
        }
        if(role == 2) {
            if(repository.findAllByEmailContains(email).size() != 0){
                if(passwords.contains(password)){
                    User user = repository.findByEmail(email);
                    return user;
                }
                return empty;
            }
            User user = new User(null, email, "moderator", null);
            if(passwords.contains(password)){
                repository.save(user);
                return user;
            }
            return empty;
        }
        if(role == 3) {
            if(repository.findAllByEmailContains(email).size() != 0){
                if(passwords.contains(password)){
                    User user = repository.findByEmail(email);
                    return user;
                }
                return empty;
            }
            return empty;
        }
        return empty;
    }



}
