package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.services.QuestionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RestController
public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }



    @GetMapping(value="/getAllByRoomId/{roomId}", produces = "application/json")
    public List<Question> getAllQuestionsByRoomId(@PathVariable String roomId) {
        return questionService.getAllQuestionByRoomId(roomId);
    }

    @GetMapping(value="/getAllByOwnerId/{ownerId}", produces = "application" +
            "/json")
    public List<Question> getAllQuestionsByOwnerId(@PathVariable String ownerId) {
        return questionService.getAllQuestionByOwnerId(ownerId);
    }

    @GetMapping(value="/toggleStatus/{questionId}")
    public String toggleQuestionStatus(@PathVariable String questionId){
        try{
            questionService.toggleQuestionStatus(questionId);
        }catch (Exception e){
            return "Something went wrong";
        }
        return "Question's status with id:" + questionId +  "was " +
                "toggled successfully";
    }

    @GetMapping(value="/upvote/{questionId}")
    public String upvoteQuestionById(@PathVariable String questionId){
        try{
            questionService.upvoteQuestionById(questionId);
        }catch (Exception e){
            return "Something went wrong";
        }
        return "Question with id:" + questionId + " Upvoted Successfully";
    }
    @GetMapping(value="/downvote/{questionId}")
    public String downvoteQuestionById(@PathVariable String questionId){
        try{
            questionService.downvoteQuestionById(questionId);
        }catch (Exception e){
            return "Something went wrong";
        }
        return "Question with id:" + questionId + " Downvoted Successfully";
    }

    @PostMapping (
            value="/addQuestion",
            consumes=MediaType.APPLICATION_JSON_VALUE
    )
    public String addQuestion(@RequestBody Question question) {
        try{
            questionService.addQuestion(question);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "Question Added Successfully";
    }




}
