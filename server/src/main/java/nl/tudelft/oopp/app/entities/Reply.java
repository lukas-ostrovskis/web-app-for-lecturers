package nl.tudelft.oopp.app.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

/**
 * The type Reply.
 */
@Entity
@Table(name = "replies")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "ownerId")
    private String ownerId;

    @Column(name = "questionId")
    private String questionId;

    @Column(name = "content")
    private String content;

    @Column(name = "numberOfUpvotes")
    private int numberOfUpvotes;

    @Column(name = "numberOfDownvotes")
    private int numberOfDownvotes;

    @CreationTimestamp
    private Instant creationTimestamp;

    /**
     * Instantiates a new Reply.
     *
     * @param id                Unique identifier of the reply as to be used in the database.
     * @param ownerId           Identifier of the author of the reply.
     * @param questionId        Identifier of the question that the reply is addressed to.
     * @param content           Actual text of the reply.
     * @param numberOfUpvotes   Number of upvotes the reply has.
     * @param numberOfDownvotes Number of downvotes the reply has.
     */
    public Reply(String     id,
                 String     ownerId,
                 String     questionId,
                 String     content,
                 int        numberOfUpvotes,
                 int        numberOfDownvotes,
                 Instant    creationTimestamp) {

        this.id = id;
        this.ownerId = ownerId;
        this.questionId = questionId;
        this.content = content;
        this.numberOfUpvotes = numberOfUpvotes;
        this.numberOfDownvotes = numberOfDownvotes;
        this.creationTimestamp = creationTimestamp;
    }

    /**
     * Instantiates a new Reply.
     *
     * @param ownerId           Identifier of the author of the reply.
     * @param questionId        Identifier of the question that the reply is addressed to.
     * @param content           Actual text of the reply.
     * @param numberOfUpvotes   Number of upvotes the reply has.
     * @param numberOfDownvotes Number of downvotes the reply has.
     */
    public Reply(String     ownerId,
                 String     questionId,
                 String     content,
                 int        numberOfUpvotes,
                 int        numberOfDownvotes,
                 Instant    creationTimestamp) {

        this.ownerId = ownerId;
        this.questionId = questionId;
        this.content = content;
        this.numberOfUpvotes = numberOfUpvotes;
        this.numberOfDownvotes = numberOfDownvotes;
        this.creationTimestamp = creationTimestamp;
    }

    /**
     * Instantiates a new Reply.
     */
    public Reply() {

    };

    /**
     * Gets the id field.
     *
     * @return the id field
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the owner id field.
     *
     * @return the owner id field
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Gets the question id field.
     *
     * @return the question id field
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * Gets the content field.
     *
     * @return the content field
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the number of upvotes field.
     *
     * @return the number of upvotes field.
     */
    public int getNumberOfUpvotes() {
        return numberOfUpvotes;
    }

    /**
     * Gets the number of downvotes field.
     *
     * @return the number of downvotes field.
     */
    public int getNumberOfDownvotes() {
        return numberOfDownvotes;
    }

    /**
     * Gets the creation timestamp field.
     *
     * @return the creation timestamp field.
     */
    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * Increases the number of upvotes by 1.
     */
    public void upvote() {
        numberOfUpvotes += 1;
    }

    /**
     * Increases the number of downvotes by 1.
     */
    public void downvote() {
        numberOfDownvotes += 1;
    }

    /**
     * Compares this reply instance with another object.
     *
     * @param o the object to be compared with
     * @return true if the two objects are the same, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reply)) return false;
        Reply reply = (Reply) o;
        return  id.equals(reply.id) &&
                ownerId.equals(reply.ownerId) &&
                questionId.equals(reply.questionId) &&
                content.equals(reply.content) &&
                numberOfUpvotes == reply.numberOfUpvotes &&
                numberOfDownvotes == reply.numberOfDownvotes &&
                creationTimestamp.equals(reply.creationTimestamp);
    }

}
