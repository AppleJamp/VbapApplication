package com.example.r23167vbap.model.dto.uzivatel.out;

import com.example.r23167vbap.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UzivatelGetDTO {
    private String id;
    private String jmeno;
    private String prijmeni;
    private String email;
    private Role role;
}
