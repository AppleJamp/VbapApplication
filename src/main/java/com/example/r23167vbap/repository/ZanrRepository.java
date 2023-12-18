package com.example.r23167vbap.repository;

import com.example.r23167vbap.model.entity.Zanr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ZanrRepository extends JpaRepository<Zanr, UUID> {
}
