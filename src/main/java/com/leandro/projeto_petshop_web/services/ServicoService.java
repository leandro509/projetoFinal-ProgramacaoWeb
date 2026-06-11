package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.ServicoEntity;
import com.leandro.projeto_petshop_web.database.repository.ServicoRepository;
import com.leandro.projeto_petshop_web.dto.ServicoDto;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServicoService {
    private final ServicoRepository  servicoRepository;

    public ServicoEntity createServico(ServicoDto servicoDto) {
        ServicoEntity novoServico = ServicoEntity.builder()
                .nomeServico(servicoDto.getNomeServico())
                .preco(servicoDto.getPreco())
                .build();
        return servicoRepository.save(novoServico);
    }

    public ServicoEntity updateServico(ServicoDto servicoDto, Long id) throws NotFoundException {
        if(servicoRepository.existsById(id) == true) {
            ServicoEntity servicoEntity = servicoRepository.getReferenceById(id);
            servicoEntity.setNomeServico(servicoDto.getNomeServico());
            servicoEntity.setPreco(servicoDto.getPreco());
            return servicoRepository.save(servicoEntity);
        }else {
            throw new NotFoundException("Serviço não encotrado");
        }
    }

    public List<ServicoEntity> findAllServicos(){
        return servicoRepository.findAll();
    }

    public ServicoEntity findServicoById(Long id) throws NotFoundException {
        return servicoRepository.findById(id).orElseThrow(() -> new NotFoundException("Serviço não encotrado"));
    }

    public void deleteServico(Long id) throws NotFoundException {
        if(servicoRepository.existsById(id) == true) {
            servicoRepository.deleteById(id);
        }else {
            throw new NotFoundException("Serviço não encontrado");
        }
    }
}
