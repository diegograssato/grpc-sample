package org.dtux.agenda.cliente.usecase.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class AgendaResponse implements Serializable {

    int id;
    String uuid;
    String nome;
    String email;
    List<TelefoneResponse> telefone;
}
