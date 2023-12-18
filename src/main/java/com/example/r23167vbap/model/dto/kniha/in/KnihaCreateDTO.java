package com.example.r23167vbap.model.dto.kniha.in;

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
public class KnihaCreateDTO {
    @NotBlank
    private String nazev;

    @NotBlank
    private UUID autorId;

    @NotNull
    private LocalDate datumVydani;

    private List<UUID> zanryIds;
}
