package com.fluente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fluente.model.User;
import com.fluente.repository.UserRepository;

@RestController
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/users")
    public String createUser(@RequestBody User user) {
        userRepository.save(user);
        return "User created successfully";
    }


    @GetMapping("/api/hello")
    public String sayHello() {
        return "Bonjour from Fluente";
    }
}
