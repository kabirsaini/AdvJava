package com.example.LibraryManagementApp.service;

import com.example.LibraryManagementApp.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    
    private List<User> users = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong(1);
    
    public void registerUser(User user) {
        user.setId(idCounter.getAndIncrement());
        users.add(user);
    }
    
    public User login(String email, String password) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
    
    public boolean emailExists(String email) {
        return users.stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }
}
