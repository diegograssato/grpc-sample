package org.dtux.agenda.exception;

import lombok.extern.slf4j.Slf4j;
import org.dtux.agenda.exception.entities.ErrorData;

import java.util.Set;

@Slf4j
public class GatewayException extends BaseException {

    public static final String GATEWAY_ACCESS_ERROR = "[ %s ] Error when trying to get informations. %s";
    static final long serialVersionUID = -1033896140745516769L;

    public GatewayException() {
        super();
    }

    public GatewayException(final String message, final Set<ErrorData> errors) {
        super(message, errors);
    }

    public GatewayException(final Set<ErrorData> errors) {
        super(errors);
    }

    public GatewayException(final ErrorData... errors) {
        super(errors);
    }

    public GatewayException(final String message) {
        super(message);
    }

    public GatewayException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public GatewayException(final Throwable cause) {
        super(cause);
    }

    public GatewayException(final String tag, final Throwable cause, final String[] args) {
        super(tag, cause, args);
    }
}
