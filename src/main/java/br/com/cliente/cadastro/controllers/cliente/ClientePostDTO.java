package br.com.cliente.cadastro.controllers.cliente;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.cliente.cadastro.modelo.entity.Cliente;
import lombok.Data;

/**
 * ClienteDTO
 */
@Data
public class ClientePostDTO {

    @NotBlank(message = Cliente.VALIDACAO_NOME)
    private String nome;

    @NotNull(message = Cliente.VALIDACAO_SEXO)
    private Character sexo;

    @NotNull(message = Cliente.VALIDACAO_DATA_NASCIMENTO)
    private LocalDate dataNascimento;

    @NotNull
    private Long cidade;
}