package com.travelManagement.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelManagement.model.User;
import com.travelManagement.security.JwtUtil;
import com.travelManagement.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
    private UserService userService;
    private final JwtUtil jwtUtil;
    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }
    @GetMapping
    public List<User> getAllUsers() { return userService.getAllUsers(); }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) { return userService.registerUser(user); }

//    @PostMapping("/login")
//    public User loginUser(@RequestBody User user) {
//        return userService.loginUser(user.getEmail(), user.getPassword());
//    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User loggedUser = userService.loginUser(user.getEmail(), user.getPassword());
            String token = jwtUtil.generateToken(loggedUser.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", loggedUser);

            return ResponseEntity.ok(response);

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody User user) 
    { userService.deleteUser(user.getId());
    }

    // health check
    @GetMapping("/ping")
    public String ping() { return "users pong"; }
}
