package com.example.r23167vbap.service.impl;

import com.example.r23167vbap.model.dto.kniha.in.KnihaCreateDTO;
import com.example.r23167vbap.model.dto.kniha.out.KnihaGetDTO;
import com.example.r23167vbap.model.entity.Kniha;
import com.example.r23167vbap.repository.KnihaRepository;
import com.example.r23167vbap.service.KnihaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KnihaServiceImpl implements KnihaService {
    private final KnihaRepository knihaRepository;
    private final ModelMapper modelMapper;

    @Override
    public KnihaGetDTO create(KnihaCreateDTO knihaCreateDTO) {
        log.info("create({})", knihaCreateDTO);
        return this.save(modelMapper.map(knihaCreateDTO, Kniha.class));
    }

    @Override
    public KnihaGetDTO update(UUID id, KnihaCreateDTO knihaCreateDTO) {
        Kniha kniha = knihaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Kniha s id: " + id + " nebyla nalezena"));
        modelMapper.map(knihaCreateDTO, kniha);

        log.info("update({})", knihaCreateDTO);
        return this.save(kniha);
    }

    @Override
    public void delete(UUID id) {
        knihaRepository.deleteById(id);
    }

    @Override
    public KnihaGetDTO get(UUID id) {
        return modelMapper.map(knihaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Kniha s id: " + id + " nebyla nalezena")), KnihaGetDTO.class);
    }

    @Override
    public List<KnihaGetDTO> getAll() {
        return knihaRepository.findAll().stream().map(kniha -> modelMapper.map(kniha, KnihaGetDTO.class)).toList();
    }

    @Override
    public KnihaGetDTO save(Kniha kniha) {
        return modelMapper.map(knihaRepository.save(kniha), KnihaGetDTO.class);
    }
}
