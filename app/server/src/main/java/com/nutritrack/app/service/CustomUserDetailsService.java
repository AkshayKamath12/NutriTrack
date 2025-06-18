package com.nutritrack.app.service;

import com.nutritrack.app.dao.UserRepository;
import com.nutritrack.app.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameOrEmail(username, username);
        if(user.isPresent()) {
            User userDetails = user.get();
            Set<GrantedAuthority> authorities = userDetails
                    .getRoles()
                    .stream()
                    .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
            return new org.springframework.security.core.userdetails.User(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    authorities
            );
        } else {
            throw new UsernameNotFoundException("Invalid username or email");
        }
    }
}
