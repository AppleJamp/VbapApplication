package com.example.r23167vbap.model.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDTO {
    @NotBlank
    private String name;

    @NotBlank
    private UUID authorId;

    @NotNull
    private LocalDate releaseDate;

    private List<UUID> genresIds;
}
