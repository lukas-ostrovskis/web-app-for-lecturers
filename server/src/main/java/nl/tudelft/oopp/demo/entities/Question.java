package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "replies")
public class Question {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "owner_id")
    private String owner_id;

    @Column(name = "room_id")
    private String room_id;

    @Column(name = "content")
    private String content;

    @Column(name = "number_of_upvotes")
    private int number_of_upvotes;

    @Column(name = "number_of_downvotes")
    private int number_of_downvotes;

    @Column(name = "status")
    private boolean status;

    @Column(name = "answer")
    private String answer;


    public Question(String id, String owner_id, String room_id, String content, int number_of_upvotes, int number_of_downvotes, boolean status, String answer) {
        this.id = id;
        this.owner_id = owner_id;
        this.room_id = room_id;
        this.content = content;
        this.number_of_upvotes = number_of_upvotes;
        this.number_of_downvotes = number_of_downvotes;
        this.status = status;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public String getContent() {
        return content;
    }

    public int getNumber_of_upvotes() {
        return number_of_upvotes;
    }

    public int getNumber_of_downvotes() {
        return number_of_downvotes;
    }

    public boolean isStatus() {
        return status;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return  number_of_upvotes == question.number_of_upvotes &&
                number_of_downvotes == question.number_of_downvotes &&
                status == question.status &&
                id.equals(question.id) &&
                owner_id.equals(question.owner_id) &&
                room_id.equals(question.room_id) &&
                content.equals(question.content) &&
                answer.equals(question.answer);
    }

}
