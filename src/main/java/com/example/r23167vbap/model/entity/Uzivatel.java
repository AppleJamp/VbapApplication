package com.example.r23167vbap.model.entity;

import com.example.r23167vbap.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`account`")
public class Uzivatel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;

    @Column(length = 50, nullable = false)
    private String jmeno;

    @Column(length = 50, nullable = false)
    private String prijmeni;

    @Column(length = 320, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String heslo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    @PrePersist
    private void encodePassword() {
        this.setHeslo(new BCryptPasswordEncoder().encode(this.getHeslo()));
    }
}
