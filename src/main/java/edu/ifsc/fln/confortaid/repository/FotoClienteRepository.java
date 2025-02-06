package edu.ifsc.fln.confortaid.repository;

import edu.ifsc.fln.confortaid.model.FotoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FotoClienteRepository extends JpaRepository<FotoCliente, Integer> {
    List<FotoCliente> findByClienteId(Integer clienteId);
}
