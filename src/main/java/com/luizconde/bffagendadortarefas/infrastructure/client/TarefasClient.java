package com.luizconde.bffagendadortarefas.infrastructure.client;

import com.luizconde.bffagendadortarefas.business.dto.TarefasDTO;
import com.luizconde.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "Tarefas", url = "${tarefas.url}")
public interface TarefasClient {
    @PostMapping("/tarefas")
    TarefasDTO salvaTarefa(@RequestBody TarefasDTO dto,
                           @RequestHeader("Authorization") String token);

    @GetMapping("/tarefas")
    List<TarefasDTO> buscaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @GetMapping("/tarefas/eventos")
    List<TarefasDTO> buscaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);

    @DeleteMapping("/tarefas")
    Void deletaUsuarioPorId(@RequestParam("id") String id,
                                                   @RequestHeader("Authorization") String token);

    @PatchMapping("/tarefas")
    TarefasDTO alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                              @RequestParam("id") String id,
                                                              @RequestHeader("Authorization") String token);

    @PutMapping("/tarefas")
    TarefasDTO updateTarefas(@RequestBody TarefasDTO dto,
                                                    @RequestParam("id") String id,
                                                    @RequestHeader("Authorization") String token);

}
