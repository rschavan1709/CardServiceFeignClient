package com.neosoft.user.service.impl;

import com.neosoft.user.model.User;
import com.neosoft.user.repository.UserRepository;
import com.neosoft.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByPanNo(String panNo) {
        return userRepository.findByPanNo(panNo);
    }

    @Override
    public User add(User user) {
       return userRepository.save(user);
    }
}
