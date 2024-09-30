package com.example.r23167vbap.service;

import com.example.r23167vbap.model.dto.author.AuthorCreateDTO;
import com.example.r23167vbap.model.dto.author.AuthorGetDTO;
import com.example.r23167vbap.model.entity.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    AuthorGetDTO create(AuthorCreateDTO authorCreateDTO);
    AuthorGetDTO update(UUID id, AuthorCreateDTO authorCreateDTO);
    AuthorGetDTO save(Author author);
    void delete(UUID id);
    List<AuthorGetDTO> getAll();
    AuthorGetDTO get(UUID id);
}
