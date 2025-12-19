package com.joao.bffagendador.business;


import com.joao.bffagendador.business.dto.in.EnderecoDTORequest;
import com.joao.bffagendador.business.dto.in.LoginDTORequest;
import com.joao.bffagendador.business.dto.in.TelefoneDTORequest;
import com.joao.bffagendador.business.dto.in.UsuarioDTORequest;
import com.joao.bffagendador.business.dto.out.EnderecoDTOResponse;
import com.joao.bffagendador.business.dto.out.TelefoneDTOResponse;
import com.joao.bffagendador.business.dto.out.UsuarioDTOResponse;
import com.joao.bffagendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {
    private final UsuarioClient client;


    public UsuarioDTOResponse salvarUsusario(UsuarioDTORequest usuarioDTO){
        return client.salvarUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest usuarioDTO){
        return client.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return client.buscarUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token){
        client.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto){
        return client.atualizaDadoUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){
        return client.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest dto, String token){
        return client.atualizaTelefone(dto, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto){
        return client.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto){
        return client.cadastraTelefone(dto, token);
    }
}