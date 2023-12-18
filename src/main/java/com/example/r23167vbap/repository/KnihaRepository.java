package com.example.r23167vbap.repository;

import com.example.r23167vbap.model.entity.Kniha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KnihaRepository extends JpaRepository<Kniha, UUID> {
}
