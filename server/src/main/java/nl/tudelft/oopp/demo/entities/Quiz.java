package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "roomId")
    private String roomId;

    @Column(name = "question")
    private String question;

    /**
     * Possible answers are stored in separate String variables and not a List of Strings because:
     * 1) No more than 10 possible answers.
     * 2) Storing them as a List would create a separate database table for answers to quizzes.
     * Since answers don't actually need to be separated from other data about the quiz and will always be
     * pulled together, there's no real need for a separate table and it's more efficient to just store them in the
     * same row as the quiz entry that they belong to.
     */

    @Column(name = "answerA")
    private String answerA;

    @Column(name = "answerB")
    private String answerB;

    @Column(name = "answerC")
    private String answerC;

    @Column(name = "answerD")
    private String answerD;

    @Column(name = "answerE")
    private String answerE;

    @Column(name = "answerF")
    private String answerF;

    @Column(name = "answerG")
    private String answerG;

    @Column(name = "answerH")
    private String answerH;

    @Column(name = "answerI")
    private String answerI;

    @Column(name = "answerJ")
    private String answerJ;

    @Column(name = "correctAnswer")
    private char correctAnswer;

    @Column(name = "used")
    private boolean used;

    @Column(name = "open")
    private boolean open;

    @Column(name = "answerDistributionReady")
    private boolean answerDistributionReady;

    @Transient
    public Map<Character, Integer> answerDistribution;


    public Quiz() {
    }

    /**
     * Instantiates a new Quiz.
     *
     * @param roomId room id.
     * @param question the question of the quiz.
     * @param answerA answer A.
     * @param answerB answer B.
     * @param answerC answer C.
     * @param answerD answer D.
     * @param answerE answer E.
     * @param answerF answer F.
     * @param answerG answer G.
     * @param answerH answer H.
     * @param answerI answer I.
     * @param answerJ answer J.
     * @param correctAnswer correct answer.
     */
    public Quiz(String roomId, String question,
                String answerA, String answerB, String answerC, String answerD, String answerE, String answerF, String answerG, String answerH, String answerI, String answerJ,
                char correctAnswer) {
        this.roomId = roomId;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answerE = answerE;
        this.answerF = answerF;
        this.answerG = answerG;
        this.answerH = answerH;
        this.answerI = answerI;
        this.answerJ = answerJ;
        this.correctAnswer = correctAnswer;
        this.open = false;
        this.used = false;
        this.answerDistributionReady = false;
        this.answerDistribution = new HashMap<Character, Integer>();
    }

    public Quiz(String id, String roomId, String question,
                String answerA, String answerB, String answerC, String answerD, String answerE, String answerF, String answerG, String answerH, String answerI, String answerJ,
                char correctAnswer, boolean used, boolean open, boolean answerDistributionReady) {
        this.id = id;
        this.roomId = roomId;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answerE = answerE;
        this.answerF = answerF;
        this.answerG = answerG;
        this.answerH = answerH;
        this.answerI = answerI;
        this.answerJ = answerJ;
        this.correctAnswer = correctAnswer;
        this.used = used;
        this.open = open;
        this.answerDistributionReady = answerDistributionReady;
        this.answerDistribution = new HashMap<Character, Integer>();
    }

    /**
     * Gets a quiz id.
     *
     * @return the id.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets quiz's room id.
     *
     * @return the room id.
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Gets the quiz question.
     *
     * @return the question.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gets answer A.
     *
     * @return answer A.
     */
    public String getAnswerA() {
        return answerA;
    }
    /**
     * Gets answer B.
     *
     * @return answer B.
     */
    public String getAnswerB() {
        return answerB;
    }
    /**
     * Gets answer C.
     *
     * @return answer C.
     */
    public String getAnswerC() {
        return answerC;
    }
    /**
     * Gets answer D.
     *
     * @return answer D.
     */
    public String getAnswerD() {
        return answerD;
    }
    /**
     * Gets answer E.
     *
     * @return answer E.
     */
    public String getAnswerE() {
        return answerE;
    }
    /**
     * Gets answer F.
     *
     * @return answer F.
     */
    public String getAnswerF() {
        return answerF;
    }
    /**
     * Gets answer G.
     *
     * @return answer G.
     */
    public String getAnswerG() {
        return answerG;
    }
    /**
     * Gets answer H.
     *
     * @return answer H.
     */
    public String getAnswerH() {
        return answerH;
    }
    /**
     * Gets answer I.
     *
     * @return answer I.
     */
    public String getAnswerI() {
        return answerI;
    }
    /**
     * Gets answer J.
     *
     * @return answer J.
     */
    public String getAnswerJ() {
        return answerJ;
    }

    /**
     * Gets the correct answer to the question.
     *
     * @return the correct answer.
     */
    public char getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Gets the used status of the quiz.
     *
     * @return TRUE if already used, FALSE otherwise.
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Gets the open status of the quiz.
     *
     * @return TRUE if the quiz is currently open.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Gets the status of the answer distribution of the Quiz.
     *
     * @return TRUE if distribution is ready to be shown, FALSE otherwise.
     */
    public boolean isAnswerDistributionReady() {
        return answerDistributionReady;
    }

    /**
     * Gets the answer distribution.
     *
     * @return the answer distribution.
     */
    public Map<Character, Integer> getAnswerDistribution() {
        return answerDistribution;
    }

    /**
     * Sets the question of the quiz.
     *
     * @param question the question.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Sets the correct answer letter.
     *
     * @param correctAnswer the correct answer letter (a - j).
     */
    public void setCorrectAnswer(char correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Sets the used status of the quiz.
     *
     * @param used the status (whether it has already been used or not).
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * Sets the open status of the quiz.
     *
     * @param open the status (whether it is currently open or not).
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * Sets the answer distribution status of the quiz.
     *
     * @param answerDistributionReady the answer distribution status (whether it's ready to be shown).
     */
    public void setAnswerDistributionReady(boolean answerDistributionReady) {
        this.answerDistributionReady = answerDistributionReady;
    }

    /**
     * Checks whether ant Object o is equal to this instance of a Quiz
     *
     * @param o the Object
     * @return TRUE if equal, FALSE otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return correctAnswer == quiz.correctAnswer &&
                used == quiz.used &&
                open == quiz.open &&
                answerDistributionReady == quiz.answerDistributionReady &&
                id.equals(quiz.id) &&
                roomId.equals(quiz.roomId) &&
                question.equals(quiz.question) &&
                Objects.equals(answerA, quiz.answerA) &&
                Objects.equals(answerB, quiz.answerB) &&
                Objects.equals(answerC, quiz.answerC) &&
                Objects.equals(answerD, quiz.answerD) &&
                Objects.equals(answerE, quiz.answerE) &&
                Objects.equals(answerF, quiz.answerF) &&
                Objects.equals(answerG, quiz.answerG) &&
                Objects.equals(answerH, quiz.answerH) &&
                Objects.equals(answerI, quiz.answerI) &&
                Objects.equals(answerJ, quiz.answerJ) &&
                Objects.equals(answerDistribution, quiz.answerDistribution);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", roomId='" + roomId + '\'' +
                ", question='" + question + '\'' +
                ", answerA='" + answerA + '\'' +
                ", answerB='" + answerB + '\'' +
                ", answerC='" + answerC + '\'' +
                ", answerD='" + answerD + '\'' +
                ", answerE='" + answerE + '\'' +
                ", answerF='" + answerF + '\'' +
                ", answerG='" + answerG + '\'' +
                ", answerH='" + answerH + '\'' +
                ", answerI='" + answerI + '\'' +
                ", answerJ='" + answerJ + '\'' +
                ", correctAnswer=" + correctAnswer +
                ", used=" + used +
                ", open=" + open +
                ", answerDistributionReady=" + answerDistributionReady +
                ", answerDistribution=" + answerDistribution +
                '}';
    }


}
