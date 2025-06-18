package com.example.QuizApplication.DTO;

import jakarta.validation.constraints.NotNull;

public class AnswerDTO {

    @NotNull
    private Integer questionId;

    @NotNull
    private String userAnswer;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
