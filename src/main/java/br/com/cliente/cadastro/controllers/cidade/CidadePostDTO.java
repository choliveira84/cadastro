package br.com.cliente.cadastro.controllers.cidade;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.cliente.cadastro.modelo.entity.Cidade;
import lombok.Data;

/**
 * ClienteDTO
 */
@Data
public class CidadePostDTO {
    @NotBlank(message = Cidade.VALIDACAO_NOME)
    private String nome;

    @NotBlank(message = Cidade.VALIDACAO_SIGLA_ESTADO)
    @Size(max = 2, min = 2, message = Cidade.VALIDACAO_TM_SIGLA_ESTADO)
    private String estado;
}
