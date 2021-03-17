package nl.tudelft.oopp.demo.services;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void addQuestion(Question question){
        questionRepository.save(question);
    }

    public List<Question> getAllQuestionByRoomId(String roomId){
        return questionRepository.findAllByRoomId(roomId);
    }

    public List<Question> getAllQuestionByOwnerId(String ownerId) {
        return questionRepository.findAllByOwnerId(ownerId);
    }

    public void upvoteQuestionById(String questionId){
        Question question = questionRepository.getOne(questionId);
        question.upvote();
        questionRepository.save(question);
    }
    public void downvoteQuestionById(String questionId){
        Question question = questionRepository.getOne(questionId);
        question.downvote();
        questionRepository.save(question);
    }

    public void toggleQuestionStatus(String questionId) {
        Question question = questionRepository.getOne(questionId);
        if(question.isStatus()){
            question.setStatusFalse();
        }else{
            question.setStatusTrue();
        }
        questionRepository.save(question);
    }
}
