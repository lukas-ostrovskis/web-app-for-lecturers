package nl.tudelft.oopp.app.controllers;

import nl.tudelft.oopp.app.entities.Quiz;
import nl.tudelft.oopp.app.services.QuizService;
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
    @PutMapping("answer/{roomId}")
    public String quizUserAnswer(@PathVariable String roomId, @RequestBody String answer) {
        quizService.quizUserAnswer(roomId, answer.charAt(0));
        return "Answer recorded!";
    }

    /**
     * Toggles the open status of the Quiz.
     *
     * @param quizId the quiz id.
     */
    @PutMapping("toggleOpenStatus")
    public Quiz toggleQuizOpenStatus(@RequestBody String quizId) {
        return quizService.toggleQuizOpenStatus(quizId);
    }

    /**
     * Toggles the answerDistributionReady status of the Quiz.
     *
     * @param quizId the quiz id.
     */
    @PutMapping("toggleAnswerDistributionReadyStatus")
    public Quiz toggleAnswerDistributionReadyStatus(@RequestBody String quizId) {
        return quizService.toggleAnswerDistributionReadyStatus(quizId);
    }

    /**
     * Deletes a Quiz from server memory by its roomId.
     *
     * SHOULD BE CALLED WHEN THE ROOM IS GETTING CLOSED.
     *
     * @param roomId the room id.
     * @return the Quiz that will be deleted.
     */
    @DeleteMapping("deleteFromServerMemory/{roomId}")
    public Quiz deleteQuizFromServerMemory(@PathVariable String roomId) {
        return quizService.deleteQuizFromServerMemory(roomId);
    }

    /**
     * Gets the answer distribution of a Quiz that has it ready.
     *
     * @param roomId the id of the room to which the Quiz belongs.
     * @return answerDistribution if there's one ready, null otherwise.
     */
    @GetMapping("answerDistribution/{roomId}")
    public DeferredResult<Map<Character, Integer>> getQuizAnswerDistribution(@PathVariable String roomId) {
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
