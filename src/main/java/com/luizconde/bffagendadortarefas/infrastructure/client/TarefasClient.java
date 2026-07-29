package com.luizconde.bffagendadortarefas.infrastructure.client;

import com.luizconde.bffagendadortarefas.business.dto.TarefasRequestDTO;
import com.luizconde.bffagendadortarefas.business.dto.TarefasResponseDTO;
import com.luizconde.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "Tarefas", url = "${tarefas.url}")
public interface TarefasClient {
    @PostMapping("/tarefas")
    TarefasResponseDTO salvaTarefa(@RequestBody TarefasRequestDTO dto,
                                   @RequestHeader("Authorization") String token);

    @GetMapping("/tarefas")
    List<TarefasResponseDTO> buscaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @GetMapping("/tarefas/eventos")
    List<TarefasResponseDTO> buscaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);

    @DeleteMapping("/tarefas")
    Void deletaUsuarioPorId(@RequestParam("id") String id,
                                                   @RequestHeader("Authorization") String token);

    @PatchMapping("/tarefas")
    TarefasResponseDTO alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                               @RequestParam("id") String id,
                                               @RequestHeader("Authorization") String token);

    @PutMapping("/tarefas")
    TarefasResponseDTO updateTarefas(@RequestBody TarefasRequestDTO dto,
                                     @RequestParam("id") String id,
                                     @RequestHeader("Authorization") String token);

}
