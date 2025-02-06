package edu.ifsc.fln.confortaid.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "foto_cliente")
public class FotoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cliente_id", nullable = false)
    private Integer clienteId;

    @Lob
    @Column(nullable = false)
    private byte[] foto;
}
