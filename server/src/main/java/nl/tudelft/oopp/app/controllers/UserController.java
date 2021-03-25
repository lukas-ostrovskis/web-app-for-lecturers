package nl.tudelft.oopp.app.controllers;

import java.util.*;
import java.util.List;

import nl.tudelft.oopp.app.entities.User;
import nl.tudelft.oopp.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    public List<String> passwords = new ArrayList<String>(Arrays.asList("12345", "0000"));
    @Autowired
    private UserRepository repository;

    @GetMapping("/searchOrAdd")
    public List<User> searchUsers(@RequestParam String email, @RequestParam String password) {

        if(repository.findAllByEmailContains(email).size() == 0){
            User user = new User(null, email, "student", null);
            if(passwords.contains(password)){
                user.setRole("lecturer");
            }
            repository.save(user);
        }
        if(passwords.contains(password)){
            //to be added
        }
        return repository.findAllByEmailContains(email);
    }



}
