package org.dtux.agenda.external.rest.repository.template;

import lombok.extern.slf4j.Slf4j;
import org.dtux.agenda.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

import java.util.Optional;

@Slf4j
public abstract class RestGetTemplate<T> {

    @Autowired
    protected RestTemplate restTemplate;

    protected abstract ParameterizedTypeReference<T> type();

    protected Optional<T> callRest(final UriComponents uri, final HttpEntity<Object> httpRequest, final HttpMethod method) {
        try {
            log.info("Preparing request to {} with method {} from {}.", uri.toUriString(), method.toString(), this.getClass().getSimpleName());
            final ResponseEntity<T> response = restTemplate.exchange(uri.toUriString(), method, httpRequest, type());

            log.info("Preparing response to {} from {}.", response.getStatusCode() == HttpStatus.OK ? response.getBody().toString() : null, this.getClass().getSimpleName());
            return response.getStatusCode() == HttpStatus.OK ? Optional.ofNullable(response.getBody()) : Optional.empty();
        } catch (final Exception e) {
            final String message = String.format(RepositoryException.CALL_ERROR, method, uri.toUriString());
            log.error(message, e);
            throw new RepositoryException(message, e);
        }
    }

    protected Optional<T> get(final UriComponents uri) {
        log.info("Preparing get request to {} from {}.", uri.toUriString(), this.getClass().getSimpleName());
        return callRest(uri, new HttpEntity<>(headers()), HttpMethod.GET);
    }

    public HttpHeaders headers() {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

}