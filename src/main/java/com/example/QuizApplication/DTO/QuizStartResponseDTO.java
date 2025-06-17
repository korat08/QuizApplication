package com.example.QuizApplication.DTO;


import java.util.List;

public class QuizStartResponseDTO {
    private Integer quizId;
    private List<QuestionDTO> questions;

    public QuizStartResponseDTO() {
    }

    public QuizStartResponseDTO(Integer quizId, List<QuestionDTO> questions) {
        this.quizId = quizId;
        this.questions = questions;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}

