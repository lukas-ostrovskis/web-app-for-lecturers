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
    private QuestionRepository questionRepository;
    public List<String> passwords = List.of("12345", "0000");

    @Autowired
    public UserService(UserRepository userRepository, IpBlacklistRepository ipBlacklistRepository, QuestionRepository questionRepository) {
        this.userRepo = userRepository;
        this.ipBlacklistRepository = ipBlacklistRepository;
        this.questionRepository = questionRepository;
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
        else if (user.getRole().equals("lecturer")) {

            // Check if that E-Mail is already registered and if the password is valid
            if (userRepo.existsByEmail(user.getEmail()) && passwords.contains(password)) {
                System.out.println("Logging in " + user.getEmail());
                return userRepo.findByEmail(user.getEmail());
            }

            // Otherwise, return null
            return null;
        }

        // if the user is a moderator
        else if(user.getRole().equals("moderator")) {
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

    /**
     * Checks whether an ip address is in the server's blacklist
     * @param Ip of the user
     * @return true if the IP address is in the blacklist, false if it's not
     */
    public boolean isBanned(String Ip){
        if(ipBlacklistRepository.existsByIp(Ip)) {
            return true;
        }
        return false;
    }

    public List<User> findAllByEmailContains(String email) {
        return userRepo.findAllByEmailContains(email);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void banUser(String questionId) {
        Question question = questionRepository.findById(questionId).get();
        String userIp = userRepo.findById(question.getOwnerId()).get().getIp();
        ipBlacklistRepository.save(new IpBlacklist(userIp));
    }
}
