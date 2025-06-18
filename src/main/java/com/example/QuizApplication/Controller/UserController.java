package com.example.QuizApplication.Controller;

import com.example.QuizApplication.Model.User;
import com.example.QuizApplication.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("create-user/{userName}")
    public ResponseEntity<String> createUser(@Valid @PathVariable String userName){
        return userService.createUser(userName);
    }

}
