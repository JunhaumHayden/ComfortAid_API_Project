package edu.ifsc.fln.confortaid.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioDTO {
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String tipo;

    // Getters and Setters
}
