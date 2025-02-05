package edu.ifsc.fln.confortaid.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor //construtor que receba todos os campos
@NoArgsConstructor //construtor sem argumentos
@MappedSuperclass
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cep;
    private String numeroEndereco;
    private String complementoEndereco;

}

