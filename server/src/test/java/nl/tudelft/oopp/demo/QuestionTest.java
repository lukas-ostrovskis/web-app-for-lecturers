package nl.tudelft.oopp.demo;

import nl.tudelft.oopp.demo.entities.Question;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {


    Question question_a = new Question("1232asd", "41224as", "1212ads", "What is the time", 10, 3, false, "");
    Question question_b = new Question("1232asd", "41224as", "1212ads", "What is the time", 10, 3, false, "");
    Question question_c = new Question("123u0j", "n123n", "zma2m", "Is this going to be on the exam?", 15, 0, false, "");


    @Test
    public void constructor_Test() {
        assertNotNull(question_a);
    }

    @Test
    public void id_Test() {
        assertEquals(question_a.getId(), question_b.getId());
    }

    @Test
    public void owner_id_Test() {
        assertEquals(question_a.getOwner_id(), question_b.getOwner_id());
    }

    @Test
    public void room_id_Test() {
        assertEquals(question_a.getRoom_id(), question_b.getRoom_id());
    }

    @Test
    public void content_Test(){
        assertEquals(question_a.getContent(), question_b.getContent());
    }

    @Test
    public void number_of_upvotes_Test()
    {
        assertEquals(question_a.getNumber_of_upvotes(), question_b.getNumber_of_upvotes());
    }

    @Test
    public void number_of_downvotes_Test(){
        assertEquals(question_a.getNumber_of_downvotes(), question_b.getNumber_of_downvotes());
    }

    @Test
    public void status_Test()
    {
        assertEquals(question_a.isStatus(), question_b.isStatus());
    }

    @Test
    public void answer_Test()
    {
        assertEquals(question_a.getAnswer(), question_b.getAnswer());
    }

    @Test
    public void equals_Test()
    {
        assertEquals(question_a, question_b);
        assertNotEquals(question_a, question_c);
    }


}
