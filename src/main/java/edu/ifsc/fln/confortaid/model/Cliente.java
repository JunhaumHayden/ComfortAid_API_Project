package edu.ifsc.fln.confortaid.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id_usuario")
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Usuario {
    @Column(nullable = false, unique = true)
    private String cpf;
    private LocalDate nascimento;
}

