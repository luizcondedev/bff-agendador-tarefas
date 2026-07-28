package com.luizconde.bffagendadortarefas.infrastructure.client;

import com.luizconde.bffagendadortarefas.business.dto.EnderecoDTO;
import com.luizconde.bffagendadortarefas.business.dto.TelefoneDTO;
import com.luizconde.bffagendadortarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuarios", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuarios")
    UsuarioDTO buscaPorEmail(@RequestParam("email") String email,
                             @RequestHeader("Authorization") String token);

    @PostMapping("/usuarios")
    UsuarioDTO criarUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("/usuarios/login")
     String login(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("/usuarios/endereco")
    EnderecoDTO cadastroEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                 @RequestHeader("Authorization") String token);
    @PostMapping("/usuarios/telefone")
    TelefoneDTO cadastroTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                 @RequestHeader("Authorization") String token);

    @DeleteMapping("/usuarios/{email}")
    Void deletaPorEmail(@PathVariable String email,
                        @RequestHeader("Authorization") String token);

    @PutMapping("/usuarios")
    UsuarioDTO atualizaUsuario(@RequestBody UsuarioDTO usuarioDTO,
                               @RequestHeader("Authorization") String token);

    @PutMapping("/usuarios/enderecos/{idEndereco}")
    EnderecoDTO atualizaEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                 @PathVariable Long idEndereco,
                                 @RequestHeader("Authorization") String token);

    @PutMapping("/usuarios/telefones/{idTelefone}")
    TelefoneDTO atualizaTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                 @PathVariable Long idTelefone,
                                 @RequestHeader("Authorization") String token);


}
