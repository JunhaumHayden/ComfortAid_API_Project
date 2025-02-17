package edu.ifsc.fln.confortaid.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.ifsc.fln.confortaid.model.Avaliacao;
import org.springframework.stereotype.Repository;
@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
}
