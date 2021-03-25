package nl.tudelft.oopp.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import nl.tudelft.oopp.app.entities.Question;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class QuestionTest {
    private static Question questionA;
    private static Question questionB;
    private static Question questionC;

    @BeforeAll
    static void setUp() {
        questionA = new Question("1232asd",
                "41224as",
                "1212ads",
                "What is the time",
                10,
                3,
                false,
                "");
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
    public void constructorTest() {
        assertNotNull(questionA);
    }

    @Test
    public void idTest() {
        assertEquals(questionA.getId(), questionB.getId());
    }

    @Test
    public void ownerIdTest() {
        assertEquals(questionA.getOwnerId(), questionB.getOwnerId());
    }

    @Test
    public void roomIdTest() {
        assertEquals(questionA.getRoomId(), questionB.getRoomId());
    }

    @Test
    public void contentTest() {
        assertEquals(questionA.getContent(), questionB.getContent());
    }

    @Test
    public void numberOfUpvotesTest() {
        assertEquals(questionA.getNumberOfUpvotes(), questionB.getNumberOfUpvotes());
    }

    @Test
    public void numberOfDownvotesTest() {
        assertEquals(questionA.getNumberOfDownvotes(), questionB.getNumberOfDownvotes());
    }

    @Test
    public void statusTest() {
        assertEquals(questionA.isStatus(), questionB.isStatus());
    }

    @Test
    public void answerTest() {
        assertEquals(questionA.getAnswer(), questionB.getAnswer());
    }

    @Test
    public void equalsTest() {
        assertEquals(questionA, questionB);
        assertNotEquals(questionA, questionC);
    }


}
