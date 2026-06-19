package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.AgendamentoEntity;
import com.leandro.projeto_petshop_web.database.model.PetEntity;
import com.leandro.projeto_petshop_web.database.model.ServicoEntity;
import com.leandro.projeto_petshop_web.database.repository.AgendamentoRepository;
import com.leandro.projeto_petshop_web.database.repository.PetRepository;
import com.leandro.projeto_petshop_web.database.repository.ServicoRepository;
import com.leandro.projeto_petshop_web.dto.AgendamentoDto;
import com.leandro.projeto_petshop_web.exception.BadRequestException;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
    private final PetRepository petRepository;
    private final ServicoRepository servicoRepository;

    public AgendamentoDto createAgendamento(AgendamentoDto agendamentoDto) throws NotFoundException {
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
        agendamentoRepository.save(agendamentoEntity);
    }

    public AgendamentoDto updateAgendamento(AgendamentoDto agendamentoDto, Long id) throws NotFoundException, BadRequestException {

        AgendamentoEntity agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Agendamento não foi encotrado"));

        PetEntity petEntity = petRepository.findById(agendamentoDto.getPetId())
                .orElseThrow(() -> new NotFoundException("Pet não encontrado"));

        List<AgendamentoEntity> listaAgendamentos = agendamentoRepository.findByDataAndPetPetId(agendamentoDto.getData(), agendamentoDto.getPetId());

        for (AgendamentoEntity agendamentos : listaAgendamentos) {
            if (agendamentos.getData().equals(agendamentoDto.getData())) {
                throw new BadRequestException("Já existe agendamento com essa data para o Pet");
            }
        }

        Set<ServicoEntity> servicos = new HashSet<>();

        for (Long servicoId : agendamentoDto.getServicoIds()) {
            ServicoEntity servico = servicoRepository.findById(servicoId)
                    .orElseThrow(() -> new NotFoundException("Serviço não encontrado " + servicoId));

            servicos.add(servico);
        }

        agendamento.setPet(petEntity);
        agendamento.setData(agendamentoDto.getData());
        agendamento.setServicos(servicos);

        return agendamentoRepository.save(agendamento);

    }

    public void addServicos(Long agendamentoId, List<Long> servicosIds) throws NotFoundException {
        AgendamentoEntity agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));

        Set<ServicoEntity> servicos = new HashSet<>();

        for (Long id : servicosIds) {
            ServicoEntity servico = servicoRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Serviço não encontrado: " + id));

            servicos.add(servico);
        }

        agendamento.getServicos().addAll(servicos);

        agendamentoRepository.save(agendamento);
    }

    public AgendamentoDto addServico(Long agendamentoId, Long servicoId) throws NotFoundException {
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        AgendamentoEntity agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));

        ServicoEntity servico = servicoRepository.findById(servicoId)
                .orElseThrow(() -> new NotFoundException("Serviço não encontrado"));

        Set<ServicoEntity> servicos = new HashSet<>();
        servicos.add(servico);

        agendamento.getServicos().addAll(servicos);

        agendamentoRepository.save(agendamento);
        agendamentoDto.setData(agendamentoDto.getData());
        agendamentoDto.setPetId(agendamentoDto.getPetId());
        List<Long> servicosIds = new ArrayList<>();
        for (ServicoEntity idServicos : servicos) {
            servicosIds.add(idServicos.getServicoId());
        }
        agendamentoDto.setServicoIds(servicosIds);
        return agendamentoDto;
    }

    public AgendamentoDto findAgendamentoById(Long id) throws NotFoundException {
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        AgendamentoEntity agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));
        agendamentoDto.setData(agendamento.getData());
        agendamentoDto.setPetId(agendamento.getId());
        List<Long> servicosIds = new ArrayList<>();
        agendamento.getServicos().forEach(servico -> {
            servicosIds.add(servico.getServicoId());
        });
        agendamentoDto.setServicoIds(servicosIds);
        return agendamentoDto;
    }

    public List<AgendamentoDto> findAllAgendamentos() {
            List<AgendamentoDto> agendamentoDtos = new ArrayList<>();
        for(AgendamentoEntity agendamentoEntity : agendamentoRepository.findAll()){
            AgendamentoDto novoAgendamento = new  AgendamentoDto();
            novoAgendamento.setData(agendamentoEntity.getData());
            novoAgendamento.setPetId(agendamentoEntity.getPet().getPetId());
            List<Long> servicosIds = new ArrayList<>();
            agendamentoEntity.getServicos().forEach(servico -> {
               servicosIds.add(servico.getServicoId());
            });
            novoAgendamento.setServicoIds(servicosIds);
            agendamentoDtos.add(novoAgendamento);
        }
        return agendamentoDtos;
    }

    public void deleteAgendamento(Long id) throws NotFoundException {
        if (agendamentoRepository.existsById(id) == true) {
            agendamentoRepository.deleteById(id);
        } else {
            throw new NotFoundException("Agendamento não encontrado");
        }
    }
}
