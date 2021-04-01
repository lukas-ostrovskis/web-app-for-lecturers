package nl.tudelft.oopp.app.repositories;

import nl.tudelft.oopp.app.entities.IpBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpBlacklistRepository extends JpaRepository<IpBlacklist, String> {

    boolean existsByIp(String ip);
}
