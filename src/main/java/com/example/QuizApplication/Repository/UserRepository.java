package com.example.QuizApplication.Repository;

import com.example.QuizApplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUserName(String userName);
}
