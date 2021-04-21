package org.dtux.agenda.exception.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    private ErrorCode code;
    private String field;
    private Object identifier;

    public ErrorData(final ErrorData errorData) {
        this.message = errorData.message;
        this.code = errorData.code;
        this.identifier = errorData.identifier;
        this.field = errorData.field;
    }

    public ErrorData(final String message, final ErrorCode code) {
        this.message = message;
        this.code = code;
    }

    public ErrorData(final String message, final String field, final ErrorCode code) {
        this.message = message;
        this.code = code;
        this.field = field;
    }

    public ErrorData(final ErrorCode code, final String message, final String identifier) {
        this.message = message;
        this.code = code;
        this.identifier = identifier;
    }

    public ErrorData(final ErrorCode code, final String message, final String identifier, final String field) {
        this.message = message;
        this.code = code;
        this.identifier = identifier;
        this.field = field;
    }
}
