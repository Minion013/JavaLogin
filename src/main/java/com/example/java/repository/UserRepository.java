package com.example.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.java.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM User where USERNAME = ?1", nativeQuery = true)
    public User findUserByUserName(String userName);

    @Query(value = "SELECT * FROM User", nativeQuery = true)
    public List<User> checkingColumnn();
}
