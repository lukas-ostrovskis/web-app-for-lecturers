package nl.tudelft.oopp.demo.controllers;

import java.util.*;
import java.util.List;

import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    public List<String> passwords = new ArrayList<String>(Arrays.asList("12345", "0000"));
    @Autowired
    private UserRepository repository;

    @GetMapping("/searchOrAdd")
    public List<User> searchUsers(@RequestParam String email, @RequestParam String password, HttpServletRequest request) {

        List<User> result = repository.findAllByEmailContains(email);

        String ipAddress = request.getRemoteAddr(); // obtains ip address of user, it is being obtained
                                                    // before accessing the database to check whether the user's
                                                    // address is in the banlist or if it's a new ip address for him
        if(result.isEmpty()){

            User user = new User(null, email, "student", ipAddress);
            if(passwords.contains(password)){
                user.setRole("lecturer");
            }
            repository.save(user);
        }

        else {                                      // check whether the ip is the same, updates it if it's not
            System.out.println(ipAddress + " " + result.get(0).getIp());
            if (!(ipAddress.equals(result.get(0).getIp()))) {
                result.get(0).setIp(ipAddress);
            }
        }

        if(passwords.contains(password)){
            //to be added
        }
        return repository.findAllByEmailContains(email);
    }

}
