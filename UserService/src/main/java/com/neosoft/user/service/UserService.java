package com.neosoft.user.service;

import com.neosoft.user.model.User;

public interface UserService {
    User getUserByPanNo(String panNo);
    User add(User user);
}
