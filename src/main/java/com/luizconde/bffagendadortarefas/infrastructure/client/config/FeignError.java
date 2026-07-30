package com.luizconde.bffagendadortarefas.infrastructure.client.config;

import com.luizconde.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import com.luizconde.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.luizconde.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.luizconde.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 409 -> new ConflictException("Erro atributo já existente");
            case 403 -> new ResourceNotFoundException("Erro atributo nao encontrado");
            case 401 -> new UnauthorizedException("Erro usuario nao autorizado");
            default -> new BusinessException("Erro de servidor");
        };
    }
}
