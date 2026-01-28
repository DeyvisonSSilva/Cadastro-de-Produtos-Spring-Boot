package com.example.demo.exception;

//Mensagens claras
//Código autoexplicativo
//Facilita tratamento global

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(Long id) {
        super("Produto não encontrado: " + id);
    }
}
