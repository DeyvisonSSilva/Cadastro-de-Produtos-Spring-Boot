package com.example.demo.teste.service;

import com.example.demo.dto.request.ProdutoRequestDTO;
import com.example.demo.dto.response.ProdutoResponseDTO;
import com.example.demo.mapper.ProdutoMapper;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.impl.ProdutoServiceComDesconto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceComDescontoTest {

    @Mock
    private ProdutoRepository repository;

    @Mock
    private ProdutoMapper mapper;

    @InjectMocks
    private ProdutoServiceComDesconto service;

    @Test
    void deveAplicarDescontoNoPreco() {
        ProdutoRequestDTO dto = new ProdutoRequestDTO("TV", 2000);

        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("TV");
        produto.setPreco(2000);

        ProdutoResponseDTO responseDTO = new ProdutoResponseDTO(1L, "TV", 1800);

        when(mapper.toEntity(dto)).thenReturn(produto);
        when(repository.save(produto)).thenReturn(produto);
        when(mapper.toDTOComDesconto(produto, 0.1)).thenReturn(responseDTO);

        ProdutoResponseDTO response = service.salvar(dto);

        assertEquals(1800, response.getPreco());
    }
}