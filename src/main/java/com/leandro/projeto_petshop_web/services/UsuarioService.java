package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.PetEntity;
import com.leandro.projeto_petshop_web.database.model.UsuarioEntity;
import com.leandro.projeto_petshop_web.database.repository.ServicoRepository;
import com.leandro.projeto_petshop_web.database.repository.UsuarioRepository;
import com.leandro.projeto_petshop_web.dto.UsuarioDto;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ServicoRepository servicoRepository;

    public UsuarioDto createUsuario(UsuarioDto usuarioDto) {
        UsuarioEntity novoUsuario = UsuarioEntity.builder()
                .nome(usuarioDto.getNome())
                .email(usuarioDto.getEmail())
                .senha(usuarioDto.getSenha())
                .numeroTelefone(usuarioDto.getNumeroTelefone())
                .build();
        usuarioRepository.save(novoUsuario);
        return usuarioDto;
    }

    public UsuarioDto updateUsuario(UsuarioDto usuarioDto, Long id) throws NotFoundException {
        if (usuarioRepository.existsById(id) == true) {
            UsuarioEntity usuarioEntity = usuarioRepository.getReferenceById(id);
            usuarioEntity.setNome(usuarioDto.getNome());
            usuarioEntity.setEmail(usuarioDto.getEmail());
            usuarioEntity.setSenha(usuarioDto.getSenha());
            usuarioEntity.setNumeroTelefone(usuarioDto.getNumeroTelefone());
            usuarioRepository.save(usuarioEntity);
            return usuarioDto;
        } else {
            throw new NotFoundException("Usuário não encontrado");
        }
    }

    public UsuarioDto findUsuarioById(Long id) throws NotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome(usuarioEntity.getNome());
        usuarioDto.setEmail(usuarioEntity.getEmail());
        usuarioDto.setSenha(usuarioEntity.getSenha());
        usuarioDto.setNumeroTelefone(usuarioEntity.getNumeroTelefone());
        return usuarioDto;
    }

    public List<UsuarioDto> findAllUsuarios() throws NotFoundException {

        if(usuarioRepository.findAll().isEmpty()) {
            throw new NotFoundException("Nenhum usuário encontrado");
        }

        List<UsuarioDto> usuarioDtos = new ArrayList<>();
        for (UsuarioEntity usuarioEntity : usuarioRepository.findAll()) {
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setNome(usuarioEntity.getNome());
            usuarioDto.setEmail(usuarioEntity.getEmail());
            usuarioDto.setSenha(usuarioEntity.getSenha());
            usuarioDto.setNumeroTelefone(usuarioEntity.getNumeroTelefone());

            if (usuarioEntity.getPets() != null) {
                List<Long> petIds = new ArrayList<>();
                for (PetEntity petsUsuario : usuarioEntity.getPets()) {
                    petIds.add(petsUsuario.getPetId());
                }
                usuarioDto.setPetIds(petIds);
            }
            usuarioDtos.add(usuarioDto);
        }
        return usuarioDtos;
    }

    public void deleteUsuario(Long id) throws NotFoundException {
        if (usuarioRepository.existsById(id) == true) {
            usuarioRepository.deleteById(id);
        } else {
            throw new NotFoundException("Usuário não encotrado");
        }
    }
}
