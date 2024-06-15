package com.example.BTTHWebsite2.repository;

import com.example.BTTHWebsite2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}