package edu.ifsc.fln.confortaid.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "profissional")
@PrimaryKeyJoinColumn(name = "id_usuario")
@EqualsAndHashCode(callSuper = true)
public class Profissional extends Usuario {
    @Column(nullable = false)
    private String especialidade;

    @Column(unique = true)
    private String registroProfissional;
}