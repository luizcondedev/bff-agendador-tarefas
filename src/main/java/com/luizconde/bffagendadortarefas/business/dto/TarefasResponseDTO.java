package com.luizconde.bffagendadortarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luizconde.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TarefasResponseDTO(
     String id,
     String nomeTarefa,
     String descricao,
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
     LocalDateTime dataCriacao,
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
     LocalDateTime dataEvento,
     String emailUsuario,
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
     LocalDateTime dataAlteracao,
     StatusNotificacaoEnum statusNotificacaoEnum
) {
}
