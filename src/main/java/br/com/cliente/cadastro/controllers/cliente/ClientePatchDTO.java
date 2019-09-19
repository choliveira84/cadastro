package br.com.cliente.cadastro.controllers.cliente;

import javax.validation.constraints.NotBlank;

import br.com.cliente.cadastro.modelo.entity.Cliente;
import lombok.Data;

@Data
public class ClientePatchDTO {

    @NotBlank(message = Cliente.VALIDACAO_NOME)
    private String nome;
}
