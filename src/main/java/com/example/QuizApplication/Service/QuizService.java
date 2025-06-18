package com.example.QuizApplication.Service;

import com.example.QuizApplication.DTO.AnswerDTO;
import com.example.QuizApplication.DTO.QuestionDTO;
import com.example.QuizApplication.DTO.QuizStartResponseDTO;
import com.example.QuizApplication.DTO.QuizSubmitDTO;
import com.example.QuizApplication.Model.Question;
import com.example.QuizApplication.Model.Quiz;
import com.example.QuizApplication.Model.QuizQuestion;
import com.example.QuizApplication.Model.User;
import com.example.QuizApplication.Repository.QuestionRepository;
import com.example.QuizApplication.Repository.QuizRepository;
import com.example.QuizApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.event.AncestorEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> startQuiz(String userName) {
        User user = userRepository.findByUserName(userName);

        if(user == null){
            return ResponseEntity.badRequest().body("User Not Found........");
        }

        Quiz quiz = new Quiz();
        quiz.setUser(user);
        quizRepository.save(quiz);

        Integer quizId = quiz.getId();
        return ResponseEntity.ok("Quiz Started Your Quiz id is => " + quizId);
    }



    public ResponseEntity<?> getQuizSet(Integer quizId) {
        Quiz quiz = new Quiz();
        quiz = quizRepository.findById(quizId).orElse(null);
        return ResponseEntity.ok(quiz);
    }
}
