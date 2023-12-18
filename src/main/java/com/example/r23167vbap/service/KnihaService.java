package com.example.r23167vbap.service;


import com.example.r23167vbap.model.dto.kniha.in.KnihaCreateDTO;
import com.example.r23167vbap.model.dto.kniha.out.KnihaGetDTO;
import com.example.r23167vbap.model.entity.Kniha;

import java.util.List;
import java.util.UUID;

public interface KnihaService {
    KnihaGetDTO create(KnihaCreateDTO knihaCreateDTO);
    KnihaGetDTO update(UUID id, KnihaCreateDTO knihaCreateDTO);
    KnihaGetDTO save(Kniha kniha);
    void delete(UUID id);
    List<KnihaGetDTO> getAll();
    KnihaGetDTO get(UUID id);
}
