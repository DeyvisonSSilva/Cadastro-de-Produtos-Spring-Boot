package com.example.demo.service.escrita;

import com.example.demo.dto.request.ProdutoRequestDTO;
import com.example.demo.dto.response.ProdutoResponseDTO;

//Desacopla Controller da implementação
//Permite múltiplas implementações (LSP)
//Aplicação clara do ISP (Interface Segregation)
//Cada cliente depende só do que usa

public interface ProdutoEscritaService {
    ProdutoResponseDTO salvar(ProdutoRequestDTO dto);
    void remover(Long id);
}

