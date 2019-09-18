package br.com.cliente.cadastro.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * FieldMessage
 */
@Getter
@Setter
@AllArgsConstructor
class FieldMessage {
    private String fieldName;
    private String message;
}