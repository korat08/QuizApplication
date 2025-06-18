package com.example.QuizApplication.Service;

import com.example.QuizApplication.CustomResponse.AnswerResponse;
import com.example.QuizApplication.CustomResponse.QuestionResponse;
import com.example.QuizApplication.DTO.AnswerDTO;
import com.example.QuizApplication.DTO.QuestionDTO;
import com.example.QuizApplication.DTO.QuizResultDTO;
import com.example.QuizApplication.Model.Question;
import com.example.QuizApplication.Model.Quiz;
import com.example.QuizApplication.Model.QuizQuestion;
import com.example.QuizApplication.Model.User;
import com.example.QuizApplication.Repository.QuestionRepository;
import com.example.QuizApplication.Repository.QuizQuestionRepository;
import com.example.QuizApplication.Repository.QuizRepository;
import com.example.QuizApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuizQuestionRepository quizQuestionRepository;

    public ResponseEntity<String> startQuiz(String userName) {
        User user = userRepository.findByUserName(userName);

        if(user == null){
            return ResponseEntity.badRequest().body("User Not Found........");
        }

        Quiz quiz = new Quiz();
        quiz.setUser(user);

        List<Question> questionList = questionRepository.findRandomQuestions(5);

        for(Question qq : questionList){
            QuizQuestion quizQuestion = new QuizQuestion(quiz,qq,null);
            quiz.getQuestionList().add(quizQuestion);
        }

        quizRepository.save(quiz);

        Integer quizId = quiz.getId();
        return ResponseEntity.ok("Quiz Started Your Quiz id is => " + quizId);
    }

    public ResponseEntity<QuestionResponse<QuestionDTO>> getQuestion(Integer quizId) {

        Optional<Quiz> quizOpt = quizRepository.findById(quizId);

        if(quizOpt.isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }

        Quiz quiz = quizOpt.get();
        int currentIndex = quiz.getCurrentQuestionIndex();

        if(quiz.getQuestionList().size() <= currentIndex){
            QuestionResponse<QuestionDTO> response = new QuestionResponse<>(false,"Quiz is Finished...",null);
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        QuizQuestion currentQQ = quiz.getQuestionList().get(currentIndex);
        Question question = currentQQ.getQuestion();

        currentIndex++;
        quiz.setCurrentQuestionIndex(currentIndex);

        quizRepository.save(quiz);

        QuestionDTO questionDTO = new QuestionDTO(quiz.getUser().getId(),question.getId(),question.getQuestionText(),question.getOptionA(),question.getOptionB(),question.getOptionC(),question.getOptionD());

        QuestionResponse<QuestionDTO> response = new QuestionResponse<>(true,"SuccessFully Fetch",questionDTO);

        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<AnswerResponse> submitAnswer(Integer quizId, AnswerDTO answerDTO) {
        Optional<Quiz> quizOpt = quizRepository.findById(quizId);

        if(quizOpt.isEmpty()){
            AnswerResponse response = new AnswerResponse(false,"Quiz Id Not Found.....");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        if(!questionRepository.existsById(answerDTO.getQuestionId())){
            AnswerResponse response = new AnswerResponse(false,"Question Not Found In Database.....");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        Quiz quiz = quizOpt.get();

        if(!quizQuestionRepository.existsByQuizIdAndQuestionId(quizId,answerDTO.getQuestionId())){
            AnswerResponse response = new AnswerResponse(false,"Question Not Found In " + quizId +" Quizid..");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        QuizQuestion quizQuestion = quizQuestionRepository.findByQuizIdAndQuestionId(quizId, answerDTO.getQuestionId());
        quizQuestion.setUserAnswer(answerDTO.getUserAnswer());

        boolean isCorrect = false;

        if(answerDTO.getUserAnswer().toUpperCase().equals(questionRepository.findCorrectAnswerByID(answerDTO.getQuestionId()))){
            isCorrect = true;
        }

        quizQuestion.setIsCorrect(isCorrect);

        quizRepository.save(quiz);

        AnswerResponse response = new AnswerResponse(true, "Answer submitted successfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getQuizSet(Integer quizId) {
        Quiz quiz = new Quiz();
        quiz = quizRepository.findById(quizId).orElse(null);
        return ResponseEntity.ok(quiz);
    }

    public ResponseEntity<QuestionResponse<QuizResultDTO>> getQuizResult(Integer quizId) {
        Optional<Quiz> quizOpt = quizRepository.findById(quizId);

        if(quizOpt.isEmpty()){
            QuestionResponse<QuizResultDTO> questionResponse = new QuestionResponse<>(false,"QuizId not Found..",null);
            return new ResponseEntity<>(questionResponse,HttpStatus.BAD_REQUEST);
        }

        Quiz quiz = quizOpt.get();

        if(quiz.getCurrentQuestionIndex() != 5){
            QuestionResponse<QuizResultDTO> questionResponse = new QuestionResponse<>(false,"First attend All question..",null);
            return new ResponseEntity<>(questionResponse,HttpStatus.BAD_REQUEST);
        }

        String userName = quiz.getUser().getUserName();

        int correctAnswer = 0;
        int inCorrectAnswer = 0;

        for(QuizQuestion qq : quiz.getQuestionList()){
            if(qq.getIsCorrect() == true){
                correctAnswer++;
            }else{
                inCorrectAnswer++;
            }
        }

        QuizResultDTO quizResultDTO = new QuizResultDTO(userName,5,correctAnswer,inCorrectAnswer,quiz.getQuestionList());

        QuestionResponse<QuizResultDTO> questionResponse = new QuestionResponse<>(true,"Quiz Result",quizResultDTO);
        return new ResponseEntity<>(questionResponse,HttpStatus.OK);
    }
}
