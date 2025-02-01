package com.example.r23167vbap.service.impl;

import com.example.r23167vbap.exception.NotFoundException;
import com.example.r23167vbap.model.dto.genre.GenreCreateDTO;
import com.example.r23167vbap.model.dto.genre.GenreGetDTO;
import com.example.r23167vbap.model.entity.Genre;
import com.example.r23167vbap.repository.GenreRepository;
import com.example.r23167vbap.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Override
    public GenreGetDTO create(GenreCreateDTO genreCreateDTO) {
        log.info("create({})", genreCreateDTO);
        return this.save(modelMapper.map(genreCreateDTO, Genre.class));
    }

    @Override
    public GenreGetDTO update(UUID id, GenreCreateDTO genreCreateDTO) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre with id: " + id + " not found"));
        modelMapper.map(genreCreateDTO, genre);

        log.info("update({})", genreCreateDTO);
        return this.save(genre);
    }

    @Override
    public void delete(UUID id) {
        genreRepository.deleteById(id);
    }

    @Override
    public GenreGetDTO get(UUID id) {
        return modelMapper.map(genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre with id: " + id + " not found")), GenreGetDTO.class);
    }

    @Override
    public List<GenreGetDTO> getAll() {
        return genreRepository.findAll().stream().map(genre -> modelMapper.map(genre, GenreGetDTO.class)).toList();
    }

    @Override
    public GenreGetDTO save(Genre genre) {
        return modelMapper.map(genreRepository.save(genre), GenreGetDTO.class);
    }
}
