package com.example.demo.controller;

import com.example.demo.model.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/produtos")
public class CadastroProdutoController {

    private List<Produto> produtos = new ArrayList<>();
    private AtomicLong contadorId = new AtomicLong(1);

    // GET /produtos/hello
    @GetMapping("/hello")
    public String hello() {
        return "API de Produtos rodando com Spring Boot";
    }

    // GET /produtos
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtos;
    }

    // GET /produtos/{id}
    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return produto.orElse(null);
    }

    // POST /produtos
    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto produto) {
        produto.setId(contadorId.getAndIncrement());
        produtos.add(produto);
        return produto;
    }

    // DELETE /produtos/{id}
    @DeleteMapping("/{id}")
    public String removerProduto(@PathVariable Long id) {
        produtos.removeIf(p -> p.getId().equals(id));
        return "Produto removido com sucesso";
    }
}
