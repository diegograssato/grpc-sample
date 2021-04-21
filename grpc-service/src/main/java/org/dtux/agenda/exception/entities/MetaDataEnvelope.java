package org.dtux.agenda.exception.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaDataEnvelope<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonUnwrapped
    private T successData;

    @NonNull
    private MetaData meta;

    public MetaDataEnvelope(final String statusCode,
                            final ErrorCode code,
                            final String message) {
        this.meta = new MetaData(statusCode, code, message);
    }

    public MetaDataEnvelope(final String statusCode,
                            final Set<ErrorData> errors) {
        this.meta = new MetaData(statusCode, errors);
    }
}
