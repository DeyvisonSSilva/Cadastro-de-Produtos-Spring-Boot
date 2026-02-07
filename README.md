# Exercicio do Bootcamp Deloitte - JAVA 2026

# 📦 Cadastro de Produtos – Spring Boot

Este é um projeto simples desenvolvido com **Spring Boot** que implementa uma API REST para cadastro e gerenciamento de produtos.  
Inicialmente os dados eram armazenados em memória, porém o projeto foi evoluído para utilizar **JPA e Hibernate**, permitindo persistência dos dados em banco de dados relacional.  

Além disso, o projeto passou a seguir **boas práticas de desenvolvimento** com separação de camadas, uso de **DTOs**, **Service** e princípios **SOLID**, tornando o código mais modular, testável e fácil de manter.

O objetivo do projeto é servir como exemplo didático para estudos de **Spring Boot**, **REST APIs**, **CRUD**, **JPA** e **Hibernate**.

---

## 🚀 Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* Maven
* H2 Database (banco em memória para desenvolvimento)
* API REST
* Jakarta Bean Validation (`@Valid`, `@NotBlank`, `@Positive`)

---

## 📁 Estrutura do Projeto

```
com.example.demo
├── config
│   ├── CorsConfig.java          <-- Configuração de segurança de acesso
│   └── OpenApiConfig.java       <-- Configuração do Swagger/Doc
├── controller
│   └── ProdutoController.java
├── dto
│   ├── request
│   │   └── ProdutoRequestDTO.java
│   └── response
│       └── ProdutoResponseDTO.java
├── exception
│   ├── handler
│   │   ├── ApiErrorResponse.java
│   │   └── GlobalExceptionHandler.java
│   └── PrecoInvalidoException.java
│   └── ProdutoNaoEncontradoException.java
├── mapper
│   └── ProdutoMapper.java
├── model
│   └── Produto.java
├── repository
│   └── ProdutoRepository.java
└── service
    ├── escrita
        └──  ProdutoEscritaService.java
    └── leitura
        └──  ProdutoLeituraService.java
    └── impl
        └── ProdutoServicePadrao.java
        └── ProdutoServiceComDesconto.java
```
---

## 📌 Funcionalidades

* ✅ Verificar se a API está rodando
* ✅ Listar produtos
* ✅ Buscar produto por ID
* ✅ Cadastrar novo produto com validação
* ✅ Remover produto por ID
* ✅ Persistência de dados com JPA e Hibernate
* ✅ Arquitetura modular usando **DTOs**, **Service** e princípios **SOLID**

ℹ️ Os dados são persistidos em banco de dados (H2). Ao reiniciar a aplicação, os dados podem ser mantidos ou recriados de acordo com a configuração do JPA.

---

## Padrões de Projeto e Arquitetura Avançada

### 1. Uso de Data Transfer Objects (DTOs)

Para garantir a segurança e o encapsulamento, a API utiliza DTOs para separar a camada de persistência da camada de exibição.

   - `ProdutoRequestDTO`: Controla e valida os dados de entrada usando Bean Validation (`@NotBlank`, `@Positive`). O ID nunca é enviado pelo cliente, garantindo que o banco controle a identidade.

   - `ProdutoResponseDTO`: Define exatamente o que o cliente verá. Isso impede a exposição de campos sensíveis ou internos da entidade JPA.

---

### 2. Services: Segregação e Flexibilidade (SOLID)

A camada de serviço foi dividida seguindo os princípios de design orientado a objetos:

   - ISP (Interface Segregation Principle): Criamos interfaces distintas para Leitura `(ProdutoLeituraService)` e Escrita `(ProdutoEscritaService)`. Isso permite que componentes que só precisam ler dados não tenham acesso aos métodos de exclusão ou alteração.

   - LSP (Liskov Substitution Principle): O Controller depende das interfaces, permitindo que qualquer implementação (Padrão ou Com Desconto) seja injetada sem quebrar o sistema.

   - OCP (Open-Closed Principle): O sistema está aberto para extensões, mas fechado para modificações. Quer uma regra nova? Basta criar uma nova classe de Service sem tocar na anterior.

## 🛠️ Lógica de Negócio e Exceções Customizadas

Diferente de um CRUD simples, a camada de serviço orquestra regras complexas e lançamentos de exceções:

   - Validação de Preço: Lança `PrecoInvalidoException` se o valor for menor ou igual a zero.

   - Verificação de Existência: Antes de remover ou atualizar, o sistema verifica a presença do ID e lança `ProdutoNaoEncontradoException`.

   - Orquestração de Mapeamento: O Service utiliza o `ProdutoMapper` para converter dados de forma limpa, mantendo o Repository focado apenas em persistência.

---

### 3. Implementações Dinâmicas com Spring Profiles

O projeto utiliza Profiles do Spring para alternar o comportamento da regra de negócio sem alterar o código:

```
Implementação                      │                        Profile                             │                     Comportamento
ProdutoServicePadrao               │                        padrao                              │  Opera com os preços originais e validações rigorosas.
ProdutoServiceComDescontodesconto  │Aplica automaticamente 10% de desconto na visualização (DTO)│        mantendo o preço original no banco de dados.
```

---

### 4. Validação de Dados
- `GlobalExceptionHandler` → Centralização do tratamento de exceções (como ProdutoNaoEncontradoException), retornando respostas HTTP padronizadas.
- Uso de **Jakarta Bean Validation** para garantir a integridade dos dados antes de chegarem ao banco:
```java
@NotBlank
private String nome;

@Positive
private double preco;
```

---

## 🏗️ O Model / Entidade Produto

Esta classe representa a Entidade. Ela é o espelho de uma tabela no seu banco de dados. Graças ao Jakarta Persistence (JPA), o Java consegue conversar com o banco sem você precisar escrever uma linha de SQL.

* `@Entity` Diz ao Spring/Hibernate que esta classe é uma tabela do banco de dados.
  
* `@Table(name = "produtos")`: Define explicitamente o nome da tabela. Se não fosse usado, o Hibernate criaria uma tabela chamada "Produto".
  
* `@Id` e `@GeneratedValue` Define a chave primária. O `IDENTITY` delega ao banco de dados a tarefa de auto-incrementar o ID (1, 2, 3...).
  
* `@Column(nullable = false)`: Uma restrição de banco (Constraint). Garante que ninguém consiga salvar um produto sem nome diretamente na tabela.
  
Isso permite que os objetos Java sejam automaticamente mapeados para tabelas no banco de dados.

---

## 🔄 O Mapper

O `ProdutoMapper` é um componente `(@Component)` estratégico que isola a estrutura do banco de dados da estrutura da API.

- Desacoplamento: Permite que a entidade `Produto` mude sem quebrar o contrato da API com o front-end.
- Transformação de Dados:
   
   - `toEntity`: Converte o DTO de entrada em uma entidade pronta para persistência.

   - `toDTO`: Converte a entidade persistida em uma resposta limpa.
   
   - Lógica de Exibição: O método `toDTOComDesconto` demonstra como aplicar regras de visualização (como cálculos de preço) sem alterar o valor original armazenado no banco de dados, garantindo a integridade financeira da aplicação.

---

## 🗄️ Camada de Persistência (Repository)

A interface `ProdutoRepository` é o componente responsável por mediar a comunicação entre a lógica de negócio e o banco de dados (seja o H2 local ou o Azure SQL Database).

Ao estender `JpaRepository<Produto, Long>`, o Spring Data JPA gera automaticamente todas as implementações SQL necessárias em tempo de execução. Isso significa que não precisamos escrever consultas manuais para operações básicas.

Benefícios no Projeto:
   - **Abstração Total**: Não há necessidade de escrever código JDBC ou SQL complexo.
   - **Métodos Prontos**: Ganhamos acesso imediato a métodos como:
     
      - save(): Cria ou atualiza um produto.
        
      - findAll(): Retorna a lista completa de produtos.
        
      - findById(): Busca um registro pela chave primária.
        
      - deleteById(): Remove o registro do banco.
        
   - **Portabilidade**: Graças ao uso do Repository com JPA, o código que funciona no H2 local é o mesmo que funciona no Azure SQL, mudando apenas a configuração do Dialeto no application.properties.

---

## 📖 Documentação Interativa (Swagger/OpenAPI)

A API conta com documentação automatizada via Swagger UI, facilitando o teste dos endpoints e a integração com outros sistemas.

* Acesso Local: http://localhost:8080/swagger-ui.html

* Configuração: A classe `OpenApiConfig` define o título, versão e descrição da API, utilizando a especificação OpenAPI 3.0.

Com o Swagger, é possível:
   1. Visualizar todos os endpoints disponíveis.

   2. Verificar os modelos de dados (Schemas) de entrada e saída.

   3. Executar requisições `(Try it out)` diretamente pelo navegador.

---

## ⚙️ Configuração do Banco de Dados

### Uso do Banco Local(H2)

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

## ▶️ Como Executar o Projeto ( De Forma Local )

1. Clone o repositório

   ```bash
   git clone https://github.com/DeyvisonSSilva/Cadastro-de-Produtos-Spring-Boot.git
   ```

2. Acesse a pasta do projeto

   ```bash
   cd Cadastro-de-Produtos-Spring-Boot
   cd demo
   ```

3. Execute a aplicação

   ```bash
   ./mvnw spring-boot:run
   ```

   ou execute pelo método `main` da classe `CadastroProdutoApplication`.

---

## 🌐 Endpoints da API

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

### 🧪 Testes

Os endpoints podem ser testados utilizando:

* Postman
* Insomnia
* cURL
* Navegador (para requisições GET)
* Swagger

---

## ▶️ Acesso à Aplicação em Nuvem

O projeto está publicado e operacional na infraestrutura da Microsoft Azure. Você pode interagir com a API em tempo real sem precisar configurar um ambiente local.

## 🚀 Testando via Swagger

A forma mais fácil de testar todos os métodos (GET, POST, PUT, DELETE) é através da interface do Swagger.
   
   - URL do Swagger: https://bootcampdeloittejava-a9feebbkgwbrfsbm.brazilsouth-01.azurewebsites.net/swagger-ui/index.html

## Passo a passo para testar:
   
   1. Acesse o link acima.

   2. Clique em um endpoint `(ex: POST /produtos)`.

   3. Clique no botão "Try it out".

   4. Preencha o JSON no corpo da requisição e clique em "Execute".

   5. Confira o Server Response (Código 201 para sucesso na criação).

## 🔗 Verificação Direta via URL (Interface interativa Deloitte API Interface)

Você também pode checar a persistência dos dados diretamente no navegador acessando a URL base da aplicação: https://bootcampdeloittejava-a9feebbkgwbrfsbm.brazilsouth-01.azurewebsites.net
   
---

## 📚 Objetivo Educacional

Este projeto faz parte do **Bootcamp Deloitte – Java 2026**, com foco em:

* Construção de APIs REST
* Boas práticas com Spring Boot
* Persistência de dados com JPA e Hibernate
* Uso de DTOs e Service seguindo princípios SOLID
* Evolução de um CRUD simples para uma aplicação mais próxima do mundo real
