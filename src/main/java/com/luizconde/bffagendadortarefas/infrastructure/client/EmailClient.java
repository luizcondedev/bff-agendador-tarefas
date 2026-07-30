package com.luizconde.bffagendadortarefas.infrastructure.client;

import com.luizconde.bffagendadortarefas.business.dto.TarefasResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Notificacao", url = "${notificacao.url}")
public interface EmailClient
{
    @PostMapping
    void enviarEmail(@RequestBody TarefasResponseDTO dto);
}
