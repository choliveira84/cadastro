package br.com.cliente.cadastro.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DefaultError
 */
@Getter
@Setter
@AllArgsConstructor
class DefaultError {

    private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}