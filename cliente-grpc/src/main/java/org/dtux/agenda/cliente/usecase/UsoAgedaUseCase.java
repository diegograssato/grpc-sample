package org.dtux.agenda.cliente.usecase;

import com.google.protobuf.Timestamp;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dtux.agenda.cliente.usecase.entity.AgendaResponse;
import org.dtux.agenda.cliente.usecase.mapper.AgendaResponseMapper;
import org.dtux.interfaces.agenda.AgendaGrpc;
import org.dtux.interfaces.agenda.Input;
import org.dtux.interfaces.agenda.Pessoa;
import org.dtux.interfaces.agenda.Resposta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UsoAgedaUseCase {

    @GrpcClient("agenda")
    private AgendaGrpc.AgendaBlockingStub agendaStub;

    public List<AgendaResponse> adicionar(int number) {

        List<AgendaResponse> agendaResponseList = new ArrayList<>(number);
        for (int i = 1; i <= number; i++) {

            log.info("*************************************************************");
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


            final Resposta output = this.agendaStub.adicionar(input);

            agendaResponseList.add(AgendaResponseMapper.map(output.getResultado()));
        }

        return agendaResponseList;

    }

    public List<AgendaResponse> adicionarStream(int number) {

        Input input = Input.newBuilder().setNumber(number).build();
        List<AgendaResponse> agendaResponseList = new ArrayList<>(number);

        var output = this.agendaStub.adicionarStream(input);

        output.forEachRemaining(a -> agendaResponseList.add(AgendaResponseMapper.map(a.getResultado())));

        return agendaResponseList;

    }


}
