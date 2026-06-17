package com.leandro.projeto_petshop_web.controller;

import com.leandro.projeto_petshop_web.database.model.PetEntity;
import com.leandro.projeto_petshop_web.database.model.ServicoEntity;
import com.leandro.projeto_petshop_web.dto.PetDto;
import com.leandro.projeto_petshop_web.dto.ServicoDto;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import com.leandro.projeto_petshop_web.services.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servicos")
public class ServicoController {
    private final ServicoService servicoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ServicoEntity> findAllServicos() {
        return servicoService.findAllServicos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServicoEntity findById(@PathVariable Long id) throws NotFoundException {
        return servicoService.findServicoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoEntity createServico(@RequestBody ServicoDto servico) {
        return servicoService.createServico(servico);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ServicoEntity updateServico(@PathVariable Long id,
                               @RequestBody ServicoDto servico) throws NotFoundException {

        return servicoService.updateServico(servico, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServico(@PathVariable Long id) throws NotFoundException {
        servicoService.deleteServico(id);
    }

}
