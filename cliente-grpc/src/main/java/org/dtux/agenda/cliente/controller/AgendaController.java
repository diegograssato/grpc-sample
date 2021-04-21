package org.dtux.agenda.cliente.controller;

import org.dtux.agenda.cliente.usecase.UsoAgedaUseCase;
import org.dtux.agenda.cliente.usecase.entity.AgendaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("grpc")
public class AgendaController {

    @Autowired
    private UsoAgedaUseCase useCase;

    @GetMapping("/agenda/{number}")
    public List<AgendaResponse> getResponseUnary(@PathVariable int number) {
        return this.useCase.adicionar(number);
    }

    @GetMapping("/agenda/stream/{number}")
    public List<AgendaResponse> getResponseStream(@PathVariable int number) {
        return this.useCase.adicionarStream(number);
    }

}
