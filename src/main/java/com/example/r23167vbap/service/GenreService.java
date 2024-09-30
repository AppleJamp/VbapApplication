package com.example.r23167vbap.service;

import com.example.r23167vbap.model.dto.genre.GenreCreateDTO;
import com.example.r23167vbap.model.dto.genre.GenreGetDTO;
import com.example.r23167vbap.model.entity.Genre;

import java.util.List;
import java.util.UUID;

public interface GenreService {
    GenreGetDTO create(GenreCreateDTO genreCreateDTO);
    GenreGetDTO update(UUID id, GenreCreateDTO genreCreateDTO);
    GenreGetDTO save(Genre genre);
    void delete(UUID id);
    List<GenreGetDTO> getAll();
    GenreGetDTO get(UUID id);
}
