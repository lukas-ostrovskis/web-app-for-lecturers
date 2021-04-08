package nl.tudelft.oopp.app.entities;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;


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

    @Column(name = "ownerName")
    private String ownerName;

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

    @ManyToMany
    private List<User> upvoters;

    @ManyToMany
    private List<User> downvoters;

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
    public Question(String ownerId, String ownerName, String roomId, String content,
                    int numberOfUpvotes, int numberOfDownvotes, boolean status, String answer) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
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
    public Question(String id, String ownerId, String ownerName, String roomId, String content,
                    int numberOfUpvotes, int numberOfDownvotes, boolean status, String answer) {
        this.id = id;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
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

    public void setNumberOfUpvotes(int numberOfUpvotes) {
        this.numberOfUpvotes = numberOfUpvotes;
    }

    public void setNumberOfDownvotes(int numberOfDownvotes) {
        this.numberOfDownvotes = numberOfDownvotes;
    }

    /**
     * Downvotes a question.
     */
    public void downvote() {
        this.numberOfDownvotes++;
    }

    /**
     * Getter for the upvoters of the question.
     * @returns a list of all the id's of users that have upvoted this question
     */
    public List<User> getUpvoters() {
        return upvoters;
    }

    /**
     * Getter for the downvoters of the question.
     * @returns a list of all the id's of users that have downvoted this question
     */
    public List<User> getDownvoters() {
        return downvoters;
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
     * Getter for the creation timestamp of the question.
     * @return the creation timestamp
     */
    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * Sets the creation timestamp.
     * @param creationTimestamp - timestamp of the moment when question was created
     */
    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question = (Question) o;
        return numberOfUpvotes == question.numberOfUpvotes
            && numberOfDownvotes == question.numberOfDownvotes
            && status == question.status
            && ownerId.equals(question.ownerId)
            && roomId.equals(question.roomId)
            && content.equals(question.content)
            && answer.equals(question.answer);
    }


    /**
     * Gets question's owner's name.
     *
     * @return the owner name
     */
    public String getOwnerName() {
        return ownerName;
    }
}
