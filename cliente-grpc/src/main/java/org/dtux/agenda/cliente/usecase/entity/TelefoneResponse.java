package org.dtux.agenda.cliente.usecase.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TelefoneResponse implements Serializable {

    String tipo;
    String telefone;
}
