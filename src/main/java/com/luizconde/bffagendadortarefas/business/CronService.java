package com.luizconde.bffagendadortarefas.business;

import com.luizconde.bffagendadortarefas.business.dto.LoginRequestDTO;
import com.luizconde.bffagendadortarefas.business.dto.TarefasResponseDTO;
import com.luizconde.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {
    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value(("${usuario.senha}"))
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void BuscaTarefasProximaHora(){
        String token = login(converterParaRequestDTO());
        log.info("Iniciada a busca de tarefas");
        LocalDateTime horaFutura = LocalDateTime.now().plusMinutes(10);
        LocalDateTime horaFuturaMais5 = LocalDateTime.now().plusHours(2);

        List<TarefasResponseDTO> listaTarefas =  tarefasService.buscaTarefasPorPeriodo(horaFutura, horaFuturaMais5, token);
        log.info("Tarefas Encontrada: " + listaTarefas);

        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Email enviado para o usuario " + tarefa.emailUsuario());
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.id(), token);
        });
        log.info("Finalizada a busca e envio de notificações");
    }

    public String login(LoginRequestDTO dto){
        return usuarioService.login(dto);
    }

    public LoginRequestDTO converterParaRequestDTO(){
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
