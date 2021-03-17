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
}
