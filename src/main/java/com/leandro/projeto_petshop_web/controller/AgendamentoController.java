package com.leandro.projeto_petshop_web.controller;


import com.leandro.projeto_petshop_web.database.model.AgendamentoEntity;
import com.leandro.projeto_petshop_web.dto.AgendamentoDto;
import com.leandro.projeto_petshop_web.services.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


//Sem regras de negócio aqui
//Todas as rotas ficam aqui dentro de controllers
@RequiredArgsConstructor
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<AgendamentoEntity> getAgendamentos(){
        return agendamentoService.buscaTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AgendamentoEntity getAgendamento(@PathVariable Long id){
        return agendamentoService.buscaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoEntity createAgendamento(@RequestBody AgendamentoDto agendamento){
        return agendamentoService.criaAgendamento(agendamento);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoEntity alterarAgendamento(@PathVariable Long id,
                                                @RequestBody AgendamentoDto agendamento) {
        return agendamentoService.atualizaAgendamento(id, agendamento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteAgendamento(@PathVariable Long id){
        agendamentoService.deletaAgendamento(id);
    }



}
