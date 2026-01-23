# 📦 Cadastro de Produtos – Spring Boot

  Este é um projeto simples desenvolvido com *Spring Boot* que implementa uma API REST para cadastro e gerenciamento de produtos em memória.
  O objetivo do projeto é servir como exemplo didático para estudos de *Spring Boot*, *REST APIs* e *CRUD* básico.

## 🚀  Tecnologias Utilizadas

  - Java 17+ (ou compatível)
  - Spring Boot
  - Spring Web
  - Maven
  - API REST

## 📁 Estrutura do Projeto

  com.example.demo   
  ├── CadastroProdutoApplication.java  
  ├── controller  
  │   └── CadastroProdutoController.java  
  └── model  
      └── Produto.java  

## 📌 Funcionalidades

- ✅ Verificar se a API está rodando
- ✅ Listar produtos
- ✅ Buscar produto por ID
- ✅ Cadastrar novo produto
- ✅ Remover produto por ID

⚠️ Os dados são armazenados em memória, ou seja, são perdidos ao reiniciar a aplicação.

## ▶️ Como Executar o Projeto

1. Clone o repositório
    ```
    git clone https://github.com/DeyvisonSSilva/Cadastro-de-Produtos-Spring-Boot.git
    ```

2. Acesse a pasta do projeto
   ```
   cd demo
   ```

3. Execute a aplicação
   ```
   ./mvnw spring-boot:run
   ```
   ou pelo método main da classe CadastroProdutoApplication.

## 🌐 Endpoints da API

## 🔹 Teste da API
  
  GET /produtos/hello  
  Resposta: API de Produtos rodando com Spring Boot

## 🔹 Listar todos os produtos
  GET /produtos

## 🔹 Buscar produto por ID
  GET /produtos/{id}

## 🔹 Cadastrar produto
  POST /produtos  
  Body (JSON):  
  ```
  {
  "id": 1,
  "nome": "Teclado",
  "preco": 150.0
  }
  ```

## 🔹 Remover produto
  DELETE /produtos/{id}

## 🧪 Testes

Os endpoints podem ser testados utilizando:
- Postman
- Insomnia
- cURL
- Navegador (para requisições GET)
