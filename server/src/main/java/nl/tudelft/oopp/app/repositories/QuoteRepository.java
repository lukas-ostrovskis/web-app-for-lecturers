package nl.tudelft.oopp.app.repositories;

import nl.tudelft.oopp.app.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Quote repository.
 */
public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
