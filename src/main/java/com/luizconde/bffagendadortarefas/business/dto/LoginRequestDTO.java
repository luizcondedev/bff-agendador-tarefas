package com.luizconde.bffagendadortarefas.business.dto;

import lombok.Builder;

@Builder
public record LoginRequestDTO(
        String email,
        String senha
) {}
