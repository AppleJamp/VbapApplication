package com.example.r23167vbap.model.dto.autor.in;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AutorCreateDTO {
    @NotBlank
    private String jmeno;

    @NotBlank
    private String prijmeni;

    private List<UUID> knihyIds;
}
