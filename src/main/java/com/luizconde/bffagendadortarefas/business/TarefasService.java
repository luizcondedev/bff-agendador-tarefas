package com.luizconde.bffagendadortarefas.business;


import com.luizconde.bffagendadortarefas.business.dto.TarefasRequestDTO;
import com.luizconde.bffagendadortarefas.business.dto.TarefasResponseDTO;
import com.luizconde.bffagendadortarefas.infrastructure.client.TarefasClient;
import com.luizconde.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TarefasService {
    private final TarefasClient client;


    public TarefasResponseDTO salvarTarefa(TarefasRequestDTO dto, String token) {
        return client.salvaTarefa(dto, token);
    }

    public List<TarefasResponseDTO> buscaTarefasPorPeriodo(LocalDateTime dataInicial,
                                                           LocalDateTime dataFinal,
                                                           String token) {
        return client.buscaTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasResponseDTO> buscaTarefasPorEmail(String token) {
        return client.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id,
                                  String token) {
        client.deletaUsuarioPorId(id, token);
    }

    public TarefasResponseDTO alteraStatus(StatusNotificacaoEnum status, String id,
                                           String token) {
        return client.alteraStatusNotificacao(status, id, token);
    }

    public TarefasResponseDTO updateTarefas(TarefasRequestDTO dto, String id,
                                            String token) {
        return client.updateTarefas(dto, id, token);
    }
}
