package nl.tudelft.oopp.app.data;

import java.util.Map;

public class Quiz {

    public Map<Character, Integer> answerDistribution;
    private String id;
    private String roomId;
    private String question;
    /**
     * Possible answers are stored in separate String variables and not a List of Strings because:
     * 1) No more than 10 possible answers.
     * 2) Storing them as a List would create a separate database table for answers to quizzes.
     * Since answers don't actually need to be separated from other data about the quiz
     * and will always be pulled together, there's no real need for a separate table
     * and it's more efficient to just store them in the
     * same row as the quiz entry that they belong to.
     */
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String answerE;
    private String answerF;
    private String answerG;
    private String answerH;
    private String answerI;
    private String answerJ;
    private char correctAnswer;
    private boolean used;
    private boolean open;
    private boolean answerDistributionReady;

    public Quiz() {
    }

    /**
     * Instantiates a new Quiz.
     *
     * @param roomId        room id.
     * @param question      the question of the quiz.
     * @param answerA       answer A.
     * @param answerB       answer B.
     * @param answerC       answer C.
     * @param answerD       answer D.
     * @param answerE       answer E.
     * @param answerF       answer F.
     * @param answerG       answer G.
     * @param answerH       answer H.
     * @param answerI       answer I.
     * @param answerJ       answer J.
     * @param correctAnswer correct answer.
     */
    public Quiz(String roomId, String question, String answerA,
                String answerB, String answerC, String answerD,
                String answerE, String answerF, String answerG,
                String answerH, String answerI, String answerJ, char correctAnswer) {
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
    }

    /**
     * Instantiates a new Quiz.
     *
     * @param id                      the id
     * @param roomId                  the room id
     * @param question                the question
     * @param answerA                 the answer a
     * @param answerB                 the answer b
     * @param answerC                 the answer c
     * @param answerD                 the answer d
     * @param answerE                 the answer e
     * @param answerF                 the answer f
     * @param answerG                 the answer g
     * @param answerH                 the answer h
     * @param answerI                 the answer i
     * @param answerJ                 the answer j
     * @param correctAnswer           the correct answer
     * @param used                    the used
     * @param open                    the open
     * @param answerDistributionReady the answer distribution ready
     */
    public Quiz(String id, String roomId, String question,
                String answerA, String answerB, String answerC,
                String answerD, String answerE, String answerF,
                String answerG, String answerH, String answerI, String answerJ,
                char correctAnswer, boolean used, boolean open,
                boolean answerDistributionReady) {
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
     * Sets the question of the quiz.
     *
     * @param question the question.
     */
    public void setQuestion(String question) {
        this.question = question;
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
     * Sets the correct answer letter.
     *
     * @param correctAnswer the correct answer letter (a - j).
     */
    public void setCorrectAnswer(char correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Gets the used status of the quiz.
     *
     * @return TRUE if already used, FALSE otherwise.
     */
    public boolean isUsed() {
        return used;
    }

    public boolean isOpen() {
        return open;
    }

    /**
     * Gets the answer distribution.
     *
     * @return the answer distribution.
     */
    public Map<Character, Integer> getAnswerDistribution() {
        return answerDistribution;
    }

    @Override
    public String toString() {
        return "Question: " + question + "\n"
            + "A) " + answerA + "\n"
            + "B) " + answerB + "\n"
            + "C) " + answerC + "\n"
            + "D) " + answerD + "\n"
            + "E) " + answerE + "\n"
            + "F) " + answerF + "\n"
            + "G) " + answerG + "\n"
            + "H) " + answerH + "\n"
            + "I) " + answerI + "\n"
            + "J) " + answerJ + "\n"
            + "Correct answer: " + correctAnswer;
    }
}
