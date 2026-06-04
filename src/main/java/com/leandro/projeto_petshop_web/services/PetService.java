package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.PetEntity;
import com.leandro.projeto_petshop_web.database.repository.PetRepository;
import com.leandro.projeto_petshop_web.dto.PetDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

//banco de dados chamado aqui
//regras de negocio

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public PetEntity criaPet(PetDto pet) {
        PetEntity novoPet = PetEntity.builder()
                .nome(pet.getNome())
                .raca(pet.getRaca())
                .tipo(pet.getTipo())
                .sexo(pet.getSexo())
                .build();

        return petRepository.save(novoPet);
    }

    public void deletaPet(Long id) {
        if (!petRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado!");
        }

        petRepository.deleteById(id);
    }

    public PetEntity atualizaPet(Long id, PetDto pet) {
        PetEntity petEntity = petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Pet não encontrado!"
                ));

        petEntity.setNome(pet.getNome());
        petEntity.setRaca(pet.getRaca());
        petEntity.setTipo(pet.getTipo());
        petEntity.setSexo(pet.getSexo());

        return petRepository.save(petEntity);
    }

    public PetEntity buscaPorId(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Pet não encontrado!"
                ));
    }

    public List<PetEntity> buscaTodos() {
        return petRepository.findAll();
    }
}
