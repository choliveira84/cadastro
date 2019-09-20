package br.com.cliente.cadastro.controllers.cliente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.cliente.cadastro.modelo.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClienteDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientePostDTO {

    @NotBlank(message = Cliente.VALIDACAO_NOME)
    private String nome;

    @NotNull(message = Cliente.VALIDACAO_SEXO)
    private Character sexo;

    @NotNull(message = Cliente.VALIDACAO_DATA_NASCIMENTO)
    private String dataNascimento;

    @NotNull(message = "A cidade não pode ser nula")
    private Long cidade;
}