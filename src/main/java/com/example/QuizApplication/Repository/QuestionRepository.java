package com.example.QuizApplication.Repository;


import com.example.QuizApplication.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    @Query(value = "select * from question_table order by rand() limit ?1",nativeQuery = true)
    List<Question> findRandomQuestions(int count);
}
