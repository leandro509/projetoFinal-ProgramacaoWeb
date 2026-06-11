package com.leandro.projeto_petshop_web.controller;

import com.leandro.projeto_petshop_web.database.model.PetEntity;
import com.leandro.projeto_petshop_web.dto.PetDto;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import com.leandro.projeto_petshop_web.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PetEntity> findAllPets() {
        return petService.findAllPets();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PetEntity findById(@PathVariable Long id) throws NotFoundException {
        return petService.findPetById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetEntity createPet(@RequestBody PetDto pet) {
        return petService.createPet(pet);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PetEntity updatePet(@PathVariable Long id,
                                @RequestBody PetDto pet) throws NotFoundException {

        return petService.updatePet(pet, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePet(@PathVariable Long id) throws NotFoundException {
        petService.deletePet(id);
    }

}

