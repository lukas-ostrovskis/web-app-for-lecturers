package nl.tudelft.oopp.app.controllers;

import java.util.List;
import nl.tudelft.oopp.app.entities.Question;
import nl.tudelft.oopp.app.services.QuestionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Question controller.
 */
@RequestMapping("/question")
@RestController
public class QuestionController {
    private final QuestionService questionService;

    /**
     * Instantiates a new Question controller.
     *
     * @param questionService the question service
     */
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    /**
     * GET Endpoint to retrieve one questions by it's id.
     *
     * @param questionId the question ID
     * @return question by with specified ID
     */
    @GetMapping(value = "/getQuestionById/{questionId}",
                produces = "application" + "/json")
    public Question getOneQuestionById(@PathVariable String questionId) {
        return questionService.getOneQuestionById(questionId);
    }

    /**
     * GET Endpoint to retrieve all questions by room id.
     *
     * @param roomId the room id
     * @return the all questions by room id
     */
    @GetMapping(value = "/getAllByRoomId/{roomId}",
                produces = "application" + "/json")
    public List<Question> getAllQuestionsByRoomId(@PathVariable String roomId) {
        return questionService.getAllQuestionByRoomId(roomId);
    }

    /**
     * GET Endpoint to retrieve questions by owner id.
     *
     * @param ownerId the owner id
     * @return the all questions by owner id
     */
    @GetMapping(value = "/getAllByOwnerId/{ownerId}",
                produces = "application" + "/json")
    public List<Question> getAllQuestionsByOwnerId(@PathVariable String ownerId) {
        return questionService.getAllQuestionByOwnerId(ownerId);
    }

    /**
     * PUT Endpoint to toggle question status.
     * True - Answered
     * False - Unanswered
     *
     * @param questionId the question id
     * @return String
     */
    @PutMapping(value = "/toggleStatus/{questionId}")
    public String toggleQuestionStatus(@PathVariable String questionId) {
        try {
            questionService.toggleQuestionStatus(questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Question's id: " + questionId + " status was toggled successfully";
    }

    /**
     * PUT Endpoint to upvote question by id string.
     *
     * @param questionId the question id
     * @return String
     */
    @PutMapping(value = "/upvote")
    public String upvoteQuestionById(@RequestParam String questionId, @RequestParam String userId) {
        try {
            questionService.upvoteQuestionById(questionId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Question id: " + questionId + " upvoted  successfully";
    }

    /**
     * PUT Endpoint to downvote question by id string.
     *
     * @param questionId the question id
     * @return String
     */
    @PutMapping(value = "/downvote")
    public String downvoteQuestionById(@RequestParam String questionId,
                                       @RequestParam String userId) {
        try {
            questionService.downvoteQuestionById(questionId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Question id: " + questionId + " downvoted successfully";
    }

    /**
     * DELETE Endpoint to delete question from the database by id string.
     *
     * @param questionId the question id
     * @return String
     */
    @DeleteMapping(value = "/delete/{questionId}")
    public String deleteQuestionById(@PathVariable String questionId) {
        try {
            questionService.deleteQuestion(questionId);
        } catch (Exception e) {
            return "Something went wrong";
        }
        return "Question with id: " + questionId + " deleted Successfully";
    }

    /**
     * DELETE Endpoint to delete all question from the room by it's ID.
     *
     * @param roomId ID of the room
     * @return String
     */
    @DeleteMapping(value = "/deleteAllByRoomId/{roomId}")
    public String deleteAllQuestionByRoomId(@PathVariable String roomId) {
        try {
            questionService.deleteAllQuestionByRoomId(roomId);
        } catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong";
        }
        return "All Question from room with id: " + roomId + " were deleted Successfully";
    }

    /**
     * PUT Endpoint Add question to the database.
     *
     * @param question the question
     * @return String
     */
    @PutMapping(
        value = "/add",
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
