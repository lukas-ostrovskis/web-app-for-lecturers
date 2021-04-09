package nl.tudelft.oopp.app.repositories;

import java.util.List;
import nl.tudelft.oopp.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByEmailContains(String query);

    User findByEmail(String email);

    boolean existsByEmail(String email);
}

