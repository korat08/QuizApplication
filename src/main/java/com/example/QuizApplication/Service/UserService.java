package com.example.QuizApplication.Service;

import com.example.QuizApplication.Model.User;
import com.example.QuizApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<String> createUser(User user) {
        userRepository.save(user);
        return new ResponseEntity<>("Successfully enter..", HttpStatus.CREATED);
    }
}
