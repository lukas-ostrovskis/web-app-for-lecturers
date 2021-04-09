package nl.tudelft.oopp.app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import nl.tudelft.oopp.app.services.QuizService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;


/**
 * The type Quiz controller test.
 */

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class QuizControllerTest {
    private static String id;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuizService quizService;

    /**
     * Test adding a quiz.
     */
    @Test
    @Order(1)
    void addQuiz() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/quiz/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ " +
                        "\"roomId\": \"testRoomId\"," +
                        "\"question\": \"testQuestion\"," +
                        "\"answerA\": \"testAnswerA\"," +
                        "\"answerB\": \"testAnswerB\"," +
                        "\"answerC\": \"testAnswerC\"," +
                        "\"answerD\": \"testAnswerD\"," +
                        "\"answerE\": \"testAnswerE\"," +
                        "\"answerF\": \"testAnswerF\"," +
                        "\"answerG\": \"testAnswerG\"," +
                        "\"answerH\": \"testAnswerH\"," +
                        "\"answerI\": \"testAnswerI\"," +
                        "\"answerJ\": \"testAnswerJ\"," +
                        "\"correctAnswer\": \"c\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Quiz Added Successfully"));
    }

    /**
     * Test getting all quizzes by room id.
     * Checks if endpoint returns correct quiz info when input is valid.
     *
     * @throws Exception the exception
     */
    @Test
    @Order(2)
    void getAllQuizzesByRoomId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/quiz/getAll/" + "dddddd")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roomId", is("dddddd")))
                .andExpect(jsonPath("$[0].question", is("Question4")))
                .andExpect(jsonPath("$[0].correctAnswer", is("d")))
                .andExpect(jsonPath("$[0].used", is(false)))
                .andExpect(jsonPath("$[0].open", is(false)))
                .andExpect(jsonPath("$[0].answerDistributionReady", is(false)));
    }

    /**
     * Test toggling open status of a quiz.
     * Checks if open status can be toggled on.
     *
     * @throws Exception the exception
     */
    @Test
    @Order(3)
    void toggleOpenStatus1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/toggleOpenStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("4")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId", is("dddddd")))
                .andExpect(jsonPath("$.question", is("Question4")))
                .andExpect(jsonPath("$.correctAnswer", is("d")))
                .andExpect(jsonPath("$.used", is(false)))
                .andExpect(jsonPath("$.open", is(true)))
                .andExpect(jsonPath("$.answerDistributionReady", is(false)));
    }


    /**
     * Test toggling open status of a quiz.
     * Checks if open status can be toggled off.
     *
     * @throws Exception the exception
     */
    @Test
    @Order(4)
    void toggleOpenStatus2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/toggleOpenStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2")
                .accept(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/toggleOpenStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId", is("bbbbbb")))
                .andExpect(jsonPath("$.question", is("Question2")))
                .andExpect(jsonPath("$.correctAnswer", is("b")))
                .andExpect(jsonPath("$.used", is(false)))
                .andExpect(jsonPath("$.open", is(false)))
                .andExpect(jsonPath("$.answerDistributionReady", is(false)));
    }

    /**
     * Test getting an open quiz by room id.
     *
     * @throws Exception
     */
    @Test
    @Order(5)
    void getOpenQuizByRoomId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/toggleOpenStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2")
                .accept(MediaType.APPLICATION_JSON));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/quiz/getOpen/bbbbbb"))
                .andReturn();
        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId", is("bbbbbb")))
                .andExpect(jsonPath("$.question", is("Question2")))
                .andExpect(jsonPath("$.correctAnswer", is("b")))
                .andExpect(jsonPath("$.used", is(false)))
                .andExpect(jsonPath("$.open", is(true)))
                .andExpect(jsonPath("$.answerDistributionReady", is(false)));
    }

    /**
     * Test adding a student answer to an open quiz.
     *
     * @throws Exception
     */
    @Test
    @Order(6)
    void userAnswer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/toggleOpenStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2")
                .accept(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/answer/bbbbbb")
                .contentType(MediaType.APPLICATION_JSON)
                .content("e")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Answer recorded!"));
    }

    /**
     * Test toggling answerDistributionReady status
     * Checks if answerDistributionReady status can be toggled on
     *
     * @throws Exception the exception
     */
    @Test
    @Order(7)
    void toggleAnswerDistributionReadyStatus1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/toggleAnswerDistributionReadyStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId", is("eeeeee")))
                .andExpect(jsonPath("$.question", is("Question5")))
                .andExpect(jsonPath("$.correctAnswer", is("e")))
                .andExpect(jsonPath("$.used", is(false)))
                .andExpect(jsonPath("$.open", is(false)))
                .andExpect(jsonPath("$.answerDistributionReady", is(true)));
    }

    /**
     * Test toggling answerDistributionReady status
     * Checks if answerDistributionReady status can be toggled off
     *
     * @throws Exception the exception
     */
    @Test
    @Order(8)
    void toggleAnswerDistributionReadyStatus2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/toggleAnswerDistributionReadyStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2")
                .accept(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/toggleAnswerDistributionReadyStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId", is("bbbbbb")))
                .andExpect(jsonPath("$.question", is("Question2")))
                .andExpect(jsonPath("$.correctAnswer", is("b")))
                .andExpect(jsonPath("$.used", is(false)))
                .andExpect(jsonPath("$.open", is(false)))
                .andExpect(jsonPath("$.answerDistributionReady", is(false)));
    }

    /**
     * Test getting quiz answer distribution.
     * Checks if server correctly returns a map with submitted answers.
     *
     * @throws Exception
     */
    @Test
    @Order(9)
    void getQuizAnswerDistribution1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/toggleOpenStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2")
                .accept(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/answer/bbbbbb")
                .contentType(MediaType.APPLICATION_JSON)
                .content("e")
                .accept(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/toggleOpenStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2")
                .accept(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/toggleAnswerDistributionReadyStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2")
                .accept(MediaType.APPLICATION_JSON));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/quiz/answerDistribution/bbbbbb"))
                .andReturn();
        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    /**
     * Test getting quiz answer distribution.
     * Checks if server correctly returns null content if answer distribution is not ready.
     *
     * @throws Exception
     */
    @Test
    @Order(10)
    void getQuizAnswerDistribution2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/toggleOpenStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2")
                .accept(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                .put("/quiz/toggleOpenStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2")
                .accept(MediaType.APPLICATION_JSON));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/quiz/answerDistribution/bbbbbb"))
                .andReturn();
        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    /**
     * Test deleting quiz from server's memory (quizzes list on QuizService)
     *
     * @throws Exception
     */
    @Test
    @Order(11)
    void deleteQuizFromServerMemory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/quiz/deleteFromServerMemory/bbbbbb")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId", is("bbbbbb")))
                .andExpect(jsonPath("$.question", is("Question2")));
    }

}