package com.example.r23167vbap.model.dto.user;

import com.example.r23167vbap.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
