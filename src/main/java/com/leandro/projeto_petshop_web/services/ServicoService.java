package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.ServicoEntity;
import com.leandro.projeto_petshop_web.database.repository.ServicoRepository;
import com.leandro.projeto_petshop_web.dto.ServicoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServicoService {
    private final ServicoRepository  servicoRepository;

    public List<ServicoEntity> findAll(){
        return servicoRepository.findAll();
    }

    public ServicoEntity findById(Long id){
        return servicoRepository.findById(id).get();
    }

    public void save(ServicoDto servicoDto){
        ServicoEntity servico = ServicoEntity.builder()
                .nomeServico(servicoDto.getNomeServico())
                .preco(servicoDto.getPreco())
                .build();
        servicoRepository.save(servico);
    }

    public void delete(ServicoEntity servico){
        servicoRepository.delete(servico);
    }
}
