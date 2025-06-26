package com.nutritrack.app.service;

import com.nutritrack.app.dao.RoleRepository;
import com.nutritrack.app.dao.UserRepository;
import com.nutritrack.app.dto.SignInDTO;
import com.nutritrack.app.dto.SignUpDTO;
import com.nutritrack.app.entity.Role;
import com.nutritrack.app.entity.User;
import com.nutritrack.app.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
    }

    public String login(SignInDTO signInDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getUsernameOrEmail(), signInDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateToken(userDetails);
    }

    public boolean signup(SignUpDTO signUpDTO) {
        //check if username already exists
        Optional<User> user = userRepository.findByUsernameOrEmail(signUpDTO.getUsername(), signUpDTO.getEmail());
        if (user.isPresent()) {
            return false;
        }

        User userNew = new User();
        userNew.setUsername(signUpDTO.getUsername());
        userNew.setEmail(signUpDTO.getEmail());
        userNew.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER").get();
        userNew.setRoles(Collections.singleton(role));
        userRepository.save(userNew);
        return true;
    }

}
