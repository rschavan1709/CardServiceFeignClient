package com.neosoft.user.repository;

import com.neosoft.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByPanNo(String panNo);
}
