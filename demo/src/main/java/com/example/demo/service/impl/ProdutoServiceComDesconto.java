package com.example.demo.service.impl;

import com.example.demo.dto.request.ProdutoRequestDTO;
import com.example.demo.dto.response.ProdutoResponseDTO;
import com.example.demo.exception.ProdutoNaoEncontradoException;
import com.example.demo.mapper.ProdutoMapper;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.escrita.ProdutoEscritaService;
import com.example.demo.service.leitura.ProdutoLeituraService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//Validar regras de negócio
//Orquestrar persistência
//Lançar exceções específicas
// OCP Novas regras = nova implementação, não alteração da existente.

@Service
@Profile("desconto")
public class ProdutoServiceComDesconto implements ProdutoLeituraService, ProdutoEscritaService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    private static final double DESCONTO = 0.1; // 10%

    public ProdutoServiceComDesconto(ProdutoRepository repository, ProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        System.out.println(">>> Bean ativo: ProdutoServiceComDesconto");
    }

    @Override
    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        Produto produto = mapper.toEntity(dto);
        Produto salvo = repository.save(produto); // preço original salvo no banco
        return mapper.toDTOComDesconto(salvo, DESCONTO); // desconto aplicado no retorno
    }

    @Override
    public List<ProdutoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(produto -> mapper.toDTOComDesconto(produto, DESCONTO)) // desconto aplicado
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
        return mapper.toDTOComDesconto(produto, DESCONTO);
    }

    @Override
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {

        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());

        Produto atualizado = repository.save(produto);
        return mapper.toDTOComDesconto(atualizado, DESCONTO);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }
}