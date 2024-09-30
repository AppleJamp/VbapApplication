package com.example.r23167vbap.model.dto.author;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorCreateDTO {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private List<UUID> bookIds;
}
