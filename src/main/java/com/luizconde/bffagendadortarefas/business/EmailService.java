package com.luizconde.bffagendadortarefas.business;


import com.luizconde.bffagendadortarefas.business.dto.TarefasResponseDTO;
import com.luizconde.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient client;

    public void enviaEmail(TarefasResponseDTO dto) {
        client.enviarEmail(dto);
    }
}
