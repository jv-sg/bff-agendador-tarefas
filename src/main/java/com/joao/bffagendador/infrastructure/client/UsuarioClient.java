package com.joao.bffagendador.infrastructure.client;

import com.joao.bffagendador.business.dto.in.EnderecoDTORequest;
import com.joao.bffagendador.business.dto.in.LoginDTORequest;
import com.joao.bffagendador.business.dto.in.TelefoneDTORequest;
import com.joao.bffagendador.business.dto.in.UsuarioDTORequest;
import com.joao.bffagendador.business.dto.out.EnderecoDTOResponse;
import com.joao.bffagendador.business.dto.out.TelefoneDTOResponse;
import com.joao.bffagendador.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest loginDTORequest);

    //Metodo delete
    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                          @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                        @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                        @RequestHeader("Authorization") String token);

}
