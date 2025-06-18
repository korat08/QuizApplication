package com.example.QuizApplication.Controller;

import com.example.QuizApplication.CustomResponse.AnswerResponse;
import com.example.QuizApplication.CustomResponse.QuestionResponse;
import com.example.QuizApplication.DTO.AnswerDTO;
import com.example.QuizApplication.DTO.QuestionDTO;
import com.example.QuizApplication.DTO.QuizResultDTO;
import com.example.QuizApplication.Service.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("quiz-start/{userName}")
    public ResponseEntity<String> startQuiz(@PathVariable String userName){
        return quizService.startQuiz(userName);
    }

    @GetMapping("{quizId}/get-question")
    public ResponseEntity<QuestionResponse<QuestionDTO>> getQuestion(@PathVariable Integer quizId){
        return quizService.getQuestion(quizId);
    }

    @PostMapping("{quizId}/submitAnswer")
    public ResponseEntity<AnswerResponse> submitAnswer(@PathVariable Integer quizId ,@Valid @RequestBody AnswerDTO answerDTO){
        return quizService.submitAnswer(quizId,answerDTO);
    }

    @GetMapping("{quizId}/get-result")
    public ResponseEntity<QuestionResponse<QuizResultDTO>> getQuizResult(@PathVariable Integer quizId){
        return quizService.getQuizResult(quizId);
    }

    @GetMapping("getQuiz/{quizId}")
    public ResponseEntity<?> getQuizSet(@PathVariable Integer quizId){
        return quizService.getQuizSet(quizId);
    }
}
