package com.luizconde.bffagendadortarefas.infrastructure.exceptions;

public class ConflictException extends RuntimeException{
    public  ConflictException(String mensagem){
        super(mensagem);
    }

}
