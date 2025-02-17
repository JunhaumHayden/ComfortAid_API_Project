package edu.ifsc.fln.confortaid.repository;

import edu.ifsc.fln.confortaid.model.FotoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoUsuarioRepository extends JpaRepository<FotoUsuario, Integer> {
    List<FotoUsuario> findByUsuarioId(Integer usuarioId);

    void deleteByUsuarioId(Integer usuarioId);
}
