package nl.tudelft.oopp.app.controllers;

import com.google.gson.internal.bind.util.ISO8601Utils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import nl.tudelft.oopp.app.communication.ServerCommunication;
import nl.tudelft.oopp.app.data.Quiz;
import nl.tudelft.oopp.app.views.MainView;

import java.io.IOException;
import java.util.Map;

public class QuizViewController {

    @FXML
    private TextArea questionArea;

    @FXML
    private Button answerA;

    @FXML
    private Button answerB;

    @FXML
    private Button answerC;

    @FXML
    private Button answerD;

    @FXML
    private Button answerE;

    @FXML
    private Button answerF;

    @FXML
    private Button answerG;

    @FXML
    private Button answerH;

    @FXML
    private Button answerI;

    @FXML
    private Button answerJ;

    private boolean answerDistributionReady;

    private Quiz currentQuiz;
    private Map<Character, Integer> currentAnswerDistribution;

    @FXML
    public void initialize() {
        currentQuiz = RoomViewController.getOpenedQuiz();
        questionArea.setText(currentQuiz.getQuestion());

        if(currentQuiz.getAnswerA().equals("")) answerA.setVisible(false);
        else answerA.setText(currentQuiz.getAnswerA());
        if(currentQuiz.getAnswerB().equals("")) answerB.setVisible(false);
        else answerB.setText(currentQuiz.getAnswerB());
        if(currentQuiz.getAnswerC().equals("")) answerC.setVisible(false);
        else answerC.setText(currentQuiz.getAnswerC());
        if(currentQuiz.getAnswerD().equals("")) answerD.setVisible(false);
        else answerD.setText(currentQuiz.getAnswerD());
        if(currentQuiz.getAnswerE().equals("")) answerE.setVisible(false);
        else answerE.setText(currentQuiz.getAnswerE());
        if(currentQuiz.getAnswerF().equals("")) answerF.setVisible(false);
        else answerF.setText(currentQuiz.getAnswerF());
        if(currentQuiz.getAnswerG().equals("")) answerG.setVisible(false);
        else answerG.setText(currentQuiz.getAnswerG());
        if(currentQuiz.getAnswerH().equals("")) answerH.setVisible(false);
        else answerH.setText(currentQuiz.getAnswerH());
        if(currentQuiz.getAnswerI().equals("")) answerI.setVisible(false);
        else answerI.setText(currentQuiz.getAnswerI());
        if(currentQuiz.getAnswerJ().equals("")) answerJ.setVisible(false);
        else answerJ.setText(currentQuiz.getAnswerJ());

        startCheckingAnswerDistributionReady(MainView.getRoomId());
    }

    private void startCheckingAnswerDistributionReady(String roomId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    Map<Character, Integer> answerDistribution = ServerCommunication.checkAnswerDistributionReady(roomId);
                    currentAnswerDistribution = answerDistribution;
                    if(answerDistribution != null) {
                        if(!answerDistributionReady) {
                            answerDistributionReady = true;

                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    if(currentQuiz.getCorrectAnswer() == 'a') answerA.setStyle("-fx-border-color: #00FF00; ");
                                    else if(currentQuiz.getCorrectAnswer() == 'b') answerB.setStyle("-fx-border-color: #00FF00; ");
                                    else if(currentQuiz.getCorrectAnswer() == 'c') answerC.setStyle("-fx-border-color: #00FF00; ");
                                    else if(currentQuiz.getCorrectAnswer() == 'd') answerD.setStyle("-fx-border-color: #00FF00; ");
                                    else if(currentQuiz.getCorrectAnswer() == 'e') answerE.setStyle("-fx-border-color: #00FF00; ");
                                    else if(currentQuiz.getCorrectAnswer() == 'f') answerF.setStyle("-fx-border-color: #00FF00; ");
                                    else if(currentQuiz.getCorrectAnswer() == 'g') answerG.setStyle("-fx-border-color: #00FF00; ");
                                    else if(currentQuiz.getCorrectAnswer() == 'h') answerH.setStyle("-fx-border-color: #00FF00; ");
                                    else if(currentQuiz.getCorrectAnswer() == 'i') answerI.setStyle("-fx-border-color: #00FF00; ");
                                    else if(currentQuiz.getCorrectAnswer() == 'j') answerJ.setStyle("-fx-border-color: #00FF00; ");

                                    System.out.println(currentAnswerDistribution);
                                    int amountOfAnswers = 0;
                                    for(char answer = 'a'; answer <= 'j'; answer++) {
                                        if(currentAnswerDistribution.containsKey(answer)) {
                                            amountOfAnswers += currentAnswerDistribution.get(answer);
                                        }
                                        else currentAnswerDistribution.put(answer, 0);
                                    }
                                    answerA.setText(String.valueOf((((double)currentAnswerDistribution.get('a')/(double)amountOfAnswers)*100.0)) + "%  " + answerA.getText());
                                    answerB.setText(String.valueOf((((double)currentAnswerDistribution.get('b')/(double)amountOfAnswers)*100.0)) + "%  " + answerB.getText());
                                    answerC.setText(String.valueOf((((double)currentAnswerDistribution.get('c')/(double)amountOfAnswers)*100.0)) + "%  " + answerC.getText());
                                    answerD.setText(String.valueOf((((double)currentAnswerDistribution.get('d')/(double)amountOfAnswers)*100.0)) + "%  " + answerD.getText());
                                    answerE.setText(String.valueOf((((double)currentAnswerDistribution.get('e')/(double)amountOfAnswers)*100.0)) + "%  " + answerE.getText());
                                    answerF.setText(String.valueOf((((double)currentAnswerDistribution.get('f')/(double)amountOfAnswers)*100.0)) + "%  " + answerF.getText());
                                    answerG.setText(String.valueOf((((double)currentAnswerDistribution.get('g')/(double)amountOfAnswers)*100.0)) + "%  " + answerG.getText());
                                    answerH.setText(String.valueOf((((double)currentAnswerDistribution.get('h')/(double)amountOfAnswers)*100.0)) + "%  " + answerH.getText());
                                    answerI.setText(String.valueOf((((double)currentAnswerDistribution.get('i')/(double)amountOfAnswers)*100.0)) + "%  " + answerI.getText());
                                    answerJ.setText(String.valueOf((((double)currentAnswerDistribution.get('j')/(double)amountOfAnswers)*100.0)) + "%  " + answerJ.getText());
                                }
                            });
                        }
                    }
                    else {
                        if(answerDistributionReady) {
                            answerDistributionReady = false;
                        }
                    }
                }
            }
        }).start();
    }

    @FXML
    public void answerAClicked() {
        disableButtons();
        ServerCommunication.submitQuizAnswer(MainView.getRoomId(), "a");
    }
    @FXML
    public void answerBClicked() {
        disableButtons();
        ServerCommunication.submitQuizAnswer(MainView.getRoomId(), "b");
    }
    @FXML
    public void answerCClicked() {
        disableButtons();
        ServerCommunication.submitQuizAnswer(MainView.getRoomId(), "c");
    }
    @FXML
    public void answerDClicked() {
        disableButtons();
        ServerCommunication.submitQuizAnswer(MainView.getRoomId(), "d");
    }
    @FXML
    public void answerEClicked() {
        disableButtons();
        ServerCommunication.submitQuizAnswer(MainView.getRoomId(), "e");
    }
    @FXML
    public void answerFClicked() {
        disableButtons();
        ServerCommunication.submitQuizAnswer(MainView.getRoomId(), "f");
    }
    @FXML
    public void answerGClicked() {
        disableButtons();
        ServerCommunication.submitQuizAnswer(MainView.getRoomId(), "g");
    }
    @FXML
    public void answerHClicked() {
        disableButtons();
        ServerCommunication.submitQuizAnswer(MainView.getRoomId(), "h");
    }
    @FXML
    public void answerIClicked() {
        disableButtons();
        ServerCommunication.submitQuizAnswer(MainView.getRoomId(), "i");
    }
    @FXML
    public void answerJClicked() {
        disableButtons();
        ServerCommunication.submitQuizAnswer(MainView.getRoomId(), "j");
    }

    public void disableButtons() {
        answerA.setDisable(true);
        answerB.setDisable(true);
        answerC.setDisable(true);
        answerD.setDisable(true);
        answerE.setDisable(true);
        answerF.setDisable(true);
        answerG.setDisable(true);
        answerH.setDisable(true);
        answerI.setDisable(true);
        answerJ.setDisable(true);
    }



}
