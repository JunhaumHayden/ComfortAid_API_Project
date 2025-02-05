package edu.ifsc.fln.confortaid.repository;

import edu.ifsc.fln.confortaid.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
}
