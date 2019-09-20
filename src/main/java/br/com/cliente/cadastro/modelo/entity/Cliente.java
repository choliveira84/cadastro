package br.com.cliente.cadastro.modelo.entity;

import static java.lang.Character.toUpperCase;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    private static final long serialVersionUID = 1L;

    public static final String VALIDACAO_NOME = "O nome do cliente não pode estar em branco";
    public static final String VALIDACAO_DATA_NASCIMENTO = "A data de nascimento não pode ser nula";
    public static final String VALIDACAO_SEXO = "O sexo do cliente não pode estar nulo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @NotBlank(message = VALIDACAO_NOME)
    @Size(max = 255, message = "O Tamanho do nome do cliente deve ter até 100 caracteres")
    @Column(nullable = false, length = 255)
    private String nome;

    @NotNull(message = VALIDACAO_SEXO)
    @Column(nullable = false, length = 1)
    private Character sexo;

    @NotNull(message = VALIDACAO_DATA_NASCIMENTO)
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @NotNull(message = "A idade do cliente não pode estar nulo")
    @Column(nullable = false)
    private Integer idade;

    @ManyToOne
    private Cidade cidade;

    public void setSexo(Character sexo) {
        this.sexo = toUpperCase(sexo);
    }
}