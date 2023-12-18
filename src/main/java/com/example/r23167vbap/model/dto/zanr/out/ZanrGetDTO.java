package com.example.r23167vbap.model.dto.zanr.out;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ZanrGetDTO {
    private UUID id;
    private String nazev;
}
