package com.example.r23167vbap.repository;

import com.example.r23167vbap.model.entity.Uzivatel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UzivatelRepository extends JpaRepository<Uzivatel, UUID> {
    Boolean existsByEmail(String email);
    Optional<Uzivatel> findByEmail(String email);
}
