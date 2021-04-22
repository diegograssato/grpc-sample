package org.dtux.agenda.usecase;


import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.dtux.agenda.interceptor.AgendaInterceptor;
import org.dtux.interfaces.agenda.AgendaGrpc;
import org.dtux.interfaces.agenda.Input;
import org.dtux.interfaces.agenda.Pessoa;
import org.dtux.interfaces.agenda.Resposta;

@GrpcService(interceptors = { AgendaInterceptor.class })
public class AgendaUseCase extends AgendaGrpc.AgendaImplBase {

    @Override
    public void adicionar(Pessoa request, StreamObserver<Resposta> response) {

        Runnable runnable = () -> {

            Resposta output = Resposta.newBuilder()
                    .setResultado(request).build();

            response.onNext(output);
            response.onCompleted();
        };
        new Thread(runnable).start();
    }

    @Override
    public void adicionarStream(Input request, StreamObserver<Resposta> responseObserver) {

        Runnable runnable = () -> {
            for (int i = 1; i <= request.getNumber(); i++) {

                final Pessoa.NumeroTelefone numeroTelefone = Pessoa.NumeroTelefone
                        .newBuilder()
                        .setNumero("111-222-".concat(String.valueOf(i)))
                        .setTipo(Pessoa.TipoTelefone.CELULAR)
                        .build();

                final Pessoa input = Pessoa.newBuilder()
                        .setId(i)
                        .setNome("Jose -" + i)
                        .setEmail("joao_da_silva_" + i)
                        .addTelefones(numeroTelefone)
                        .setLastUpdated(Timestamp.newBuilder().build())
                        .build();

                final Resposta output = Resposta.newBuilder()
                        .setResultado(input).build();

                responseObserver.onNext(output);
            }
            responseObserver.onCompleted();
        };
        new Thread(runnable).start();
    }

    @Override
    public void buscar(Pessoa request, StreamObserver<Pessoa> response) {

    }

}
