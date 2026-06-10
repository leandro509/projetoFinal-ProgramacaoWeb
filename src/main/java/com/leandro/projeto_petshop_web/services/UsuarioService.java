package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.UsuarioEntity;
import com.leandro.projeto_petshop_web.database.repository.UsuarioRepository;
import com.leandro.projeto_petshop_web.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioEntity create(UsuarioDto usuarioDto){
        UsuarioEntity novoUsuario = UsuarioEntity.builder()
                .nome(usuarioDto.getNome())
                .email(usuarioDto.getEmail())
                .senha(usuarioDto.getSenha())
                .numeroTelefone(usuarioDto.getNumeroTelefone())
                .build();
        return usuarioRepository.save(novoUsuario);
    }

    public UsuarioEntity update(UsuarioDto usuarioDto, Long id){
        if(usuarioRepository.existsById(id) == true) {
            UsuarioEntity usuarioEntity = usuarioRepository.getReferenceById(id);
            usuarioEntity.setNome(usuarioDto.getNome());
            usuarioEntity.setEmail(usuarioDto.getEmail());
            usuarioEntity.setSenha(usuarioDto.getSenha());
            usuarioEntity.setNumeroTelefone(usuarioDto.getNumeroTelefone());
            return usuarioRepository.save(usuarioEntity);
        }else {
            // lancar excecao aqui
        }
    }

    public Optional<UsuarioEntity> findById(Long id){
        return usuarioRepository.findById(id);
    }

    public List<UsuarioEntity> findAll(){
        return usuarioRepository.findAll();
    }

    public void delete(Long id){
        if(usuarioRepository.existsById(id) == true) {
            usuarioRepository.deleteById(id);
        }
    }
}
