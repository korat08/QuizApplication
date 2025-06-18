package com.example.QuizApplication.Repository;

import com.example.QuizApplication.Model.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion,Integer> {

    @Query(value = "SELECT * FROM quiz_question WHERE quiz_id = :quizId AND question_id = :questionId LIMIT 1", nativeQuery = true)
    QuizQuestion findByQuizIdAndQuestionId(@Param("quizId") Integer quizId, @Param("questionId") Integer questionId);
    
    boolean existsByQuizIdAndQuestionId(Integer quiz_id,Integer question_id);

}
