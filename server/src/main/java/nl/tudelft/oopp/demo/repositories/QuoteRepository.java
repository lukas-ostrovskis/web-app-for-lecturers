package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Quote repository.
 */
public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
