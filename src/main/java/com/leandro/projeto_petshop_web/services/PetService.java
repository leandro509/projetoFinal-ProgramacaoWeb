package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
}
