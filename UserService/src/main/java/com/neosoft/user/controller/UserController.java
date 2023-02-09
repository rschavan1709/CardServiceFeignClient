package com.neosoft.user.controller;

import com.neosoft.user.model.User;
import com.neosoft.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getUser/{panNo}")
    public User getUser(@PathVariable String panNo){
        return userService.getUserByPanNo(panNo);
    }

    @PostMapping("/add")
    public User add(@RequestBody User user){
        return userService.add(user);
    }
}
