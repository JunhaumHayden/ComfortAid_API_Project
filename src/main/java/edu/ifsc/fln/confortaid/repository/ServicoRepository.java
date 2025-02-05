package edu.ifsc.fln.confortaid.repository;

import edu.ifsc.fln.confortaid.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}

