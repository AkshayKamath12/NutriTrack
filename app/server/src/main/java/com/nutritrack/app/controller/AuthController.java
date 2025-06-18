package com.nutritrack.app.controller;

import com.nutritrack.app.dao.RoleRepository;
import com.nutritrack.app.dao.UserRepository;
import com.nutritrack.app.dto.SignInDTO;
import com.nutritrack.app.dto.SignUpDTO;
import com.nutritrack.app.entity.Role;
import com.nutritrack.app.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInDTO signInDTO) {
        String usernameOrEmail = signInDTO.getUsernameOrEmail();
        String password = signInDTO.getPassword();
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usernameOrEmail, password));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignUpDTO signUpDTO) {
        //check if username already exists
        Optional<User> user = userRepository.findByUsernameOrEmail(signUpDTO.getUsername(), signUpDTO.getEmail());
        if (user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User userNew = new User();
        userNew.setUsername(signUpDTO.getUsername());
        userNew.setEmail(signUpDTO.getEmail());
        userNew.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER").get();
        userNew.setRoles(Collections.singleton(role));
        userRepository.save(userNew);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
