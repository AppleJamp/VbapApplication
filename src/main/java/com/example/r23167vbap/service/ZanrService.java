package com.example.r23167vbap.service;

import com.example.r23167vbap.model.dto.zanr.in.ZanrCreateDTO;
import com.example.r23167vbap.model.dto.zanr.out.ZanrGetDTO;
import com.example.r23167vbap.model.entity.Zanr;

import java.util.List;
import java.util.UUID;

public interface ZanrService {
    ZanrGetDTO create(ZanrCreateDTO zanrCreateDTO);
    ZanrGetDTO update(UUID id, ZanrCreateDTO zanrCreateDTO);
    ZanrGetDTO save(Zanr zane);
    void delete(UUID id);
    List<ZanrGetDTO> getAll();
    ZanrGetDTO get(UUID id);
}
