package com.example.r23167vbap.model.dto.book;

import com.example.r23167vbap.model.dto.author.AuthorGetDTO;
import com.example.r23167vbap.model.dto.genre.GenreGetDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookGetDTO {
    private UUID id;
    private String name;
    private AuthorGetDTO author;
    private LocalDate releaseDate;
    private List<GenreGetDTO> genres;
}
