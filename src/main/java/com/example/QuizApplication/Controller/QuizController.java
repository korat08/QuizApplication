package com.example.QuizApplication.Controller;

import com.example.QuizApplication.DTO.QuizStartResponseDTO;
import com.example.QuizApplication.DTO.QuizSubmitDTO;
import com.example.QuizApplication.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("quiz-start/{userName}")
    public ResponseEntity<QuizStartResponseDTO> startQuiz(@PathVariable String userName){
        return quizService.startQuiz(userName);
    }

        @PostMapping("submit")
    public ResponseEntity<String> submitQuiz(@RequestBody QuizSubmitDTO quizSubmitDTO){
        return quizService.submitQuiz(quizSubmitDTO);
    }
}
