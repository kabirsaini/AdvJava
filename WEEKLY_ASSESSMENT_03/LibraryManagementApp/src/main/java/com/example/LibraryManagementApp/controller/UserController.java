package com.example.LibraryManagementApp.controller;

import com.example.LibraryManagementApp.model.User;
import com.example.LibraryManagementApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, 
                              BindingResult bindingResult, 
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        
        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("emailError", "Email already registered");
            return "register";
        }
        
        userService.registerUser(user);
        return "redirect:/login?registered";
    }
    
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required = false) String registered, Model model) {
        if (registered != null) {
            model.addAttribute("message", "Registration successful! Please login.");
        }
        return "login";
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam String email, 
                           @RequestParam String password, 
                           Model model) {
        User user = userService.login(email, password);
        
        if (user == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
        
        model.addAttribute("userName", user.getName());
        return "redirect:/dashboard";
    }
    
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }
}
