package nl.tudelft.oopp.demo;

import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabaseLoader {

    public DatabaseLoader(UserRepository repository){
        User u1 = new User(
                "10", "Martin", "martin@martin.martin", "god", "69.69.122.0"
        );
        User u2 = new User(
                "10", "ayyythan", "athan@martin.martin", "pleb", "69.69.122.0"
        );

        repository.save(u1);
        repository.save(u2);
    }
}
