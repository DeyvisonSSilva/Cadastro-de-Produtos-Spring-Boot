package com.example.demo.controller;

import com.example.demo.dto.request.ProdutoRequestDTO;
import com.example.demo.dto.response.ProdutoResponseDTO;
import com.example.demo.service.escrita.ProdutoEscritaService;
import com.example.demo.service.leitura.ProdutoLeituraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


// S (Single Responsibility)
//Recebe as requisições HTTP, Converte JSON para o DTO, Chama o Service e Retorna as respostas ao usuário

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoLeituraService leituraService;
    private final ProdutoEscritaService escritaService;

    public ProdutoController(
            ProdutoLeituraService leituraService,
            ProdutoEscritaService escritaService) {
        this.leituraService = leituraService;
        this.escritaService = escritaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDTO criar(
            @RequestBody @Valid ProdutoRequestDTO dto) {
        return escritaService.salvar(dto);
    }


    @GetMapping
    public List<ProdutoResponseDTO> listar() {
        return leituraService.listar();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscar(@PathVariable Long id) {
        return leituraService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProdutoRequestDTO dto) {
        return escritaService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        escritaService.remover(id);
    }
}