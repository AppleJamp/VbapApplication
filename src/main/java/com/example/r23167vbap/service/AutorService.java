package com.example.r23167vbap.service;

import com.example.r23167vbap.model.dto.autor.in.AutorCreateDTO;
import com.example.r23167vbap.model.dto.autor.out.AutorGetDTO;
import com.example.r23167vbap.model.entity.Autor;

import java.util.List;
import java.util.UUID;

public interface AutorService {
    AutorGetDTO create(AutorCreateDTO autorCreateDTO);
    AutorGetDTO update(UUID id, AutorCreateDTO autorCreateDTO);
    AutorGetDTO save(Autor autor);
    void delete(UUID id);
    List<AutorGetDTO> getAll();
    AutorGetDTO get(UUID id);
}
