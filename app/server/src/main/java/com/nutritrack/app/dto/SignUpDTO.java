package com.nutritrack.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SignUpDTO {
    private String username;
    private String email;
    private String password;
}
