package edu.ifsc.fln.confortaid.model;

import jakarta.persistence.Entity;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor //construtor que receba todos os campos
@NoArgsConstructor //construtor sem argumentos
public class Profissional extends Usuario {
    private String especialidade;
    private String registroProfissional;


}

