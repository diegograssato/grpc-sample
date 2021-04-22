package org.dtux.agenda.exception;


import lombok.extern.slf4j.Slf4j;
import org.dtux.agenda.exception.entities.ErrorData;

import java.util.Set;

@Slf4j
public class RepositoryException extends BaseException {

    public static final String CALL_ERROR = "Error making call [ %s ], at address: [ %s ].";
    static final long serialVersionUID = -1033896140745516769L;

    public RepositoryException() {
        super();
    }

    public RepositoryException(final String message, final Set<ErrorData> errors) {
        super(message, errors);
    }

    public RepositoryException(final Set<ErrorData> errors) {
        super(errors);
    }

    public RepositoryException(final ErrorData... errors) {
        super(errors);
    }

    public RepositoryException(final String message) {
        super(message);
    }

    public RepositoryException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(final Throwable cause) {
        super(cause);
    }

    public RepositoryException(final String tag, final Throwable cause, final String[] args) {
        super(tag, cause, args);
    }
}
