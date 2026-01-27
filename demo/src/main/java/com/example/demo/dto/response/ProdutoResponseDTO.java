package com.example.demo.dto.response;

public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private double preco;

    public ProdutoResponseDTO() {}

    public ProdutoResponseDTO(Long id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public double getPreco() {return preco;}
    public void setPreco(double preco) {this.preco = preco;}
}