package org.dtux.agenda.interceptor.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dtux.agenda.domain.Agenda;
import org.dtux.agenda.external.gateway.AgendaGateway;
import org.dtux.agenda.external.rest.AgendaGatewayImpl;
import org.dtux.interfaces.agenda.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
public class AgendaPessoa implements GrpcOnMessageInterceptor {

    private AgendaGateway agendaGateway;

    public AgendaPessoa(AgendaGateway agendaGateway) {
        this.agendaGateway = agendaGateway;
    }

    @Override
    public Object onMessage(Object message) {

        log.info("Find complement.");
        final Agenda agendaComplemento = agendaGateway.getComplemento();

        log.info("AgendaPessoa setting strategy {}.", this.getClass().getSimpleName());

        final Pessoa.NumeroTelefone numeroTelefone = Pessoa.NumeroTelefone
                .newBuilder()
                .setNumero("242424")
                .setTipo(Pessoa.TipoTelefone.TRABALHO)
                .build();

        final Pessoa pessoa = Pessoa.newBuilder((Pessoa) message)
                .addTelefones(numeroTelefone)
                .setUuid(agendaComplemento.getUuid())
                .build();

        return pessoa;
    }

}
