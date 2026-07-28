package com.luizconde.bffagendadortarefas.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TelefoneDTO {
    private long id;
    private String numero;
    private String ddd;
}
