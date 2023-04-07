package com.papa.yogiyogi.repository;

import com.papa.yogiyogi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserIdAndUserPw(String userId, String userPw);
    Optional<User> findByUserId(String UserId);

}
