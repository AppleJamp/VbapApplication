package com.example.r23167vbap.model.dto.kniha.out;

import com.example.r23167vbap.model.dto.autor.out.AutorGetDTO;
import com.example.r23167vbap.model.dto.zanr.out.ZanrGetDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KnihaGetDTO {
    private UUID id;
    private String nazev;
    private AutorGetDTO autor;
    private LocalDate datumVydani;
    private List<ZanrGetDTO> zanry;
}
