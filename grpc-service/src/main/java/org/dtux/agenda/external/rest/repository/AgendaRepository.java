package org.dtux.agenda.external.rest.repository;

import lombok.extern.slf4j.Slf4j;
import org.dtux.agenda.domain.Agenda;
import org.dtux.agenda.external.rest.repository.template.RestGetTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Slf4j
@Repository
public class AgendaRepository extends RestGetTemplate<Agenda> {

    @Value("${application.rest.agenda.url}")
    String agendaComplementarUrl;

    public Agenda getComplemento() {

        log.info("Get {} from repository.", this.getClass().getSimpleName());
        final UriComponents uri = UriComponentsBuilder.fromHttpUrl(agendaComplementarUrl).build();
        final Optional<Agenda> response = get(uri);

        return response.orElse(null);
    }

    @Override
    protected ParameterizedTypeReference<Agenda> type() {
        return new ParameterizedTypeReference<>() {
        };
    }
}
