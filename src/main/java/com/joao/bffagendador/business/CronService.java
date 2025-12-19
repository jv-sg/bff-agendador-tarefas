package com.joao.bffagendador.business;

import com.joao.bffagendador.business.dto.in.LoginDTORequest;
import com.joao.bffagendador.business.dto.out.TarefasDTOResponse;
import com.joao.bffagendador.business.enums.StatusNoficacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;


    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        String token = login(converterParaRequestDTO());
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFurutaMaisCinto = LocalDateTime.now().plusHours(1).plusMinutes(5);
        List<TarefasDTOResponse> listaTarefas = tarefaService.buscaTarefasAgendadasPorPeriodo(horaFutura, horaFurutaMaisCinto, token);

        listaTarefas.forEach(tarefa -> { emailService.enviaEmail(tarefa);
            tarefaService.alteraStatus(StatusNoficacaoEnum.NOTIFICADO, tarefa.getId(),
                    token);});

    }

    public String login(LoginDTORequest dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginDTORequest converterParaRequestDTO(){
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}
