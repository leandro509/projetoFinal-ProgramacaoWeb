
package com.leandro.projeto_petshop_web.controller;


import com.leandro.projeto_petshop_web.database.model.AgendamentoEntity;
import com.leandro.projeto_petshop_web.dto.AgendamentoDto;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import com.leandro.projeto_petshop_web.services.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;


//Sem regras de negócio aqui
//Todas as rotas ficam aqui dentro de controllers
@RequiredArgsConstructor
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AgendamentoEntity> findAllAgendamentos(){
        return agendamentoService.findAllAgendamentos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AgendamentoEntity findAgendamentoById(@PathVariable Long id) throws NotFoundException {
        return agendamentoService.findAgendamentoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoEntity createAgendamento(@RequestBody AgendamentoDto agendamento) throws NotFoundException {
        return agendamentoService.createAgendamento(agendamento);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoEntity updateAgendamento(@PathVariable Long id,
                                                @RequestBody AgendamentoDto agendamento) throws NotFoundException {
        return agendamentoService.updateAgendamento(agendamento, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteAgendamento(@PathVariable Long id) throws NotFoundException {
        agendamentoService.deleteAgendamento(id);
    }

    @PutMapping("/{agendamentoId}/servicos")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AgendamentoEntity adicionaServicos(@PathVariable Long agendamentoId, @RequestBody List<Long> servicosIds) throws NotFoundException {
        return agendamentoService.addServicos(agendamentoId, servicosIds);
    }

    @PutMapping("/{agendamentoId}/servicos/{servicoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AgendamentoEntity adicionaServico(@PathVariable Long agendamentoId, @PathVariable Long servicoId) throws NotFoundException {
        return agendamentoService.addServico(agendamentoId, servicoId);
    }

}
