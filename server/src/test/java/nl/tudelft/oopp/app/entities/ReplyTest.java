package nl.tudelft.oopp.app.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import nl.tudelft.oopp.app.entities.Reply;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;




public class ReplyTest {

    private static Reply reply1;
    private static Reply reply2;
    private static String id;
    private static String ownerId;
    private static String questionId;
    private static String content;
    private static int numberOfUpvotes;
    private static int numberOfDownvotes;
    private static final Instant creationTimestamp = Instant.now();

    @BeforeAll
    static void setUp() {
        id = "xScGkU9fyDCcLtkp";
        ownerId = "zwXK5JPGeaGeerSu";
        questionId = "BMjuKd75rp37unqu";
        content = "Lorem ipsum dolor sit amet";
        numberOfUpvotes = 69;
        numberOfDownvotes = 42;
        reply1 = new Reply(id, ownerId, questionId, content,
                numberOfUpvotes, numberOfDownvotes, creationTimestamp);
        reply2 = new Reply(id, ownerId, questionId, content,
                numberOfUpvotes, numberOfDownvotes, creationTimestamp);
    }

    @Test
    public void constructorTest() {
        assertNotNull(reply1);
    }

    @Test
    public void getIdTest() {
        assertEquals("xScGkU9fyDCcLtkp", reply1.getId());
    }

    @Test
    public void getOwnerIdTest() {
        assertEquals("zwXK5JPGeaGeerSu", reply1.getOwnerId());
    }

    @Test
    public void getQuestionIdTest() {
        assertEquals("BMjuKd75rp37unqu", reply1.getQuestionId());
    }

    @Test
    public void getContentTest() {
        assertEquals("Lorem ipsum dolor sit amet", reply1.getContent());
    }

    @Test
    public void getCreationTimestampTest() {
        assertEquals(creationTimestamp, reply1.getCreationTimestamp());
    }

    @Test
    public void getNumberOfUpvotesTest() {
        assertEquals(69, reply1.getNumberOfUpvotes());
    }

    @Test
    public void getNumberOfDownvotesTest() {
        assertEquals(42, reply1.getNumberOfDownvotes());
    }

    @Test
    public void equalsTest() {
        assertTrue(reply1.equals(reply2));
    }
}
