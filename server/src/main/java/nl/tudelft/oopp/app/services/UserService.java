package nl.tudelft.oopp.app.services;

import java.util.List;
import nl.tudelft.oopp.app.entities.IpBlacklist;
import nl.tudelft.oopp.app.entities.Question;
import nl.tudelft.oopp.app.entities.User;
import nl.tudelft.oopp.app.repositories.IpBlacklistRepository;
import nl.tudelft.oopp.app.repositories.QuestionRepository;
import nl.tudelft.oopp.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    public List<String> passwords = List.of("12345", "0000");
    private final UserRepository userRepo;
    private final IpBlacklistRepository ipBlacklistRepository;
    private final QuestionRepository questionRepository;

    /**
     * Constructor for UserService.
     * @param userRepository - User repository
     * @param ipBlacklistRepository - Blacklist repository
     * @param questionRepository - Question repository
     */
    @Autowired
    public UserService(UserRepository userRepository, IpBlacklistRepository ipBlacklistRepository,
                       QuestionRepository questionRepository) {
        this.userRepo = userRepository;
        this.ipBlacklistRepository = ipBlacklistRepository;
        this.questionRepository = questionRepository;
    }

    /**
     * Saves a user in the databse.
     * @param user - information about the user is in this class
     * @param password - password is required if the user has a lecturer/moderator role
     * @return
     */
    public User save(User user, String password) {

        // If the user's ip is blacklisted, user can't join rooms
        if (ipBlacklistRepository.existsByIp(user.getIp())) {
            return null;
        }

        // If the user is a student, check if that email is already used
        if (user.getRole().equals("student")) {
            if (userRepo.existsByEmail(user.getEmail())) {
                System.out.println("Logging in " + user.getEmail());
                return userRepo.findByEmail(user.getEmail());
            }
        } else if (user.getRole().equals("lecturer")) {

            // Check if that E-Mail is already registered and if the password is valid
            if (userRepo.existsByEmail(user.getEmail()) && passwords.contains(password)) {
                System.out.println("Logging in " + user.getEmail());
                return userRepo.findByEmail(user.getEmail());
            }

            // Otherwise, return null
            return null;
        } else if (user.getRole().equals("moderator")) {
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
     * Checks whether an ip address is in the server's blacklist.
     *
     * @param ip of the user
     * @return true if the IP address is in the blacklist, false if it's not
     */
    public boolean isBanned(String ip) {
        return ipBlacklistRepository.existsByIp(ip);
    }

    public List<User> findAllByEmailContains(String email) {
        return userRepo.findAllByEmailContains(email);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    /**
     * The method puts the Ip address of the question's owner to the blacklist.
     * @param questionId - takes the questionId as a parameter
     */
    public void banUser(String questionId) {
        Question question = questionRepository.findById(questionId).get();
        String userIp = userRepo.findById(question.getOwnerId()).get().getIp();
        ipBlacklistRepository.save(new IpBlacklist(userIp));
    }
}
