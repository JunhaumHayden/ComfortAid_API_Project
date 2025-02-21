package edu.ifsc.fln.confortaid.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.ifsc.fln.confortaid.model.Avaliacao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
    List<Avaliacao> findByServicoId(int servicoId);
}
