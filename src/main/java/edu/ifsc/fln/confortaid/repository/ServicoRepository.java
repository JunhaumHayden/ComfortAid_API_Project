package edu.ifsc.fln.confortaid.repository;

import edu.ifsc.fln.confortaid.model.Avaliacao;
import edu.ifsc.fln.confortaid.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    List<Servico> findByProfissionalId(int profissionalId);
}

