package org.dtux.agenda.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.dtux.agenda.exception.entities.ErrorData;
import org.dtux.agenda.exception.message.BundleMessage;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class BaseException extends RuntimeException {

    static final long serialVersionUID = -9034816190742716963L;

    @Getter
    private Set<ErrorData> errors;

    public BaseException() {
        super();
    }

    public BaseException(final Set<ErrorData> errors) {
        this.errors = errors;
    }

    public BaseException(final ErrorData... errors) {
        this.errors = Arrays.stream(errors).collect(Collectors.toSet());
    }

    public BaseException(final String message) {
        super(message);
    }

    public BaseException(final String message, final Set<ErrorData> errors) {
        super(message);
        this.errors = errors;
    }

    public BaseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BaseException(final String message, final Throwable cause, final Set<ErrorData> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public BaseException(final Throwable cause) {
        super(cause);
    }

    public BaseException(final Throwable cause, final Set<ErrorData> errors) {
        super(cause);
        this.errors = errors;
    }

    public BaseException(final String tag, final Throwable cause, final String[] args) {
        super(getMessage(tag, args), cause);
    }

    private static String getMessage(final String tag, final String[] args) {
        return BundleMessage.getMessage(tag, args);
    }

}
