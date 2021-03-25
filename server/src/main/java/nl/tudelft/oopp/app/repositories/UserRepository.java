package nl.tudelft.oopp.app.repositories;

import nl.tudelft.oopp.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByEmailContains(String query);
}

