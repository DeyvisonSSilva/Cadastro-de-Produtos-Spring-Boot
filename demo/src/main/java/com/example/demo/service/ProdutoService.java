package com.example.demo.service;

import com.example.demo.dto.request.ProdutoRequestDTO;
import com.example.demo.dto.response.ProdutoResponseDTO;

import java.util.List;

public interface ProdutoService {

    List<ProdutoResponseDTO> listarProdutos();

    ProdutoResponseDTO buscarPorId(Long id);

    ProdutoResponseDTO salvarProduto(ProdutoRequestDTO dto);

    void removerProduto(Long id);
}
