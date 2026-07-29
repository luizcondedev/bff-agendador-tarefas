package com.luizconde.bffagendadortarefas.controller;


import com.luizconde.bffagendadortarefas.business.TarefasService;
import com.luizconde.bffagendadortarefas.business.dto.TarefasDTO;
import com.luizconde.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
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
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> salvaTarefa(@RequestBody TarefasDTO dto,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                tarefasService.salvarTarefa(dto, token)
        );
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasPorPeriodo(dataInicial, dataFinal, token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaUsuarioPorId(@RequestParam("id") String id,
                                                   @RequestHeader("Authorization") String token) {
        tarefasService.deletaTarefaPorId(id, token);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                              @RequestParam("id") String id,
                                                              @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefas(@RequestBody TarefasDTO dto,
                                                    @RequestParam("id") String id,
                                                    @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }
}
