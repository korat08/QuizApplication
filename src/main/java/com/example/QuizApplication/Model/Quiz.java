package com.example.QuizApplication.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.util.List;

@Entity
@Table(name = "quiz_table")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id" ,nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<QuizQuestion> questionList;

    public Quiz() {
    }

    public Quiz(User user, List<QuizQuestion> questionList) {
        this.user = user;
        this.questionList = questionList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<QuizQuestion> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuizQuestion> questionList) {
        this.questionList = questionList;
    }
}
