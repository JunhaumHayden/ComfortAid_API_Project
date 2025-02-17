package edu.ifsc.fln.confortaid.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "foto_usuario")
public class FotoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Lob
    @Column(nullable = false)
    private byte[] foto;
}
