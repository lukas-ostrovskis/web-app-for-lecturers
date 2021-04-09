package nl.tudelft.oopp.app.data;

import java.time.Instant;
import java.util.List;

public class Question {

    private String id;
    private String ownerId;
    private String ownerName;
    private String roomId;
    private String content;
    private int numberOfUpvotes;
    private int numberOfDownvotes;
    private boolean status;
    private String answer;
    private Instant creationTimestamp;
    private List<User> upvoters;
    private List<User> downvoters;


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

    public List<User> getUpvoters() {
        return upvoters;
    }

    public void setUpvoters(List<User> upvoters) {
        this.upvoters = upvoters;
    }

    public List<User> getDownvoters() {
        return downvoters;
    }

    public void setDownvoters(List<User> downvoters) {
        this.downvoters = downvoters;
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
     * Getter for creation timestamp.
     *
     * @return the creation timestamp
     */
    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * Setter for creation timestamp.
     *
     * @param creationTimestamp - creation timestamp
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

    public String getOwnerName() {
        return ownerName;
    }
}
