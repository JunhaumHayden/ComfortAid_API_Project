package edu.ifsc.fln.confortaid.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
public class Cliente extends Usuario {
    private String cpf;

}

