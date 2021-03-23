package nl.tudelft.oopp.app.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

/**
 * The type Question.
 */
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


    /**
     * Instantiates a new Question.
     *
     * @param ownerId           the owner id
     * @param roomId            the room id
     * @param content           the content
     * @param numberOfUpvotes   the number of upvotes
     * @param numberOfDownvotes the number of downvotes
     * @param status            the status
     * @param answer            the answer
     */
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

    /**
     * Instantiates a new Question.
     *
     * @param id                the id
     * @param ownerId           the owner id
     * @param roomId            the room id
     * @param content           the content
     * @param numberOfUpvotes   the number of upvotes
     * @param numberOfDownvotes the number of downvotes
     * @param status            the status
     * @param answer            the answer
     */
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

    /**
     * Instantiates a new Question.
     */
    public Question() {

    }

    /**
     * Gets a question id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets question's owner id.
     *
     * @return the owner id
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Gets question's room id.
     *
     * @return the room id
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Gets question's content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets question's number of upvotes.
     *
     * @return the number of upvotes
     */
    public int getNumberOfUpvotes() {
        return numberOfUpvotes;
    }

    /**
     * Gets question's number of downvotes.
     *
     * @return the number of downvotes
     */
    public int getNumberOfDownvotes() {
        return numberOfDownvotes;
    }

    /**
     * Is status boolean.
     *
     * @return the boolean
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Gets question's answer.
     *
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Upvote question.
     */
    public void upvote() {
        this.numberOfUpvotes++;
    }

    /**
     * Downvote question.
     */
    public void downvote() {
        this.numberOfDownvotes++;
    }

    /**
     * Sets question status false.
     */
    public void setStatusFalse() {
        this.status = false;
    }

    /**
     * Sets question status true.
     */
    public void setStatusTrue() {
        this.status = true;
    }

    /**
     * @return the creation timestamp
     */
    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * @param creationTimestamp
     */
    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return numberOfUpvotes == question.numberOfUpvotes &&
                numberOfDownvotes == question.numberOfDownvotes &&
                status == question.status &&
//                id.equals(question.id) &&
                ownerId.equals(question.ownerId) &&
                roomId.equals(question.roomId) &&
                content.equals(question.content) &&
                answer.equals(question.answer);
    }
}
