package com.example.demo.exception;

//Mensagens claras
//Código autoexplicativo
//Facilita tratamento global

public class PrecoInvalidoException extends RuntimeException {
    public PrecoInvalidoException() {
        super("Preço do produto deve ser maior que zero");
    }
}