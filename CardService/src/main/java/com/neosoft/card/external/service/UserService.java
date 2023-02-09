package com.neosoft.card.external.service;

import com.neosoft.card.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "User-Service",url = "http://localhost:8083/user")
public interface UserService {
    @GetMapping("/getUser/{panNo}")
    public User getUser(@PathVariable String panNo);

    @PostMapping("/add")
    public User add(@RequestBody User user);
}
