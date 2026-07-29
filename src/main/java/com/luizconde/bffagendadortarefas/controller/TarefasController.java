package com.luizconde.bffagendadortarefas.controller;


import com.luizconde.bffagendadortarefas.business.TarefasService;
import com.luizconde.bffagendadortarefas.business.dto.TarefasRequestDTO;
import com.luizconde.bffagendadortarefas.business.dto.TarefasResponseDTO;
import com.luizconde.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.luizconde.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Agendamento e consulta de tarefas")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar tarefas", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "201", description = "Tarefa Salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Tarefa ja cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasResponseDTO> salvaTarefa(@RequestBody TarefasRequestDTO dto,
                                                          @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                tarefasService.salvarTarefa(dto, token)
        );
    }

    @GetMapping
    @Operation(summary = "Busca tarefa por email",
            description = "Busca tarefas do usuario, por email retirado do token JWT")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasResponseDTO>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca usuario por período", description = "Busca usuario por período de tempo")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasResponseDTO>> buscaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasPorPeriodo(dataInicial, dataFinal, token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefa", description = "Deleta tarefa por ID")
    @ApiResponse(responseCode = "200", description = "Tarefa deletado com sucesso")
    @ApiResponse(responseCode = "400", description = "Tarefa nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorId(@RequestParam("id") String id,
                                                   @RequestHeader(name = "Authorization", required = false) String token) {
        tarefasService.deletaTarefaPorId(id, token);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping
    @Operation(summary = "Atualiza Status", description = "Atualiza Status da Tarefa por ID")
    @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasResponseDTO> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Atualiza tarefa", description = "Atualiza dados da tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasResponseDTO> updateTarefas(@RequestBody TarefasRequestDTO dto,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }
}
