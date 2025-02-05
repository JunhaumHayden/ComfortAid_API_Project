package edu.ifsc.fln.confortaid.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalDTO  {
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String cep;
    private String especialidade;
    private String registroProfissional;
}

