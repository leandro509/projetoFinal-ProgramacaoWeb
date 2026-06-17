package com.leandro.projeto_petshop_web.services;

import com.leandro.projeto_petshop_web.database.model.UsuarioEntity;
import com.leandro.projeto_petshop_web.database.repository.UsuarioRepository;
import com.leandro.projeto_petshop_web.dto.UsuarioDto;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioEntity createUsuario(UsuarioDto usuarioDto){
        UsuarioEntity novoUsuario = UsuarioEntity.builder()
                .nome(usuarioDto.getNome())
                .email(usuarioDto.getEmail())
                .senha(usuarioDto.getSenha())
                .numeroTelefone(usuarioDto.getNumeroTelefone())
                .build();
        return usuarioRepository.save(novoUsuario);
    }

    public UsuarioEntity updateUsuario(UsuarioDto usuarioDto, Long id) throws NotFoundException {
        if(usuarioRepository.existsById(id) == true) {
            UsuarioEntity usuarioEntity = usuarioRepository.getReferenceById(id);
            usuarioEntity.setNome(usuarioDto.getNome());
            usuarioEntity.setEmail(usuarioDto.getEmail());
            usuarioEntity.setSenha(usuarioDto.getSenha());
            usuarioEntity.setNumeroTelefone(usuarioDto.getNumeroTelefone());
            return usuarioRepository.save(usuarioEntity);
        }else {
            throw new NotFoundException("Usuário não encontrado");
        }
    }

    public UsuarioEntity findUsuarioById(Long id) throws NotFoundException {
        return usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encotrado"));
    }

    public List<UsuarioEntity> findAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public void deleteUsuario(Long id) throws NotFoundException {
        if(usuarioRepository.existsById(id) == true) {
            usuarioRepository.deleteById(id);
        }else {
            throw new NotFoundException("Usuário não encotrado");
        }
    }
}
