package edu.ifsc.fln.confortaid.repository;

import edu.ifsc.fln.confortaid.model.FotoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoServicoRepository extends JpaRepository<FotoServico, Integer> {
    List<FotoServico> findByServicoId(Integer servicoId);

    void deleteByServicoId(Integer servicoId);
}
