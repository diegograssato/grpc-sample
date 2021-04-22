package org.dtux.agenda.cliente.usecase.mapper;

import org.dtux.agenda.cliente.usecase.entity.AgendaResponse;
import org.dtux.agenda.cliente.usecase.entity.TelefoneResponse;
import org.dtux.interfaces.agenda.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class AgendaResponseMapper {


    public static AgendaResponse map(final Pessoa agenda) {

        List<TelefoneResponse> telefoneResponseList = new ArrayList<>();
        agenda.getTelefonesList().forEach(a -> {
            telefoneResponseList.add(
                    TelefoneResponse.builder()
                            .telefone(a.getNumero())
                            .tipo(a.getTipo().toString())
                            .build()
            );
        });

        return AgendaResponse.builder()
                .id(agenda.getId())
                .uuid(agenda.getUuid())
                .nome(agenda.getNome())
                .email(agenda.getEmail())
                .telefone(telefoneResponseList)
                .build();

    }
}
