package edu.ifsc.fln.confortaid.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "foto_profissional")
public class FotoProfissional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profissional_id", nullable = false)
    private Integer profissionalId;

    @Lob
    @Column(nullable = false)
    private byte[] foto;
}
