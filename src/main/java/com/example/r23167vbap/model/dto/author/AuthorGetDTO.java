package com.example.r23167vbap.model.dto.author;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorGetDTO {
    private UUID id;
    private String firstName;
    private String lastName;
}
