package com.luizconde.bffagendadortarefas.business;

import com.luizconde.bffagendadortarefas.business.dto.EnderecoDTO;
import com.luizconde.bffagendadortarefas.business.dto.TelefoneDTO;
import com.luizconde.bffagendadortarefas.business.dto.UsuarioDTO;
import com.luizconde.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        return client.criarUsuario(usuarioDTO);
    }

    public String login(UsuarioDTO usuarioDTO){
        return client.login(usuarioDTO);
    }

    public UsuarioDTO atualizaUsuario(String token, UsuarioDTO usuarioDTO){
        return client.atualizaUsuario(usuarioDTO, token);
    }

    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO, String token) {
        return client.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTO atualizaTelefone(Long idTelefone, TelefoneDTO telefoneDTO, String token){
        return client.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    public UsuarioDTO buscarPorEmail(String email, String token){
        return client.buscaPorEmail(email, token);
    }

    public EnderecoDTO cadastraEndereco(String token, EnderecoDTO enderecoDTO){
        return client.cadastroEndereco(enderecoDTO, token);
    }

    public TelefoneDTO cadastraTelefone(String token, TelefoneDTO telefoneDTO){
        return client.cadastroTelefone(telefoneDTO, token);
    }

    public void deletaUsuarioPorEmail(String email, String token){
        client.deletaPorEmail(email, token);
    }



}
