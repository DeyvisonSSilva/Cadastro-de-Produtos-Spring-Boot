package com.example.demo.teste.service;

import com.example.demo.dto.request.ProdutoRequestDTO;
import com.example.demo.dto.response.ProdutoResponseDTO;
import com.example.demo.exception.PrecoInvalidoException;
import com.example.demo.exception.ProdutoNaoEncontradoException;
import com.example.demo.mapper.ProdutoMapper;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.impl.ProdutoServicePadrao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoServicePadraoTest {

    @Mock
    private ProdutoRepository repository;

    @Mock
    private ProdutoMapper mapper;

    @InjectMocks
    private ProdutoServicePadrao service;

    @Test
    void deveSalvarProdutoComSucesso() {
        // arrange
        ProdutoRequestDTO dto = new ProdutoRequestDTO("Notebook", 3000);

        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Notebook");
        produto.setPreco(3000);

        ProdutoResponseDTO responseDTO =
                new ProdutoResponseDTO(1L, "Notebook", 3000);

        when(mapper.toEntity(dto)).thenReturn(produto);
        when(repository.save(produto)).thenReturn(produto);
        when(mapper.toDTO(produto)).thenReturn(responseDTO);

        // act
        ProdutoResponseDTO response = service.salvar(dto);

        // assert
        assertEquals(1L, response.getId());
        assertEquals("Notebook", response.getNome());
        assertEquals(3000, response.getPreco());

        verify(repository).save(produto);
    }

    @Test
    void deveLancarExcecaoParaPrecoInvalido() {
        ProdutoRequestDTO dto = new ProdutoRequestDTO("Produto", 0);

        assertThrows(
                PrecoInvalidoException.class,
                () -> service.salvar(dto)
        );

        verifyNoInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    void deveBuscarProdutoPorId() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Mouse");
        produto.setPreco(100);

        ProdutoResponseDTO responseDTO =
                new ProdutoResponseDTO(1L, "Mouse", 100);

        when(repository.findById(1L)).thenReturn(Optional.of(produto));
        when(mapper.toDTO(produto)).thenReturn(responseDTO);

        ProdutoResponseDTO response = service.buscarPorId(1L);

        assertEquals("Mouse", response.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoEncontrado() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                ProdutoNaoEncontradoException.class,
                () -> service.buscarPorId(1L)
        );
    }

    @Test
    void deveRemoverProdutoComSucesso() {
        when(repository.existsById(1L)).thenReturn(true);

        service.remover(1L);

        verify(repository).deleteById(1L);
    }
}

