package com.example.QuizApplication.DTO;

import com.example.QuizApplication.Model.QuizQuestion;

import java.util.List;

public class QuizResultDTO {
    private String userName;
    private Integer totalQuestion;
    private Integer correctAnswer;
    private Integer inCorrectAnswer;

    private List<QuizQuestion> quizQuestionList;


    public QuizResultDTO() {
    }

    public QuizResultDTO(String userName, Integer totalQuestion, Integer correctAnswer, Integer inCorrectAnswer, List<QuizQuestion> quizQuestionList) {
        this.userName = userName;
        this.totalQuestion = totalQuestion;
        this.correctAnswer = correctAnswer;
        this.inCorrectAnswer = inCorrectAnswer;
        this.quizQuestionList = quizQuestionList;
    }

    public List<QuizQuestion> getQuizQuestionList() {
        return quizQuestionList;
    }

    public void setQuizQuestionList(List<QuizQuestion> quizQuestionList) {
        this.quizQuestionList = quizQuestionList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(Integer totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getInCorrectAnswer() {
        return inCorrectAnswer;
    }

    public void setInCorrectAnswer(Integer inCorrectAnswer) {
        this.inCorrectAnswer = inCorrectAnswer;
    }
}
