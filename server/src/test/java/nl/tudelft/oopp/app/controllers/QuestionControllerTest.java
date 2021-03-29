package nl.tudelft.oopp.app.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import nl.tudelft.oopp.app.entities.Question;
import nl.tudelft.oopp.app.services.QuestionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.*;

/**
 * The type Question controller test.
 */

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class QuestionControllerTest {
    private static String id;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuestionService questionService;

    /**
     * Sets up.
     */
    @BeforeAll
    static void setUp() {
        id = "1";
    }

    /**
     * Add question.
     */
    @Test
    @Order(1)
    void addQuestion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .put("/question/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content( "{ " +
                "\"ownerId\": \"testOwnerId\"," +
                "\"roomId\": \"testRoomId\"," +
                "\"content\": \"Second q\"," +
                "\"numberOfUpvotes\": 5, \"numberOfDownvotes\": 5," +
                "\"status\": false," +
                "\"answer\": \"yes smth\" }")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Question Added Successfully"));
    }

    /**
     * Checks if endpoint returns status code 200, when input is valid
     *
     * @throws Exception the exception
     */
    @Test
    @Order(2)
    void getAllQuestionsByRoomId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/question/getAllByRoomId/" + "testRoomId")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].ownerId", is("testOwnerId")))
            .andExpect(jsonPath("$[0].content", is("Second q")))
            .andExpect(jsonPath("$[0].roomId", is("testRoomId")))
            .andExpect(jsonPath("$[0].numberOfUpvotes", is(5)))
            .andExpect(jsonPath("$[0].numberOfDownvotes", is(5)))
            .andExpect(jsonPath("$[0].status", is(false)))
            .andExpect(jsonPath("$[0].answer", is("yes smth")));
    }


    /**
     * Gets all questions by owner id.
     */
    @Test
    @Order(3)
    void getAllQuestionsByOwnerId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/question/getAllByOwnerId/" + "testOwnerId")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].ownerId", is("testOwnerId")))
            .andExpect(jsonPath("$[0].content", is("Second q")))
            .andExpect(jsonPath("$[0].roomId", is("testRoomId")))
            .andExpect(jsonPath("$[0].numberOfUpvotes", is(5)))
            .andExpect(jsonPath("$[0].numberOfDownvotes", is(5)))
            .andExpect(jsonPath("$[0].status", is(false)))
            .andExpect(jsonPath("$[0].answer", is("yes smth")));

    }

    /**
     * Toggle question status.
     */
    @Test
    @Order(4)
    void toggleQuestionStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .put("/question/toggleStatus/" + id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
//            .andExpect(jsonPath("$.ownerId", is("testOwnerId")))
//            .andExpect(jsonPath("$.content", is("Second q")))
//            .andExpect(jsonPath("$.roomId", is("testRoomId")))
//            .andExpect(jsonPath("$.numberOfUpvotes", is(5)))
//            .andExpect(jsonPath("$.numberOfDownvotes", is(5)))
//            .andExpect(jsonPath("$.status", is(true)))
//            .andExpect(jsonPath("$.answer", is("yes smth")));
    }

    /**
     * Upvote question by id.
     */
    @Test
    @Order(5)
    void upvoteQuestionById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .put("/question/upvote/" + id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
//            .andExpect(jsonPath("$.ownerId", is("testOwnerId")))
//            .andExpect(jsonPath("$.content", is("Second q")))
//            .andExpect(jsonPath("$.roomId", is("testRoomId")))
//            .andExpect(jsonPath("$.numberOfUpvotes", is(6)))
//            .andExpect(jsonPath("$.numberOfDownvotes", is(5)))
//            .andExpect(jsonPath("$.status", is(true)))
//            .andExpect(jsonPath("$.answer", is("yes smth")));
    }

    /**
     * Downvote question by id.
     */
    @Test
    @Order(6)
    void downvoteQuestionById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .put("/question/downvote/" + id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
//            .andExpect(jsonPath("$.ownerId", is("testOwnerId")))
//            .andExpect(jsonPath("$.content", is("Second q")))
//            .andExpect(jsonPath("$.roomId", is("testRoomId")))
//            .andExpect(jsonPath("$.numberOfUpvotes", is(6)))
//            .andExpect(jsonPath("$.numberOfDownvotes", is(6)))
//            .andExpect(jsonPath("$.status", is(true)))
//            .andExpect(jsonPath("$.answer", is("yes smth")));
    }


    /**
     * Downvote question by id.
     */
    @Test
    @Order(7)
    void getOneQuestionById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/question/getQuestionById/" + id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ownerId", is("testOwnerId")))
            .andExpect(jsonPath("$.content", is("Second q")))
            .andExpect(jsonPath("$.roomId", is("testRoomId")))
            .andExpect(jsonPath("$.numberOfUpvotes", is(6)))
            .andExpect(jsonPath("$.numberOfDownvotes", is(6)))
            .andExpect(jsonPath("$.status", is(true)))
            .andExpect(jsonPath("$.answer", is("yes smth")));
    }

    @Test
    @Order(8)
    void deleteAllQuestionByRoomId() throws Exception {
        Question q1 = new Question("1",
            "666",
            "First q",
            3,
            3,
            true,
            "yes");
        Question q2 = new Question("1",
            "666",
            "First q",
            3,
            3,
            true,
            "yes");

        questionService.addQuestion(q1);
        questionService.addQuestion(q2);

        mockMvc.perform(MockMvcRequestBuilders
            .delete("/question/deleteAllByRoomId/" + "666")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("All Question from room with id: 666 were deleted " +
                "Successfully"));

        assertEquals(questionService.getAllQuestionByRoomId("666").size(), 0);
    }
    /**
     * Delete question by id.
     */
    @Test
    @Order(9)
    void deleteQuestionById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .delete("/question/delete/" + id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Question with id: " + id + " deleted Successfully"));
    }
}