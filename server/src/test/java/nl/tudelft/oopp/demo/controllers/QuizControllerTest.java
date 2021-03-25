package nl.tudelft.oopp.demo.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import nl.tudelft.oopp.demo.services.QuizService;
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

/**
 * The type Quiz controller test.
 */

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class QuizControllerTest {
    private static String id;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuizService quizService;

    /**
     * Sets up.
     */
    @BeforeAll
    static void setUp() {
        id = "1";
    }

    /**
     * Add quiz.
     */
    @Test
    @Order(1)
    void addQuiz() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/quiz/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content( "{ " +
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
}