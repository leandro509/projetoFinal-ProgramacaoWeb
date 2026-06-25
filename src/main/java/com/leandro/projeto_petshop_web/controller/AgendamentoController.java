
package com.leandro.projeto_petshop_web.controller;


import com.leandro.projeto_petshop_web.dto.AgendamentoDto;
import com.leandro.projeto_petshop_web.exception.BadRequestException;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import com.leandro.projeto_petshop_web.services.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;


//Sem regras de negócio aqui
//Todas as rotas ficam aqui dentro de controllers
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AgendamentoDto> findAllAgendamentos(){
        return agendamentoService.findAllAgendamentos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AgendamentoDto findAgendamentoById(@PathVariable Long id) throws NotFoundException {
        return agendamentoService.findAgendamentoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoDto createAgendamento(@Valid @RequestBody AgendamentoDto agendamento) throws NotFoundException, BadRequestException {
        return agendamentoService.createAgendamento(agendamento);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateAgendamento(@Valid @PathVariable Long id,
                                                @RequestBody AgendamentoDto agendamento) throws NotFoundException, BadRequestException {
        agendamentoService.updateAgendamento(agendamento, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAgendamento(@PathVariable Long id) throws NotFoundException {
        agendamentoService.deleteAgendamento(id);
    }

    @PutMapping("/{agendamentoId}/servicos")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionaServicos(@PathVariable Long agendamentoId, @RequestBody List<Long> servicosIds) throws NotFoundException {
        agendamentoService.addServicos(agendamentoId, servicosIds);
    }

    @PutMapping("/{agendamentoId}/servicos/{servicoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionaServico(@PathVariable Long agendamentoId, @PathVariable Long servicoId) throws NotFoundException {
         agendamentoService.addServico(agendamentoId, servicoId);
    }

}
