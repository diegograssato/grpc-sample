package org.dtux.agenda.exception.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String statusCode;
    private Set<ErrorData> errors;

    public MetaData(final String statusCode,
                    final ErrorCode code,
                    final String message) {
        this.statusCode = statusCode;
        this.errors = new HashSet<>(Collections.singleton(new ErrorData(message, code)));
    }
}
