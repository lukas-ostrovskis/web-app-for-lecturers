package nl.tudelft.oopp.app.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionTest {
    private Question questionA;
    private Question questionB;
    private Question questionC;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(
            "email",
            "teacher",
            "name"
        );
        questionA = new Question("666",
            "ownerId",
            "ownerName",
            "roomId",
            "content",
            666,
            666,
            true,
            "answer");
        questionB = new Question("1232asd",
            "41224as",
            "1212ads",
            "What is the time",
            10,
            3,
            false,
            "");
        questionC = new Question("123u0j",
            "n123n",
            "zma2m",
            "Is this going to be on the exam?",
            15,
            0,
            false,
            "");
    }

    @Test
    void getId() {
        assertEquals(questionA.getId(), "666");
    }

    @Test
    void getOwnerId() {
        assertEquals(questionA.getOwnerId(), "ownerId");
    }

    @Test
    void getRoomId() {
        assertEquals(questionA.getRoomId(), "roomId");
    }

    @Test
    void getContent() {
        assertEquals(questionA.getContent(), "content");
    }

    @Test
    void getNumberOfUpvotes() {
        assertEquals(questionA.getNumberOfUpvotes(), 666);
    }

    @Test
    void getNumberOfDownvotes() {
        assertEquals(questionA.getNumberOfDownvotes(), 666);
    }

    @Test
    void isStatus() {
        assertTrue(questionA.isStatus());
    }

    @Test
    void getAnswer() {
        assertEquals(questionA.getAnswer(), "answer");
    }

    @Test
    void upvote() {
        questionA.upvote();
        assertEquals(questionA.getNumberOfUpvotes(), 667);
    }

    @Test
    void downvote() {
        questionA.downvote();
        assertEquals(questionA.getNumberOfDownvotes(), 667);
    }

    @Test
    void getUpvoters() {
        List<User> q = questionA.getUpvoters();
        assertNull(q);
    }

    @Test
    void getDownvoters() {
        List<User> q = questionA.getDownvoters();
        assertNull(q);
    }

    @Test
    void setStatusFalse() {
        assertTrue(questionA.isStatus());
        questionA.setStatusFalse();
        assertFalse(questionA.isStatus());
    }

    @Test
    void setStatusTrue() {
        assertFalse(questionB.isStatus());
        questionB.setStatusTrue();
        assertTrue(questionB.isStatus());
    }

    @Test
    void setCreationTimestamp() {
        Instant now = Instant.now();
        questionA.setCreationTimestamp(now);
        assertEquals(questionA.getCreationTimestamp(), now);
    }

    @Test
    void testEquals() {
        assertEquals(questionA, questionA);
    }

    @Test
    void getOwnerName() {
        assertEquals(questionA.getOwnerName(), "ownerName");
    }

    @Test
    void setUpvoters() {
        questionA.setUpvoters(List.of(user));
        assertEquals(questionA.getUpvoters(), List.of(user));
    }

    @Test
    void setDownvoters() {
        questionA.setDownvoters(List.of(user));
        assertEquals(questionA.getDownvoters(), List.of(user));
    }

    @Test
    void defaultConstructor() {
        assertNotNull(new Question());
    }
}