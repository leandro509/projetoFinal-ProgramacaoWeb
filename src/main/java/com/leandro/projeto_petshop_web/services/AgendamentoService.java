package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.AgendamentoEntity;
import com.leandro.projeto_petshop_web.database.repository.AgendamentoRepository;
import com.leandro.projeto_petshop_web.dto.AgendamentoDto;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoEntity createAgendamento(AgendamentoDto agendamentoDto) {
        AgendamentoEntity agendamentoEntity = AgendamentoEntity.builder()
                .data((agendamentoDto.getData()))
                .build();
        return agendamentoRepository.save(agendamentoEntity);
    }

    public AgendamentoEntity updateAgendamento(AgendamentoDto agendamentoDto, Long id) throws NotFoundException {
        if(agendamentoRepository.existsById(id) == true) {
            AgendamentoEntity agendamentoEntity = AgendamentoEntity.builder()
                    .data((agendamentoDto.getData()))
                    .build();
            return  agendamentoRepository.save(agendamentoEntity);
        }else {
            throw new NotFoundException("Agendamento não encontrado");
        }
    }

    public AgendamentoEntity findAgendamentoById(Long id) throws NotFoundException {
        return  agendamentoRepository.findById(id).orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));
    }

    public List<AgendamentoEntity> findAllAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public void deleteAgendamento(Long id) throws NotFoundException {
        if(agendamentoRepository.existsById(id) == true) {
            agendamentoRepository.deleteById(id);
        }else {
            throw new NotFoundException("Agendamento não encontrado");
        }
    }
}
