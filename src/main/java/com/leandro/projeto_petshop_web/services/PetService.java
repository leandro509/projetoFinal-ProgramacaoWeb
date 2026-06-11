package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.PetEntity;
import com.leandro.projeto_petshop_web.database.repository.PetRepository;
import com.leandro.projeto_petshop_web.dto.PetDto;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public PetEntity createPet(PetDto petDto) {
        PetEntity novoPet = PetEntity.builder()
                .nome(petDto.getNome())
                .raca(petDto.getRaca())
                .tipo(petDto.getTipo())
                .sexo(petDto.getSexo())
                .build();
        return petRepository.save(novoPet);
    }

    public PetEntity updatePet(PetDto petDto, Long id) throws NotFoundException {
        if(petRepository.existsById(id) == true) {
            PetEntity petEntity = petRepository.getReferenceById(id);
            petEntity.setNome(petDto.getNome());
            petEntity.setRaca(petDto.getRaca());
            petEntity.setTipo(petDto.getTipo());
            petEntity.setSexo(petDto.getSexo());
            return petRepository.save(petEntity);
        }else {
            throw new NotFoundException("Pet não encotrado");
        }
    }

    public PetEntity findPetById(Long id) throws NotFoundException {
        return petRepository.findById(id).orElseThrow(()-> new NotFoundException("Pet não encontrado"));
    }

    public List<PetEntity> findAllPets() {
        return petRepository.findAll();
    }

    public void deletePet(Long id) throws NotFoundException {
        if(petRepository.existsById(id) == true) {
            petRepository.deleteById(id);
        }else {
            throw new NotFoundException("Pet não encontrado");
        }
    }


}
