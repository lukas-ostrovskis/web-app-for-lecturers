package nl.tudelft.oopp.demo;

import nl.tudelft.oopp.demo.entities.Question;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {


    Question question_a = new Question("1232asd", "41224as", "1212ads", "What is the time", 10, 3, false, "");
    Question question_b = new Question("1232asd", "41224as", "1212ads", "What is the time", 10, 3, false, "");
    Question question_c = new Question("123u0j", "n123n", "zma2m", "Is this going to be on the exam?", 15, 0, false, "");


    @Test
    public void constructorTest() {
        assertNotNull(question_a);
    }

    @Test
    public void idTest() {
        assertEquals(question_a.getId(), question_b.getId());
    }

    @Test
    public void ownerIdTest() {
        assertEquals(question_a.getOwnerId(), question_b.getOwnerId());
    }

    @Test
    public void roomIdTest() {
        assertEquals(question_a.getRoomId(), question_b.getRoomId());
    }

    @Test
    public void contentTest(){
        assertEquals(question_a.getContent(), question_b.getContent());
    }

    @Test
    public void numberOfUpvotesTest()
    {
        assertEquals(question_a.getNumberOfUpvotes(), question_b.getNumberOfUpvotes());
    }

    @Test
    public void numberOfDownvotesTest(){
        assertEquals(question_a.getNumberOfDownvotes(), question_b.getNumberOfDownvotes());
    }

    @Test
    public void statusTest()
    {
        assertEquals(question_a.isStatus(), question_b.isStatus());
    }

    @Test
    public void answerTest()
    {
        assertEquals(question_a.getAnswer(), question_b.getAnswer());
    }

    @Test
    public void equalsTest()
    {
        assertEquals(question_a, question_b);
        assertNotEquals(question_a, question_c);
    }


}
