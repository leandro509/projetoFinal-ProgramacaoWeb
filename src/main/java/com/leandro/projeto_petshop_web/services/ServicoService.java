package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.AgendamentoEntity;
import com.leandro.projeto_petshop_web.database.model.ServicoEntity;
import com.leandro.projeto_petshop_web.database.repository.AgendamentoRepository;
import com.leandro.projeto_petshop_web.database.repository.ServicoRepository;
import com.leandro.projeto_petshop_web.dto.ServicoDto;
import com.leandro.projeto_petshop_web.exception.BadRequestException;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.Hibernate.map;

@RequiredArgsConstructor
@Service
public class ServicoService {
    private final ServicoRepository servicoRepository;
    private final AgendamentoRepository agendamentoRepository;

    public ServicoDto createServico(ServicoDto servicoDto) {
        ServicoEntity novoServico = ServicoEntity.builder()
                .nomeServico(servicoDto.getNomeServico())
                .preco(servicoDto.getPreco())
                .build();
        servicoRepository.save(novoServico);

        return servicoDto;
    }

    public ServicoDto updateServico(ServicoDto servicoDto, Long id) throws NotFoundException {
        if (servicoRepository.existsById(id) == true) {
            ServicoEntity servicoEntity = servicoRepository.getReferenceById(id);
            servicoEntity.setNomeServico(servicoDto.getNomeServico());
            servicoEntity.setPreco(servicoDto.getPreco());
            servicoRepository.save(servicoEntity);
        } else {
            throw new NotFoundException("Serviço não encotrado");
        }
        return servicoDto;
    }

    public List<ServicoDto> findAllServicos() throws NotFoundException {

        if (servicoRepository.findAll().isEmpty()) {
            throw new NotFoundException("Nenhum serviço foi encontrado");
        }

        List<ServicoDto> servicosDto = new ArrayList<>();
        for (ServicoEntity servicoEntity : servicoRepository.findAll()) {
            ServicoDto servico = new ServicoDto();
            servico.setNomeServico(servicoEntity.getNomeServico());
            servico.setPreco(servicoEntity.getPreco());

            List<Long> agendamentoIds = new ArrayList<>();
            for (AgendamentoEntity agendamentoEntity : servicoEntity.getAgendamentos()) {
                agendamentoIds.add(agendamentoEntity.getId());
            }
            servico.setAgendamentosIds(agendamentoIds);
            servicosDto.add(servico);
        }

        return servicosDto;
    }

    public ServicoDto findServicoById(Long id) throws NotFoundException {
        ServicoEntity servico = servicoRepository.findById(id).orElseThrow(() -> new NotFoundException("Serviço não encotrado"));
        ServicoDto servicoDto = new ServicoDto();
        servicoDto.setNomeServico(servico.getNomeServico());
        servicoDto.setPreco(servico.getPreco());

        List<Long> agendamentoIds = new ArrayList<>();
        for (AgendamentoEntity agendamentoEntity : servico.getAgendamentos()) {
            agendamentoIds.add(agendamentoEntity.getId());
        }

        servicoDto.setAgendamentosIds(agendamentoIds);
        return servicoDto;
    }

    public void deleteServico(Long id) throws NotFoundException {
        if (servicoRepository.existsById(id) == true) {
            servicoRepository.deleteById(id);
        } else {
            throw new NotFoundException("Serviço não encontrado");
        }
    }
}
