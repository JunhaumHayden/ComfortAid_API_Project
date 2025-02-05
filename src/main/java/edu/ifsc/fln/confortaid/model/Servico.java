package edu.ifsc.fln.confortaid.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(length = 500)
    private String descricao;

    private Double preco;

    private Integer duracao; // Duração em minutos

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    private LocalDate dataDisponivel;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    public enum Situacao {
        ATIVO, INATIVO, BLOQUEADO
    }
}


