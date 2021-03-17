package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.services.QuestionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Question controller.
 */
@RequestMapping("/question")
@RestController
public class QuestionController {
    private QuestionService questionService;

    /**
     * Instantiates a new Question controller.
     *
     * @param questionService the question service
     */
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * Gets all questions by room id.
     *
     * @param roomId the room id
     * @return the all questions by room id
     */
    @GetMapping(value = "/getAllByRoomId/{roomId}", produces = "application" +
            "/json")
    public List<Question> getAllQuestionsByRoomId(@PathVariable String roomId) {
        return questionService.getAllQuestionByRoomId(roomId);
    }

    /**
     * Gets all questions by owner id.
     *
     * @param ownerId the owner id
     * @return the all questions by owner id
     */
    @GetMapping(value = "/getAllByOwnerId/{ownerId}", produces = "application" +
            "/json")
    public List<Question> getAllQuestionsByOwnerId(@PathVariable String ownerId) {
        return questionService.getAllQuestionByOwnerId(ownerId);
    }

    /**
     * Toggle question status string.
     *
     * @param questionId the question id
     * @return the string
     */
    @GetMapping(value = "/toggleStatus/{questionId}")
    public String toggleQuestionStatus(@PathVariable String questionId) {
        try {
            questionService.toggleQuestionStatus(questionId);
        } catch (Exception e) {
            return "Something went wrong";
        }
        return "Question's status with id:" + questionId + "was " +
                "toggled successfully";
    }

    /**
     * Upvote question by id string.
     *
     * @param questionId the question id
     * @return the string
     */
    @GetMapping(value = "/upvote/{questionId}")
    public String upvoteQuestionById(@PathVariable String questionId) {
        try {
            questionService.upvoteQuestionById(questionId);
        } catch (Exception e) {
            return "Something went wrong";
        }
        return "Question with id:" + questionId + " Upvoted Successfully";
    }

    /**
     * Downvote question by id string.
     *
     * @param questionId the question id
     * @return the string
     */
    @GetMapping(value = "/downvote/{questionId}")
    public String downvoteQuestionById(@PathVariable String questionId) {
        try {
            questionService.downvoteQuestionById(questionId);
        } catch (Exception e) {
            return "Something went wrong";
        }
        return "Question with id:" + questionId + " Downvoted Successfully";
    }

    /**
     * Delete question by id string.
     *
     * @param questionId the question id
     * @return the string
     */
    @GetMapping(value = "/delete/{questionId}")
    public String deleteQuestionById(@PathVariable String questionId) {
        try {
            questionService.deleteQuestion(questionId);
        } catch (Exception e) {
            return "Something went wrong";
        }
        return "Question with id:" + questionId + " deleted Successfully";
    }

    /**
     * Add question string.
     *
     * @param question the question
     * @return the string
     */
    @PostMapping(
            value = "/addQuestion",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String addQuestion(@RequestBody Question question) {
        try {
            questionService.addQuestion(question);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Question Added Successfully";
    }


}
