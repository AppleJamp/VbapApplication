package com.example.r23167vbap.model.dto.uzivatel.in;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UzivatelPostDTO {

    @NotBlank
    private String jmeno;

    @NotBlank
    private String prijmeni;

    @Email
    private String email;

    @NotBlank
    private String heslo;
}
