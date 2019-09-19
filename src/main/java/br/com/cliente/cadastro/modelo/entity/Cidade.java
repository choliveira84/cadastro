package br.com.cliente.cadastro.modelo.entity;

import java.io.Serializable;
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
 * Cidade
 */
@Data
@Entity
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String VALIDACAO_NOME = "O nome da cidade não pode estar em branco";
    public static final String VALIDACAO_TM_NOME = "O nome da cidade deve ter até 255 caracters";
    public static final String VALIDACAO_SIGLA_ESTADO = "A sigla do estado não pode estar em branco";
    public static final String VALIDACAO_TM_SIGLA_ESTADO = "Só são permitidos 2 caracteres para a sigla do estado";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @NotBlank(message = VALIDACAO_NOME)
    @Size(max = 255, message = VALIDACAO_TM_NOME)
    @Column(nullable = false, length = 255)
    private String nome;

    @NotNull(message = VALIDACAO_SIGLA_ESTADO)
    @Size(max = 2, min = 2, message = VALIDACAO_TM_SIGLA_ESTADO)
    @Column(nullable = false, length = 2)
    private String estado;

    public void setEstado(String estado) {
        this.estado = estado.toUpperCase();
    }

}