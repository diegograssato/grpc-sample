package org.dtux.agenda.interceptor;

import com.google.protobuf.GeneratedMessageV3;
import io.grpc.*;
import io.grpc.stub.MetadataUtils;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.dtux.agenda.domain.Agenda;
import org.dtux.agenda.external.gateway.AgendaGateway;
import org.dtux.agenda.interceptor.strategy.GrpcOnMessageInterceptor;
import org.dtux.interfaces.agenda.Pessoa;
import org.dtux.interfaces.agenda.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
//@GrpcGlobalServerInterceptor
@Component
public class AgendaInterceptor implements ServerInterceptor {

    @Autowired
    private AgendaGateway agendaGateway;

    @Autowired
    private GrpcOnMessageInterceptor grpcOnMessageInterceptor;

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        var serverCall = new ForwardingServerCall.SimpleForwardingServerCall<>(call) {

            @Override
            public void sendMessage(RespT message) {

                log.info("--------------   sendMessage(Response)----------------");

                super.sendMessage(message);
            }

        };

        ServerCall.Listener<ReqT> listenerWithContext = Contexts.interceptCall(Context.current(), serverCall, headers, next);
        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<>(listenerWithContext) {
            @Override
            public void onMessage(ReqT message) {

                log.info("--------------   onMessage(Request) ----------------");

//                message = (ReqT) grpcOnMessageInterceptor.onMessage(message);
//                super.onMessage(message);

                if (message instanceof Pessoa) {

                    final Agenda agendaComplemento = agendaGateway.getComplemento();

                    final Pessoa.NumeroTelefone numeroTelefone = Pessoa.NumeroTelefone
                            .newBuilder()
                            .setNumero("242424")
                            .setTipo(Pessoa.TipoTelefone.RESIDENCIAL)
                            .build();

                    final Pessoa pessoa = Pessoa.newBuilder((Pessoa) message)
                            .addTelefones(numeroTelefone)
                            .setUuid(agendaComplemento.getUuid())
                            .build();

                    log.info("Modify default data {}.", serverCall.getMethodDescriptor().getServiceName());

                    super.onMessage((ReqT) pessoa);
                } else {
                    log.info("Default data {].", serverCall.getMethodDescriptor().getServiceName());
                    super.onMessage(message);
                }

            }
        };
    }


}