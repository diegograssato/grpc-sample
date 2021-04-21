package org.dtux.agenda.exception;

import lombok.extern.slf4j.Slf4j;
import org.dtux.agenda.exception.entities.ErrorData;

import java.util.Set;

@Slf4j
public class UseCaseException extends BaseException {

    static final long serialVersionUID = -1033896140745516769L;

    public UseCaseException() {
        super();
    }

    public UseCaseException(final String message, final Set<ErrorData> errors) {
        super(message, errors);
    }

    public UseCaseException(final Set<ErrorData> errors) {
        super(errors);
    }

    public UseCaseException(final ErrorData... errors) {
        super(errors);
    }

    public UseCaseException(final String message) {
        super(message);
    }

    public UseCaseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UseCaseException(final Throwable cause) {
        super(cause);
    }

    public UseCaseException(final String tag, final Throwable cause, final String[] args) {
        super(tag, cause, args);
    }
}
