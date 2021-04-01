package nl.tudelft.oopp.app.services;

import nl.tudelft.oopp.app.entities.IpBlacklist;
import nl.tudelft.oopp.app.entities.Question;
import nl.tudelft.oopp.app.entities.User;
import nl.tudelft.oopp.app.repositories.IpBlacklistRepository;
import nl.tudelft.oopp.app.repositories.QuestionRepository;
import nl.tudelft.oopp.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepo;
    private IpBlacklistRepository ipBlacklistRepository;
    public List<String> passwords = List.of("12345");

    @Autowired
    public UserService(UserRepository userRepository, IpBlacklistRepository ipBlacklistRepository) {
        this.userRepo = userRepository;
        this.ipBlacklistRepository = ipBlacklistRepository;
    }

    public User save(User user, String password) {

        // If the user's ip is blacklisted, user can't join rooms
        if(ipBlacklistRepository.existsByIp(user.getIp())) {
            return null;
        }

        // If the user is a student, check if that email is already used
        if (user.getRole().equals("student")) {
            if(userRepo.existsByEmail(user.getEmail())) {
                System.out.println("Logging in " + user.getEmail());
                return userRepo.findByEmail(user.getEmail());
            }
        }

        // If the user is a lecturer
        if (user.getRole().equals("lecturer")) {

            // Check if that E-Mail is already registered and if the password is valid
            if (userRepo.existsByEmail(user.getEmail()) && passwords.contains(password)) {
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

    public void banUser(Question question) {
        String userIp = userRepo.findById(question.getOwnerId()).get().getIp();
        ipBlacklistRepository.save(new IpBlacklist(userIp));
    }
}
