package com.joao.bffagendador.controller;

import com.joao.bffagendador.business.TarefaService;
import com.joao.bffagendador.business.dto.in.TarefasDTORequest;
import com.joao.bffagendador.business.dto.out.TarefasDTOResponse;
import com.joao.bffagendador.business.enums.StatusNoficacaoEnum;
import com.joao.bffagendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro de tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)

public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Gravar tarefas", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa gravada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefa(@RequestBody TarefasDTORequest dto,
                                                           @RequestHeader(name = "Authorization", required = false) String token ) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por Periodos", description = "Busca por tarefas cadastradas por periodos")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token ) {
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por e-mail de usuários", description = "Buscar tarefas atravez de e-mail")
    @ApiResponse(responseCode = "200", description = "Tarefas encontrada")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token ) {
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deletar tarefa por ID", description = "Deletar tarefas por ID de usuários")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
    @ApiResponse(responseCode = "500", description = "Erro nos servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token ) {
        tarefaService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Alterar os status da tarefa", description = "Alteração nos Status da tarefa")
    @ApiResponse(responseCode = "200", description = "Status atualizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TarefasDTOResponse> alteraraStatsNotificacao(@RequestParam("status") StatusNoficacaoEnum status,
                                                                       @RequestParam("id") String id,
                                                                       @RequestHeader(name = "Authorization", required = false) String token ) {
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Alterar a tarefa", description = "Alteração nas informações da tarefa")
    @ApiResponse(responseCode = "200", description = "Status atualizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token ){
        return ResponseEntity.ok(tarefaService.updateTarefas(dto, id, token));
    }

}
