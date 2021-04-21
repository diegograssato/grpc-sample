package org.dtux.agenda.interceptor;

import com.google.protobuf.GeneratedMessageV3;
import io.grpc.*;
import io.grpc.stub.MetadataUtils;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.dtux.agenda.domain.Agenda;
import org.dtux.agenda.external.gateway.AgendaGateway;
import org.dtux.interfaces.agenda.Pessoa;
import org.dtux.interfaces.agenda.Resposta;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@GrpcGlobalServerInterceptor
public class AgendaInterceptor implements ServerInterceptor {

    @Autowired
    private AgendaGateway agendaGateway;

//    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
//
//        log.info("----------------------------------------------");
//        log.info("Intercept data from {}.", this.getClass().getSimpleName());
//
//        Agenda agendaComplemento = agendaGateway.getComplemento();
//
//        // Checar Consentimento
//        log.info(agendaComplemento.toString());
//        metadata.
//        metadata.put("uuid", agendaComplemento.getUuid());
//
//        log.info(serverCall.getMethodDescriptor().getFullMethodName());
//
//        log.info("Finish and checked {}.", this.getClass().getSimpleName());
//
//
//        log.info("Finish intercept data from {}.", this.getClass().getSimpleName());
//        log.info("----------------------------------------------");
//
//        return serverCallHandler.startCall(serverCall, metadata);
//    }

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