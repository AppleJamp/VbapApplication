package com.example.r23167vbap.model.dto.autor.out;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AutorGetDTO {
    private UUID id;
    private String jmeno;
    private String prijmeni;
}
