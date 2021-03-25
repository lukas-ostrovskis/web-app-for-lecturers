package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Quiz;
import nl.tudelft.oopp.demo.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;

    /**
     * Instantiates a new Quiz controller.
     *
     * @param quizService the quiz service.
     */
    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    /**
     * Add a quiz.
     *
     * @param quiz the Quiz.
     * @return a message that the quiz was added.
     */
    @PostMapping("add")
    public String addQuiz(@RequestBody Quiz quiz) {
        try {
            quizService.addQuiz(quiz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Quiz Added Successfully";
    }

    /**
     * Get all quizzes by room id.
     *
     * @param roomId the room id.
     * @return the list of quizzes.
     */
    @GetMapping("getAll/{roomId}")
    public List<Quiz> getAllQuizzesByRoomId(@PathVariable String roomId) {
        return quizService.getAllQuizzesByRoomId(roomId);
    }

    /**
     * Get an open quiz by room id (if one exists).
     * Method based on DeferredResult, which allows long polling by waiting for a response that can be sent as an answer
     * to a request to be formed on a separate thread.
     *
     * Source: https://stackoverflow.com/questions/53697785/how-to-implement-long-polling-rest-endpoint-in-spring-boot-app/53699241
     *
     * @param roomId the room id.
     * @return the open quiz.
     */
    @GetMapping("getOpen/{roomId}")
    DeferredResult<Quiz> getOpenQuizByRoomId(@PathVariable String roomId){
        Long timeOutInMilliSec = 50000L;
        String timeOutResp = "Time Out.";
        DeferredResult<Quiz> deferredResult = new DeferredResult<>(timeOutInMilliSec,timeOutResp);
        CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(15);
                Quiz quiz = quizService.getOpenQuizByRoomId(roomId);
                deferredResult.setResult(quiz);
            }catch (Exception ex){
            }
        });
        return deferredResult;
    }

    /**
     * Submits an answer to a Quiz from a client.
     * Method based on DeferredResult, which allows long polling by waiting for a response that can be sent as an answer
     * to a request to be formed on a separate thread.
     *
     * @param roomId the room id (room to which the quiz belongs).
     * @param answer the answer chosen by the client.
     */
    @PutMapping("answer")
    public void quizUserAnswer(@RequestBody String roomId, @RequestBody char answer) {
        quizService.quizUserAnswer(roomId, answer);
    }

    /**
     * Closes a Quiz by its id.
     *
     * @param quizId the quiz id.
     */
    @PutMapping("toggleOpenStatus")
    public void toggleQuizOpenStatus(@RequestBody String quizId) {
        quizService.toggleQuizOpenStatus(quizId);
    }

    /**
     * Gets the answer distribution of a Quiz that has it ready.
     *
     * @param roomId the id of the room to which the Quiz belongs.
     * @return answerDistribution if there's one ready, null otherwise.
     */
    @GetMapping("answerDistribution")
    public DeferredResult<Map<Character, Integer>> getQuizAnswerDistribution(@RequestBody String roomId) {
        Long timeOutInMilliSec = 50000L;
        String timeOutResp = "Time Out.";
        DeferredResult<Map<Character, Integer>> deferredResult = new DeferredResult<>(timeOutInMilliSec,timeOutResp);
        CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(15);
                Map<Character, Integer> answerDistribution = quizService.getQuizAnswerDistribution(roomId);
                deferredResult.setResult(answerDistribution);
            }catch (Exception ex){
            }
        });
        return deferredResult;
    }
}
