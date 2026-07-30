package com.luizconde.bffagendadortarefas.infrastructure.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }
}
