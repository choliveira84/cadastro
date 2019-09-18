package br.com.cliente.cadastro.exceptions;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * ValidationError
 */
@Getter
@Setter
class ValidationError extends DefaultError {

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	private List<FieldMessage> errors = new ArrayList<>();

	public void addError(String fieldName, String messagem) {
		errors.add(new FieldMessage(fieldName, messagem));
	}
}