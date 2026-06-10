package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
}
