package com.example.QuizApplication.DTO;

import java.util.List;

public class QuizSubmitDTO {
    private Integer quizId;
    private List<AnswerDTO> answerDTOS;

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public List<AnswerDTO> getAnswerDTOS() {
        return answerDTOS;
    }

    public void setAnswerDTOS(List<AnswerDTO> answerDTOS) {
        this.answerDTOS = answerDTOS;
    }
}
