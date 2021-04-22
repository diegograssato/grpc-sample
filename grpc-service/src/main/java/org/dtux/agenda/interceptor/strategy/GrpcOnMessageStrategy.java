package org.dtux.agenda.interceptor.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dtux.agenda.external.gateway.AgendaGateway;
import org.dtux.interfaces.agenda.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GrpcOnMessageStrategy implements GrpcOnMessageInterceptor {

    private static GrpcOnMessageInterceptor strategy;

    @Autowired
    private AgendaGateway agendaGateway;

    @Override
    public Object onMessage(Object message) {

        if (message instanceof Pessoa) {
            strategy = new AgendaPessoa(agendaGateway);
        }

        log.info("GrpcOnMessageStrategy setting strategy {}.", strategy.getClass().getSimpleName());

        return strategy.onMessage(message);
    }
}
