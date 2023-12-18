package com.example.r23167vbap.service.impl;

import com.example.r23167vbap.model.dto.autor.in.AutorCreateDTO;
import com.example.r23167vbap.model.dto.autor.out.AutorGetDTO;
import com.example.r23167vbap.model.entity.Autor;
import com.example.r23167vbap.repository.AutorRepository;
import com.example.r23167vbap.service.AutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AutorServiceImpl implements AutorService {
    private final AutorRepository autorRepository;
    private final ModelMapper modelMapper;

    @Override
    public AutorGetDTO create(AutorCreateDTO autorCreateDTO) {
        log.info("create({})", autorCreateDTO);
        return this.save(modelMapper.map(autorCreateDTO, Autor.class));
    }

    @Override
    public AutorGetDTO update(UUID id, AutorCreateDTO autorCreateDTO) {
        Autor autor = autorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Autor s id: " + id + " nebyl nalezen"));
        modelMapper.map(autorCreateDTO, autor);

        log.info("update({})", autorCreateDTO);
        return this.save(autor);
    }

    @Override
    public void delete(UUID id) {
        autorRepository.deleteById(id);
    }

    @Override
    public AutorGetDTO get(UUID id) {
        return modelMapper.map(autorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Autor s id: " + id + " nebyl nalezen")), AutorGetDTO.class);
    }

    @Override
    public List<AutorGetDTO> getAll() {
        return autorRepository.findAll().stream().map(autor -> modelMapper.map(autor, AutorGetDTO.class)).toList();
    }

    @Override
    public AutorGetDTO save(Autor autor) {
        return modelMapper.map(autorRepository.save(autor), AutorGetDTO.class);
    }
}
