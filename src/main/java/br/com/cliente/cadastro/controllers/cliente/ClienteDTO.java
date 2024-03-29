package br.com.cliente.cadastro.controllers.cliente;

import java.time.LocalDate;

import lombok.Data;

/**
 * ClienteDTO
 */
@Data
public class ClienteDTO {
    private Long id;

    private String nome;

    private Character sexo;

    private LocalDate dataNascimento;

    private Integer idade;

    private Long cidade;
}