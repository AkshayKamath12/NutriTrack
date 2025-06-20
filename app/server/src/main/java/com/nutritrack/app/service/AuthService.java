package com.nutritrack.app.service;

import com.nutritrack.app.dao.RoleRepository;
import com.nutritrack.app.dao.UserRepository;
import com.nutritrack.app.dto.SignInDTO;
import com.nutritrack.app.dto.SignUpDTO;
import com.nutritrack.app.entity.Role;
import com.nutritrack.app.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public AuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void login(SignInDTO signInDTO, HttpServletRequest request) {
        String usernameOrEmail = signInDTO.getUsernameOrEmail();
        String password = signInDTO.getPassword();
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usernameOrEmail, password));
        SecurityContextHolder.getContext().setAuthentication(auth);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", new SecurityContextImpl(auth));
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
