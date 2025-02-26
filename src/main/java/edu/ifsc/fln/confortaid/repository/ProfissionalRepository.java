package edu.ifsc.fln.confortaid.repository;

import edu.ifsc.fln.confortaid.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Integer> {
    Optional<Profissional> findByEmail(String email);
    Optional<Profissional> findByRegistroProfissional(String registroProfissional);
}

