package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class CadastroProdutoController {

    private final ProdutoRepository produtoRepository;

    public CadastroProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "API de Produtos com JPA e Hibernate 🚀";
    }

    // GET /produtos
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // GET /produtos/{id}
    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    // POST /produtos
    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    // DELETE /produtos/{id}
    @DeleteMapping("/{id}")
    public void removerProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }
}