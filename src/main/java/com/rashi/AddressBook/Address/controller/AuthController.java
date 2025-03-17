package com.rashi.AddressBook.Address.controller;

import com.rashi.AddressBook.Address.DTO.LoginDTO;
import com.rashi.AddressBook.Address.DTO.UserDTO;
import com.rashi.AddressBook.Address.repository.UserRepository;
import com.rashi.AddressBook.Address.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    AuthService authService;
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO) {
        String response = authService.registerUser(userDTO);
        return ResponseEntity.ok(response);


    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        String response = authService.loginUser(loginDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<Map<String, String>> forgotPassword(
            @PathVariable String email,
            @RequestBody Map<String, String> requestBody) {

        String message = authService.forgotPassword(email, requestBody.get("password"));
        return ResponseEntity.ok(Map.of("message", message));
    }


    @PutMapping("/resetPassword/{email}")
    public ResponseEntity<Map<String, String>> resetPassword(
            @PathVariable String email,
            @RequestParam String currentPassword,
            @RequestParam String newPassword) {

        String message = authService.resetPassword(email, currentPassword, newPassword);
        return ResponseEntity.ok(Map.of("message", message));
    }
}