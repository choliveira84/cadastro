package br.com.cliente.cadastro.modelo.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Cliente
 */
@Data
@Entity
public class Cliente implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @NotNull(message = "O nome do cliente não pode estar nulo")
    @NotBlank(message = "O nome do cliente não pode estar em branco")
    @Size(max = 255, message = "O Tamanho do nome do cliente deve ter até 100 caracteres")
    @Column(nullable = false, length = 255)
    private String nome;

    @NotNull(message = "O sexo do cliente não pode estar nulo")
    @NotBlank(message = "O sexo do cliente não pode estar em branco")
    @Size(max = 1, message = "O Tamanho do nome do cliente deve ter até 100 caracteres")
    @Column(nullable = false, length = 1)
    private Character sexo;

    @NotNull(message = "A data de nascimento não pode ser nula")
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @NotNull(message = "A idade do cliente não pode estar nulo")
    @NotBlank(message = "A idade do cliente não pode estar em branco")
    @Column(nullable = false)
    private Integer idade;

    @NotNull(message = "A cidade do cliente não pode estar nulo")
    @NotBlank(message = "A cidade do cliente não pode estar em branco")
    private String cidade;
}