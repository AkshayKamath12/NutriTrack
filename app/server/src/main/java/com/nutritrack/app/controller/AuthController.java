package com.nutritrack.app.controller;


import com.nutritrack.app.dto.SignInDTO;
import com.nutritrack.app.dto.SignUpDTO;
import com.nutritrack.app.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInDTO signInDTO, HttpServletResponse response) {
        String jwtToken = authService.login(signInDTO);
        Cookie cookie = new Cookie("jwt-token", jwtToken);
        cookie.setHttpOnly(true); 
        cookie.setPath("/");     
        response.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignUpDTO signUpDTO) {
        boolean registered = authService.signup(signUpDTO);
        if(!registered) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
