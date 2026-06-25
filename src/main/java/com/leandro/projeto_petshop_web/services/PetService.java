package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.AgendamentoEntity;
import com.leandro.projeto_petshop_web.database.model.PetEntity;
import com.leandro.projeto_petshop_web.database.model.UsuarioEntity;
import com.leandro.projeto_petshop_web.database.repository.AgendamentoRepository;
import com.leandro.projeto_petshop_web.database.repository.PetRepository;
import com.leandro.projeto_petshop_web.database.repository.UsuarioRepository;
import com.leandro.projeto_petshop_web.dto.PetDto;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;



@Service
@RequiredArgsConstructor
public class PetService {

    private final UsuarioRepository usuarioRepository;
    private final PetRepository petRepository;

    public PetDto createPet(PetDto petDto) throws NotFoundException {

        UsuarioEntity usuarioEntity = usuarioRepository.findById(petDto.getUsuarioId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        PetEntity novoPet = PetEntity.builder()
                .usuario(usuarioEntity)
                .nome(petDto.getNome())
                .raca(petDto.getRaca())
                .tipo(petDto.getTipo())
                .sexo(petDto.getSexo())
                .build();

        petRepository.save(novoPet);

        return petDto;
    }

    public PetDto updatePet(PetDto petDto, Long id) throws NotFoundException {
        if (petRepository.existsById(id) == true) {
            PetEntity petEntity = petRepository.getReferenceById(id);
            petEntity.setNome(petDto.getNome());
            petEntity.setRaca(petDto.getRaca());
            petEntity.setTipo(petDto.getTipo());
            petEntity.setSexo(petDto.getSexo());

            if(petDto.getUsuarioId() != null && petDto.getUsuarioId() != petEntity.getUsuario().getUsuarioId()){
                Optional<UsuarioEntity> usuarioPet = usuarioRepository.findById(petDto.getUsuarioId());
                petEntity.setUsuario(usuarioPet.get());
            }

            petRepository.save(petEntity);
        } else {
            throw new NotFoundException("Pet não encotrado");
        }
        return petDto;
    }

    public PetDto findPetById(Long id) throws NotFoundException {
        PetEntity pet = petRepository.findById(id).orElseThrow(() -> new NotFoundException("Pet não encontrado"));
        List<Long> agendamentosPet = new ArrayList<>();
        PetDto petAchado = new PetDto();
        petAchado.setTipo(pet.getTipo());
        petAchado.setSexo(pet.getSexo());
        petAchado.setRaca(pet.getRaca());
        petAchado.setNome(pet.getNome());

        if (pet.getUsuario() != null) {
            petAchado.setUsuarioId(pet.getUsuario().getUsuarioId());
        }

        if (pet.getAgendamentos() != null) {
            for (AgendamentoEntity agendamento : pet.getAgendamentos()) {
                agendamentosPet.add(agendamento.getId());
            }
        }

        return petAchado;
    }

    public List<PetDto> findAllPets() throws NotFoundException {

        if(petRepository.findAll().isEmpty()) {
            throw new NotFoundException("Nenhum pet encontrado");
        }

        List<PetDto> petDtos = new ArrayList<>();
        for (PetEntity petEntity : petRepository.findAll()) {
            PetDto petAchado = new PetDto();
            petAchado.setTipo(petEntity.getTipo());
            petAchado.setSexo(petEntity.getSexo());
            petAchado.setRaca(petEntity.getRaca());
            petAchado.setNome(petEntity.getNome());

            if (petEntity.getUsuario() != null) {
                petAchado.setUsuarioId(petEntity.getUsuario().getUsuarioId());
            }

            if (petEntity.getAgendamentos() != null) {
                List<Long> agendamentosPet = new ArrayList<>();
                for (AgendamentoEntity agendamento : petEntity.getAgendamentos()) {
                    agendamentosPet.add(agendamento.getId());
                }
                petAchado.setAgendamentosIds(agendamentosPet);
            }
            petDtos.add(petAchado);
        }

        return petDtos;
    }

    public void deletePet(Long id) throws NotFoundException {
        if (petRepository.existsById(id) == true) {
            petRepository.deleteById(id);
        } else {
            throw new NotFoundException("Pet não encontrado");
        }
    }


}
