package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.AgendamentoEntity;
import com.leandro.projeto_petshop_web.database.model.PetEntity;
import com.leandro.projeto_petshop_web.database.model.ServicoEntity;
import com.leandro.projeto_petshop_web.database.repository.AgendamentoRepository;
import com.leandro.projeto_petshop_web.database.repository.PetRepository;
import com.leandro.projeto_petshop_web.database.repository.ServicoRepository;
import com.leandro.projeto_petshop_web.dto.AgendamentoDto;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
    private final PetRepository petRepository;
    private final ServicoRepository servicoRepository;

    public AgendamentoEntity createAgendamento(AgendamentoDto agendamentoDto) throws NotFoundException {
        Set<ServicoEntity> servicos = new HashSet<>();

        PetEntity petEntity = petRepository.findById(agendamentoDto.getPetId())
                .orElseThrow(() -> new NotFoundException("Pet não encontrado"));

        List<AgendamentoEntity> agendamento = agendamentoRepository.findByDataAndPetPetId(agendamentoDto.getData(), agendamentoDto.getPetId());

        for (AgendamentoEntity agendamentos : agendamento) {
            if (agendamentos.getData().equals(agendamentoDto.getData())) {
                throw new NotFoundException("Já existe agendamento com essa data para o Pet");
            }
        }

        for (Long servicoId : agendamentoDto.getServicoIds()) {
            ServicoEntity servico = servicoRepository.findById(servicoId)
                    .orElseThrow(() -> new NotFoundException("Serviço não encontrado " + servicoId));

            servicos.add(servico);
        }

        AgendamentoEntity agendamentoEntity = AgendamentoEntity.builder()
                .pet(petEntity)
                .servicos(servicos)
                .data((agendamentoDto.getData()))
                .build();
        return agendamentoRepository.save(agendamentoEntity);
    }

    public AgendamentoEntity updateAgendamento(AgendamentoDto agendamentoDto, Long id) throws NotFoundException {
        if (agendamentoRepository.existsById(id) == true) {
            Set<ServicoEntity> servicos = new HashSet<>();

            PetEntity petEntity = petRepository.findById(agendamentoDto.getPetId())
                    .orElseThrow(() -> new NotFoundException("Pet não encontrado"));

            List<AgendamentoEntity> agendamento = agendamentoRepository.findByDataAndPetPetId(agendamentoDto.getData(), agendamentoDto.getPetId());

            for (AgendamentoEntity agendamentos : agendamento) {
                if (agendamentos.getData().equals(agendamentoDto.getData())) {
                    throw new NotFoundException("Já existe agendamento com essa data para o Pet");
                }
            }

            for (Long servicoId : agendamentoDto.getServicoIds()) {
                ServicoEntity servico = servicoRepository.findById(servicoId)
                        .orElseThrow(() -> new NotFoundException("Serviço não encontrado " + servicoId));

                servicos.add(servico);
            }

            AgendamentoEntity agendamentoEntity = AgendamentoEntity.builder()
                    .pet(petEntity)
                    .servicos(servicos)
                    .data((agendamentoDto.getData()))
                    .build();
            return agendamentoRepository.save(agendamentoEntity);
        } else {
            throw new NotFoundException("Agendamento não encontrado");
        }
    }

    public AgendamentoEntity addServicos(Long agendamentoId, List<Long> servicosIds) throws NotFoundException {
        AgendamentoEntity agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));

        Set<ServicoEntity> servicos = new HashSet<>();

        for (Long id : servicosIds) {
            ServicoEntity servico = servicoRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Serviço não encontrado: " + id));

            servicos.add(servico);
        }

        agendamento.setServicos(servicos);

        return agendamentoRepository.save(agendamento);
    }

    public AgendamentoEntity addServico(Long agendamentoId, Long servicoId) throws NotFoundException {
        AgendamentoEntity agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));

        ServicoEntity servico = servicoRepository.findById(servicoId)
                .orElseThrow(() -> new NotFoundException("Serviço não encontrado"));

        Set<ServicoEntity> servicos = new HashSet<>();
        servicos.add(servico);

        agendamento.setServicos(servicos);

        return agendamentoRepository.save(agendamento);
    }

    public AgendamentoEntity findAgendamentoById(Long id) throws NotFoundException {
        return agendamentoRepository.findById(id).orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));
    }

    public List<AgendamentoEntity> findAllAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public void deleteAgendamento(Long id) throws NotFoundException {
        if (agendamentoRepository.existsById(id) == true) {
            agendamentoRepository.deleteById(id);
        } else {
            throw new NotFoundException("Agendamento não encontrado");
        }
    }
}
