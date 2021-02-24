package com.example.springrest.dao;

import com.example.springrest.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getUser(Long id);
    User findByUserName(String email);
}
