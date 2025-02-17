package edu.ifsc.fln.confortaid.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "foto_servico")
public class FotoServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "servico_id", nullable = false)
    private Integer servicoId;

    @Lob
    @Column(nullable = false)
    private byte[] foto;
}
