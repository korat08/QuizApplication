package com.example.QuizApplication.Model;


import jakarta.persistence.*;
import org.springframework.data.web.JsonPath;

@Entity
public class QuizQuestion {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public QuizQuestion() {
    }

    public QuizQuestion(Quiz quiz, Question question, String userAnswer) {
        this.quiz = quiz;
        this.question = question;
        this.userAnswer = userAnswer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    private String userAnswer;
}
