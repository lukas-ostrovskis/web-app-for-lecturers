package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type Reply.
 */
@Entity
@Table(name = "replies")
public class Reply {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "ownerId")
    private String ownerId;

    @Column(name = "questionId")
    private String questionId;

    @Column(name = "content")
    private String content;

    /**
     * Create a new Reply instance.
     *
     * @param id         Unique identifier as to be used in the database.
     * @param ownerId    Identifier of the author of the reply.
     * @param questionId Identifier of the question that the reply is addressed to.
     * @param content    Actual text of the reply.
     */
    public Reply(String id, String ownerId, String questionId, String content) {
        this.id = id;
        this.ownerId = ownerId;
        this.questionId = questionId;
        this.content = content;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets owner id.
     *
     * @return the owner id
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Gets question id.
     *
     * @return the question id
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reply)) return false;
        Reply reply = (Reply) o;
        return id.equals(reply.id) &&
                ownerId.equals(reply.ownerId) &&
                questionId.equals(reply.questionId) &&
                content.equals(reply.content);
    }

}
