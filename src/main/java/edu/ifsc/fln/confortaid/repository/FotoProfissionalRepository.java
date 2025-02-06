package edu.ifsc.fln.confortaid.repository;

import edu.ifsc.fln.confortaid.model.FotoProfissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FotoProfissionalRepository extends JpaRepository<FotoProfissional, Integer> {
    List<FotoProfissional> findByProfissionalId(Integer profissionalId);
}
