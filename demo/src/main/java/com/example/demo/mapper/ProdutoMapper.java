package com.example.demo.mapper;

import com.example.demo.dto.request.ProdutoRequestDTO;
import com.example.demo.dto.response.ProdutoResponseDTO;
import com.example.demo.model.Produto;
import org.springframework.stereotype.Component;

//Converte DTO ↔ Entity
//Não tem regra de negócio
//Não acessa o banco

@Component
public class ProdutoMapper {

    public Produto toEntity(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco()); // salva preço original no banco
        return produto;
    }

    public ProdutoResponseDTO toDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco()
        );
    }

    public ProdutoResponseDTO toDTOComDesconto(Produto produto, double percentualDesconto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco() * (1 - percentualDesconto) // desconto aplicado só no DTO (Evita que ele seja aplicado em toda requisição)
        );
    }
}