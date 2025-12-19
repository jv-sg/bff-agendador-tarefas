package com.joao.bffagendador.business;


import com.joao.bffagendador.business.dto.in.TarefasDTORequest;
import com.joao.bffagendador.business.dto.out.TarefasDTOResponse;
import com.joao.bffagendador.business.enums.StatusNoficacaoEnum;
import com.joao.bffagendador.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasClient client;


    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {
        return client.gravarTarefa(dto, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {
        return client.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {

        return client.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        client.deletaTarefaPorId(id, token);
    }

    public TarefasDTOResponse alteraStatus(StatusNoficacaoEnum status, String id, String token) {
        return client.alteraraStatsNotificacao(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token) {
        return client.updateTarefas(dto, id, token);
    }
}