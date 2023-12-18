package com.example.r23167vbap.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "knihy")
public class Kniha {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;

    @NotNull
    private String nazev;

    @NotNull
    @ManyToOne(targetEntity = Autor.class)
    private Autor autor;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "datum_vydani", nullable = false)
    private LocalDate datumVydani = LocalDate.now();

    @ManyToMany
    @JoinTable(
        name = "knihy_zanry",
        joinColumns = @JoinColumn(name = "kniha_id"),
        inverseJoinColumns = @JoinColumn(name = "zanr_id")
    )
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private List<Zanr> zanry;
}
