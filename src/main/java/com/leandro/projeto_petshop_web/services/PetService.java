package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.PetEntity;
import com.leandro.projeto_petshop_web.dto.PetDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

//banco de dados chamado aqui
//regras de negocio

@Service
public class PetService {

    private ArrayList<PetEntity> pets = new ArrayList<>();

    public PetEntity criaPet(PetDto pet) {

        Long identificador = pets.stream()
                .mapToLong(PetEntity::getPet_id)
                .max()
                .orElse(0) + 1;

        PetEntity novoPet = PetEntity.builder()
                .pet_id(identificador)
                .nome(pet.getNome())
                .raca(pet.getRaca())
                .tipo(pet.getTipo())
                .sexo(pet.getSexo())
                .build();

        pets.add(novoPet);

        return novoPet;
    }

    public void deletaPet(Long id) {
        pets.removeIf(pet -> pet.getPet_id().equals(id));
    }

    public PetEntity atualizaPet(Long id, PetDto pet) {

        PetEntity petEnti = pets.stream()
                .filter(p -> p.getPet_id().equals(id))
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        petEnti.setNome(pet.getNome());
        petEnti.setRaca(pet.getRaca());
        petEnti.setTipo(pet.getTipo());
        petEnti.setSexo(pet.getSexo());

        return petEnti;
    }

    public PetEntity buscaPorId(Long id) {

        PetEntity petEnti = pets.stream()
                .filter(p -> p.getPet_id().equals(id))
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Pet não encontrado!"
                ));

        return petEnti;
    }

    public ArrayList<PetEntity> buscaTodos() {
        return pets;
    }

}
