package com.luizconde.bffagendadortarefas.controller;


import com.luizconde.bffagendadortarefas.business.EmailService;
import com.luizconde.bffagendadortarefas.business.dto.TarefasResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> enviarEmail(@RequestBody TarefasResponseDTO dto){
        emailService.enviaEmail(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
