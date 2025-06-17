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

    public ResponseEntity<QuizStartResponseDTO> startQuiz(String userName) {
        User user = userRepository.findByUserName(userName);
        QuizStartResponseDTO responseDTO = new QuizStartResponseDTO();

        if(user == null){
            return ResponseEntity.badRequest().body(responseDTO);
        }

        List<Question> randomQuestions = questionRepository.findRandomQuestions(5);
        Quiz quiz = new Quiz(user,new ArrayList<>());

        for(Question q:randomQuestions){
            QuizQuestion qq = new QuizQuestion(quiz,q,null);
            quiz.getQuestionList().add(qq);
        }

        quizRepository.save(quiz);


        responseDTO.setQuizId(quiz.getId());

        List<QuestionDTO> questionDTOList = randomQuestions.stream().map(q -> {
            QuestionDTO dto = new QuestionDTO(q.getId(),q.getQuestionText(),q.getOptionA(),q.getOptionB(),q.getOptionC(),q.getOptionD());
            return dto;
        }).collect(Collectors.toList());

        responseDTO.setQuestions(questionDTOList);

        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<String> submitQuiz(QuizSubmitDTO quizSubmitDTO) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizSubmitDTO.getQuizId());

        if(optionalQuiz.isEmpty()){
            return ResponseEntity.badRequest().body("Quiz not found.....");
        }

        Quiz quiz = optionalQuiz.get();
        int score = 0;

        for(AnswerDTO answerDTO : quizSubmitDTO.getAnswerDTOS()){
            for(QuizQuestion qq : quiz.getQuestionList()){
                if(qq.getQuestion().getId().equals(answerDTO.getQuestionId())){
                    qq.setUserAnswer(answerDTO.getUserAnswer());

                    if(qq.getQuestion().getCorrectAnswer().equalsIgnoreCase(answerDTO.getUserAnswer())){
                        score++;
                    }

                    break;
                }
            }
        }
        quizRepository.save(quiz);
        return ResponseEntity.ok("Your Scored "+ score+ " out of " + quizSubmitDTO.getAnswerDTOS().size());
    }

}
