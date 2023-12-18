package com.example.r23167vbap.service.impl;

import com.example.r23167vbap.model.dto.zanr.in.ZanrCreateDTO;
import com.example.r23167vbap.model.dto.zanr.out.ZanrGetDTO;
import com.example.r23167vbap.model.entity.Zanr;
import com.example.r23167vbap.repository.ZanrRepository;
import com.example.r23167vbap.service.ZanrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ZanrServiceImpl implements ZanrService {
    private final ZanrRepository zanrRepository;
    private final ModelMapper modelMapper;

    @Override
    public ZanrGetDTO create(ZanrCreateDTO zanrCreateDTO) {
        log.info("create({})", zanrCreateDTO);
        return this.save(modelMapper.map(zanrCreateDTO, Zanr.class));
    }

    @Override
    public ZanrGetDTO update(UUID id, ZanrCreateDTO zanrCreateDTO) {
        Zanr zanr = zanrRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Žánr s id: " + id + " nebyl nalezen"));
        modelMapper.map(zanrCreateDTO, zanr);

        log.info("update({})", zanrCreateDTO);
        return this.save(zanr);
    }

    @Override
    public void delete(UUID id) {
        zanrRepository.deleteById(id);
    }

    @Override
    public ZanrGetDTO get(UUID id) {
        return modelMapper.map(zanrRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Žánr s id: " + id + " nebyl nalezen")), ZanrGetDTO.class);
    }

    @Override
    public List<ZanrGetDTO> getAll() {
        return zanrRepository.findAll().stream().map(zanr -> modelMapper.map(zanr, ZanrGetDTO.class)).toList();
    }

    @Override
    public ZanrGetDTO save(Zanr zanr) {
        return modelMapper.map(zanrRepository.save(zanr), ZanrGetDTO.class);
    }
}
