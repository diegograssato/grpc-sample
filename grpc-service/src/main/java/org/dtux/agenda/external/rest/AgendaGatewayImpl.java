package org.dtux.agenda.external.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dtux.agenda.domain.Agenda;
import org.dtux.agenda.exception.GatewayException;
import org.dtux.agenda.external.gateway.AgendaGateway;
import org.dtux.agenda.external.rest.repository.AgendaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class AgendaGatewayImpl implements AgendaGateway {

    private final AgendaRepository repository;

    @Override
    @Cacheable("getComplemento")
    public Agenda getComplemento() {
        try {

            log.info("Get {} from gateway.", this.getClass().getSimpleName());

            return repository.getComplemento();

        } catch (final Exception e) {

            final String exMessage = "Error when trying " + this.getClass().getSimpleName();
            log.error(exMessage);
            throw new GatewayException(exMessage, e);
        }

    }


}
