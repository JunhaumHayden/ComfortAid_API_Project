package edu.ifsc.fln.confortaid.repository;

import edu.ifsc.fln.confortaid.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}

