package com.example.QuizApplication.Controller;

import com.example.QuizApplication.Model.User;
import com.example.QuizApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("create-user")
    public ResponseEntity<String> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

}
