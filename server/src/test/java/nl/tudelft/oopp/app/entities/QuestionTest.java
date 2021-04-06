package nl.tudelft.oopp.app.entities;

import static org.junit.jupiter.api.Assertions.*;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.time.Instant;
import java.util.List;
import nl.tudelft.oopp.app.repositories.QuestionRepository;
import nl.tudelft.oopp.app.services.QuestionService;
import nl.tudelft.oopp.app.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.querydsl.QuerydslRepositoryInvokerAdapter;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest
class QuestionTest {
    private Question questionA;
    private Question questionB;
    private Question questionC;

    @Autowired
    private QuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
        questionRepository.deleteAll();
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
    public void saveAndRetrieveQuote() throws JsonProcessingException {
        questionRepository.save(questionA);
        Question questionFromDB = questionRepository.findAll().get(0);

        ObjectMapper objectMapper = new ObjectMapper();
        assertTrue(questionA.equals(questionFromDB));

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
    void setNumberOfUpvotes() {
        questionB.setNumberOfUpvotes(6666);
        assertEquals(questionB.getNumberOfUpvotes(), 6666);
    }

    @Test
    void setNumberOfDownvotes() {
        questionB.setNumberOfDownvotes(7777);
        assertEquals(questionB.getNumberOfDownvotes(), 7777);
    }

    @Test
    void downvote() {
        questionA.downvote();
        assertEquals(questionA.getNumberOfDownvotes(), 667);
    }

    @Test
    void getUpvoters() {
        questionRepository.save(new Question(
            "testOwnerId",
            "ownerName",
            "roomId",
            "content",
            121,
            121,
            true,
            "answer"
        ));

        List<Question> q = questionRepository.findAllQuestionsByOwnerId("testOwnerId");

        assertNull(q.get(0).getUpvoters());
    }

    @Test
    void getDownvoters() {
        questionRepository.save(new Question(
            "testOwnerId",
            "ownerName",
            "roomId",
            "content",
            121,
            121,
            true,
            "answer"
        ));

        List<Question> q = questionRepository.findAllQuestionsByOwnerId("testOwnerId");

        assertNull(q.get(0).getDownvoters());
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
    void getCreationTimestamp() {
        questionRepository.save(new Question(
            "testOwnerId",
            "ownerName",
            "roomId",
            "content",
            121,
            121,
            true,
            "answer"
        ));

        assertNotNull(questionRepository.findAllQuestionsByOwnerId("testOwnerId").get(0).getCreationTimestamp());
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
}