package nl.tudelft.oopp.demo.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * The type Question controller test.
 */

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionControllerTest {
    private static String id;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Sets up.
     */
    @BeforeAll
    static void setUp() {
        id = "1";

    }

    /**
     * Checks if endpoint returns status code 200, when input is valid
     *
     * @throws Exception the exception
     */
    @Test
    void GetAllQuestionsByRoomId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/question/getAllByRoomId/" + id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }


    /**
     * Gets all questions by owner id.
     */
    @Test
    void getAllQuestionsByOwnerId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/question/getAllByOwnerId/" + id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    /**
     * Toggle question status.
     */
    @Test
    void toggleQuestionStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .post("/question/toggleStatus/" + id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    /**
     * Upvote question by id.
     */
    @Test
    void upvoteQuestionById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .post("/question/upvote/" + id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    /**
     * Downvote question by id.
     */
    @Test
    void downvoteQuestionById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .post("/question/downvote/" + id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    /**
     * Delete question by id.
     */
    @Test
    void deleteQuestionById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .post("/question/delete/" + id)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    /**
     * Add question.
     */
    @Test
    void addQuestion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .post("/question/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content( "{ \"ownerId\": \"1\", \"roomId\": \"1\", \"content\": \"Second q\", " +
                "\"numberOfUpvotes\": 5, \"numberOfDownvotes\": 5, \"status\": false, \"answer\":" +
                " \"yes smth\" }")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}