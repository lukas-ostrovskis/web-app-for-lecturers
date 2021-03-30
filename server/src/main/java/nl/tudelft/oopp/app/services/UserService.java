package nl.tudelft.oopp.app.services;

import nl.tudelft.oopp.app.entities.User;
import nl.tudelft.oopp.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepo;
    public List<String> passwords = List.of("12345");

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    public User save(User user, String password) {

        // If the user is a lecturer, check the password combination first
        if (user.getRole().equals("lecturer") && passwords.contains(password)) {

            // Then see whether this lecturer already exists, and return its entry
            if (userRepo.existsByEmail(user.getEmail())) {
                System.out.println("Logging in " + user.getEmail());
                return userRepo.findByEmail(user.getEmail());
            }

            // Otherwise, return null
            return null;
        }

        System.out.println("Saving user: " + user.toString());
        return userRepo.save(user);
    }

    public List<User> findAllByEmailContains(String email) {
        return userRepo.findAllByEmailContains(email);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
