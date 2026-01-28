package com.example.demo.service.leitura;

import com.example.demo.dto.response.ProdutoResponseDTO;

import java.util.List;

//Desacopla Controller da implementação
//Permite múltiplas implementações (LSP)
//Aplicação clara do ISP (Interface Segregation)
//Cada cliente depende só do que usa

public interface ProdutoLeituraService {
    List<ProdutoResponseDTO> listar();
    ProdutoResponseDTO buscarPorId(Long id);
}

