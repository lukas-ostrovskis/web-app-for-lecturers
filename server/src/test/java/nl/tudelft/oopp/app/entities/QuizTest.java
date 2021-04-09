package nl.tudelft.oopp.app.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.tudelft.oopp.app.entities.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuizTest {

    private Quiz qz1;
    private Quiz qz2;
    private Quiz qz3;

    @BeforeEach
    void setUp() {
        qz1 = new Quiz("aaaaaaa", "Question1", "AnswerA1", "AnswerA2", "AnswerA3", "AnswerA4",
                "AnswerA5", "AnswerA6", "AnswerA7", "AnswerA8", "AnswerA9", "AnswerA10", 'a');
        qz2 = new Quiz("bbbbbb", "Question2", "AnswerB1", "AnswerB2", "AnswerB3", "AnswerB4",
                "AnswerB5", "AnswerB6", "AnswerB7", "AnswerB8", "AnswerB9", "AnswerB10", 'b');
        qz3 = new Quiz("cccccccc", "Question3", "AnswerC1", "AnswerC2", "AnswerC3", "AnswerC4",
                "AnswerC5", "AnswerC6", "AnswerC7", "AnswerC8", "AnswerC9", "AnswerC10", 'c');
    }

    @Test
    void getRoomId() {
        assertEquals(qz1.getRoomId(), "aaaaaaa");
    }

    @Test
    void getQuestion() {
        assertEquals(qz2.getQuestion(), "Question2");
    }

    @Test
    void getAnswers() {
        assertEquals(qz1.getAnswerA(), "AnswerA1");
        assertEquals(qz1.getAnswerB(), "AnswerA2");
        assertEquals(qz1.getAnswerC(), "AnswerA3");
        assertEquals(qz1.getAnswerD(), "AnswerA4");
        assertEquals(qz1.getAnswerE(), "AnswerA5");
        assertEquals(qz1.getAnswerF(), "AnswerA6");
        assertEquals(qz1.getAnswerG(), "AnswerA7");
        assertEquals(qz1.getAnswerH(), "AnswerA8");
        assertEquals(qz1.getAnswerI(), "AnswerA9");
        assertEquals(qz1.getAnswerJ(), "AnswerA10");
    }

    @Test
    void getCorrectAnswer() {
        assertEquals(qz3.getCorrectAnswer(), 'c');
    }

    @Test
    void isUsed() {
        assertFalse(qz1.isUsed());
    }

    @Test
    void isOpen() {
        assertFalse(qz2.isOpen());
    }

    @Test
    void isAnswerDistributionReady() {
        assertFalse(qz3.isAnswerDistributionReady());
    }

    @Test
    void getAnswerDistribution() {
        assertNotNull(qz2.answerDistribution);
    }

    @Test
    void setQuestion() {
        qz2.setQuestion("test");
        assertEquals(qz2.getQuestion(), "test");
    }

    @Test
    void setCorrectAnswer() {
        qz1.setCorrectAnswer('g');
        assertEquals(qz1.getCorrectAnswer(), 'g');
    }

    @Test
    void setUsed() {
        qz2.setUsed(true);
        assertTrue(qz2.isUsed());
    }

    @Test
    void setOpen() {
        qz3.setOpen(true);
        assertTrue(qz3.isOpen());
    }

    @Test
    void setAnswerDistributionReady() {
        qz1.setAnswerDistributionReady(true);
        assertTrue(qz1.isAnswerDistributionReady());
    }

    @Test
    void testEquals1() {
        assertNotEquals(qz1, qz2);
    }

    @Test
    void testEquals2() {
        assertEquals(qz1, qz1);
    }
}