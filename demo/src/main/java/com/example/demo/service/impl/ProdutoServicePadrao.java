package com.example.demo.service.impl;

import com.example.demo.dto.request.ProdutoRequestDTO;
import com.example.demo.dto.response.ProdutoResponseDTO;
import com.example.demo.exception.PrecoInvalidoException;
import com.example.demo.exception.ProdutoNaoEncontradoException;
import com.example.demo.mapper.ProdutoMapper;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.escrita.ProdutoEscritaService;
import com.example.demo.service.leitura.ProdutoLeituraService;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

//Validar regras de negócio
//Orquestrar persistência
//Lançar exceções específicas
// OCP Novas regras = nova implementação, não alteração da existente.

@Service
@Primary
@Profile("padrao")
public class ProdutoServicePadrao
        implements ProdutoLeituraService, ProdutoEscritaService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoServicePadrao(ProdutoRepository repository, ProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        System.out.println(">>> Bean ativo: ProdutoServicePadrao");
    }

    @Override
    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        if (dto.getPreco() <= 0) {
            throw new PrecoInvalidoException();
        }

        Produto produto = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(produto));
    }

    @Override
    public List<ProdutoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
        return mapper.toDTO(produto);
    }

    @Override
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {

        if (dto.getPreco() <= 0) {
            throw new PrecoInvalidoException();
        }

        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());

        Produto atualizado = repository.save(produto);
        return mapper.toDTO(atualizado);
    }

    @Override
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new ProdutoNaoEncontradoException(id);
        }
        repository.deleteById(id);
    }
}
