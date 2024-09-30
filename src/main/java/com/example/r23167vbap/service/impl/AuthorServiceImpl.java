package com.example.r23167vbap.service.impl;

import com.example.r23167vbap.model.dto.author.AuthorCreateDTO;
import com.example.r23167vbap.model.dto.author.AuthorGetDTO;
import com.example.r23167vbap.model.entity.Author;
import com.example.r23167vbap.repository.AuthorRepository;
import com.example.r23167vbap.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    public AuthorGetDTO create(AuthorCreateDTO authorCreateDTO) {
        log.info("create({})", authorCreateDTO);
        return this.save(modelMapper.map(authorCreateDTO, Author.class));
    }

    @Override
    public AuthorGetDTO update(UUID id, AuthorCreateDTO authorCreateDTO) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Author with id: " + id + "wasn't found"));
        modelMapper.map(authorCreateDTO, author);

        log.info("update({})", authorCreateDTO);
        return this.save(author);
    }

    @Override
    public void delete(UUID id) {
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorGetDTO get(UUID id) {
        return modelMapper.map(authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Author with id: " + id + "wasn't found")), AuthorGetDTO.class);
    }

    @Override
    public List<AuthorGetDTO> getAll() {
        return authorRepository.findAll().stream().map(author -> modelMapper.map(author, AuthorGetDTO.class)).toList();
    }

    @Override
    public AuthorGetDTO save(Author author) {
        return modelMapper.map(authorRepository.save(author), AuthorGetDTO.class);
    }
}
