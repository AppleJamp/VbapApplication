package com.example.r23167vbap.model.dto.genre;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenreGetDTO {
    private UUID id;
    private String name;
}
