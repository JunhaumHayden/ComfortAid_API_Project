package edu.ifsc.fln.confortaid.DTO;

    import lombok.*;

    import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String cep;
    private String numeroEndereco;
    private String complementoEndereco;
    private String cpf;
    private LocalDate nascimento;
}
