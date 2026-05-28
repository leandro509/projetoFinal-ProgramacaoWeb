package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.AgendamentoEntity;
import com.leandro.projeto_petshop_web.database.model.PetEntity;
import com.leandro.projeto_petshop_web.database.model.UsuarioEntity;
import com.leandro.projeto_petshop_web.dto.AgendamentoDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//Teste com dados mockados por enquanto
//banco de dados chamado aqui
//regras de negocio

@Service
public class AgendamentoService {

    private ArrayList<AgendamentoEntity> agendamentos = new ArrayList<>();

    public AgendamentoEntity criaAgendamento(AgendamentoDto agendamento) {

        Long identificador = agendamentos.stream()
                .mapToLong(AgendamentoEntity::getId)
                .max()
                .orElse(0) + 1;

        AgendamentoEntity novoAgendamento = AgendamentoEntity.builder()
                .id(identificador)
                .pet(agendamento.getPet())
                .data(agendamento.getData())
                .usuarioEntity(agendamento.getUsuarioEntity())
                .build();

        agendamentos.add(novoAgendamento);
        return novoAgendamento;
    }

    public void deletaAgendamento(Long id) {
        agendamentos.removeIf(agendamento -> agendamento.getId().equals(id));
    }

    public AgendamentoEntity atualizaAgendamento(Long id, AgendamentoDto agendamento) {
        AgendamentoEntity agendamentosEnti = agendamentos.stream()
                .filter(p -> p.getId().equals(id))
                .findAny()
                .orElse(null);

        agendamentosEnti.setData(agendamento.getData());
        agendamentosEnti.setPet(agendamento.getPet());
        agendamentosEnti.setUsuarioEntity(agendamento.getUsuarioEntity());

        return agendamentosEnti;
    }

    public AgendamentoEntity buscaPorId(Long id) {
        AgendamentoEntity agendamentosEnti = agendamentos.stream()
                .filter(p -> p.getId().equals(id))
                .findAny()
                .orElse(null);

        return agendamentosEnti;
    }

    public ArrayList<AgendamentoEntity> buscaTodos() {
        return  agendamentos;
    }
}
