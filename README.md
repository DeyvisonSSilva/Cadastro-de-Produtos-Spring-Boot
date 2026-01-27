# Exercicio do Bootcamp Deloitte - JAVA 2026

# 📦 Cadastro de Produtos – Spring Boot

Este é um projeto simples desenvolvido com **Spring Boot** que implementa uma API REST para cadastro e gerenciamento de produtos.
Inicialmente os dados eram armazenados em memória, porém o projeto foi evoluído para utilizar **JPA e Hibernate**, permitindo persistência dos dados em banco de dados relacional.

O objetivo do projeto é servir como exemplo didático para estudos de **Spring Boot**, **REST APIs**, **CRUD**, **JPA** e **Hibernate**.

---

## 🚀 Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* Maven
* H2 Database (banco em memória para desenvolvimento)
* API REST

---

## 📁 Estrutura do Projeto

```
com.example.demo
├── CadastroProdutoApplication.java
├── controller
│   └── CadastroProdutoController.java
├── model
│   └── Produto.java
└── repository
    └── ProdutoRepository.java
```

---

## 📌 Funcionalidades

* ✅ Verificar se a API está rodando
* ✅ Listar produtos
* ✅ Buscar produto por ID
* ✅ Cadastrar novo produto
* ✅ Remover produto por ID
* ✅ Persistência de dados com JPA e Hibernate

ℹ️ Os dados agora são persistidos em banco de dados (H2). Ao reiniciar a aplicação, os dados podem ser mantidos ou recriados de acordo com a configuração do JPA.

---

## 🗄️ Persistência com JPA e Hibernate

O projeto utiliza **Spring Data JPA** com **Hibernate** como provedor de persistência.

### Entidade Produto

A classe `Produto` é mapeada como uma entidade JPA:

* `@Entity`
* `@Id`
* `@GeneratedValue`

Isso permite que os objetos Java sejam automaticamente mapeados para tabelas no banco de dados.

### Repositório

Foi criado o `ProdutoRepository`, que estende `JpaRepository`, fornecendo automaticamente métodos como:

* `findAll()`
* `findById()`
* `save()`
* `deleteById()`

Sem a necessidade de implementação manual.

---

## ⚙️ Configuração do Banco de Dados

Exemplo de configuração no `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:produtosdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Acesse o console do H2 em:

```
http://localhost:8080/h2-console
```

---

## ▶️ Como Executar o Projeto

1. Clone o repositório

   ```bash
   git clone https://github.com/DeyvisonSSilva/Cadastro-de-Produtos-Spring-Boot.git
   ```

2. Acesse a pasta do projeto

   ```bash
   cd demo
   ```

3. Execute a aplicação

   ```bash
   ./mvnw spring-boot:run
   ```

   ou execute pelo método `main` da classe `CadastroProdutoApplication`.

---

## 🌐 Endpoints da API

### 🔹 Teste da API

`GET /produtos/hello`

Resposta:

```
API de Produtos rodando com Spring Boot
```

---

### 🔹 Listar todos os produtos

`GET /produtos`

---

### 🔹 Buscar produto por ID

`GET /produtos/{id}`

---

### 🔹 Cadastrar produto

`POST /produtos`

Body (JSON):

```json
{
  "nome": "Teclado",
  "preco": 150.0
}
```

---

### 🔹 Remover produto

`DELETE /produtos/{id}`

---

## 🧪 Testes

Os endpoints podem ser testados utilizando:

* Postman
* Insomnia
* cURL
* Navegador (para requisições GET)

---

## 📚 Objetivo Educacional

Este projeto faz parte do **Bootcamp Deloitte – Java 2026**, com foco em:

* Construção de APIs REST
* Boas práticas com Spring Boot
* Persistência de dados com JPA e Hibernate
* Evolução de um CRUD simples para uma aplicação mais próxima do mundo real

