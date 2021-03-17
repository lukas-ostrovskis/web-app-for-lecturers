package nl.tudelft.oopp.demo.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "ownerId")
    private String ownerId;

    @Column(name = "roomId")
    private String roomId;

    @Column(name = "content")
    private String content;

    @Column(name = "numberOfUpvotes")
    private int numberOfUpvotes;

    @Column(name = "numberOfDownvotes")
    private int numberOfDownvotes;

    @Column(name = "status")
    private boolean status;

    @Column(name = "answer")
    private String answer;

    @CreationTimestamp
    private Instant creationTimestamp;


    public Question(String ownerId, String roomId, String content,
                    int numberOfUpvotes, int numberOfDownvotes, boolean status, String answer) {
        this.ownerId = ownerId;
        this.roomId = roomId;
        this.content = content;
        this.numberOfUpvotes = numberOfUpvotes;
        this.numberOfDownvotes = numberOfDownvotes;
        this.status = status;
        this.answer = answer;
    }
    public Question(String id, String ownerId, String roomId, String content,
                    int numberOfUpvotes, int numberOfDownvotes, boolean status, String answer) {
        this.id = id;
        this.ownerId = ownerId;
        this.roomId = roomId;
        this.content = content;
        this.numberOfUpvotes = numberOfUpvotes;
        this.numberOfDownvotes = numberOfDownvotes;
        this.status = status;
        this.answer = answer;
    }

    public Question() {

    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getContent() {
        return content;
    }

    public int getNumberOfUpvotes() {
        return numberOfUpvotes;
    }

    public int getNumberOfDownvotes() {
        return numberOfDownvotes;
    }

    public boolean isStatus() {
        return status;
    }

    public String getAnswer() {
        return answer;
    }

    public void upvote(){
        this.numberOfUpvotes++;
    }
    public void downvote(){
        this.numberOfDownvotes++;
    }
    public void setStatusFalse(){
        this.status = false;
    }
    public void setStatusTrue(){
        this.status = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return  numberOfUpvotes == question.numberOfUpvotes &&
                numberOfDownvotes == question.numberOfDownvotes &&
                status == question.status &&
//                id.equals(question.id) &&
                ownerId.equals(question.ownerId) &&
                roomId.equals(question.roomId) &&
                content.equals(question.content) &&
                answer.equals(question.answer);
    }

}
