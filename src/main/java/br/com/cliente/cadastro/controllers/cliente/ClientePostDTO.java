package br.com.cliente.cadastro.controllers.cliente;

import java.time.LocalDate;

import lombok.Data;

/**
 * ClienteDTO
 */
@Data
public class ClientePostDTO {
    
    private String nome;

    private Character sexo;

    private LocalDate dataNascimento;

    private String cidade;
}