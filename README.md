# Projeto Sistema de Cadastro de Produtos

## Índice

1. [Descrição Geral](#descrição-geral)
2. [Documentação do Projeto](#documentação-do-projeto)
3. [Integrantes](#integrantes)
4. [Dependências do Projeto](#dependências-do-projeto)
5. [Arquitetura do Sistema (Completa)](#arquitetura-do-sistema-completa)
6. [Principais Funcionalidades](#principais-funcionalidades)
   - [Categoria](#categoria)
   - [Produto](#produto)
   - [Usuário](#usuário)
7. [Rotas Principais (Endpoints)](#rotas-principais-endpoints)
   - [Categoria (`/categorias`)](#categoria-categorias)
   - [Produto (`/produtos`)](#produto-produtos)
   - [Usuário (`/usuarios`)](#usuário-usuarios)
8. [Estrutura das Tabelas](#estrutura-das-tabelas)
   - [Tabela `usuario`](#tabela-usuario)
   - [Tabela `perfil`](#tabela-perfil)
   - [Tabela `usuario_perfil`](#tabela-usuario_perfil)
   - [Tabela `categorias`](#tabela-categorias)
   - [Tabela `produtos`](#tabela-produtos)
9. [Testes de API](#testes-de-api)
   - [Ferramentas Recomendadas](#ferramentas-recomendadas)
   - [Autenticação HTTP Basic](#autenticação-http-basic)
   - [Exemplo 1: Criar Categoria (ADMIN)](#exemplo-1-criar-categoria-admin)
   - [Exemplo 2: Criar Produto (ADMIN)](#exemplo-2-criar-produto-admin)
   - [Exemplo 3: Listar Produtos (COMUM-ou-admin)](#exemplo-3-listar-produtos-comum-ou-admin)
   - [Exemplo 4: Criar Usuário (ADMIN)](#exemplo-4-criar-usuário-admin)
   - [Exemplo 5: Buscar Próprio Perfil](#exemplo-5-buscar-próprio-perfil)
   - [Exemplo 6: Tentativa de Acesso Negado](#exemplo-6-tentativa-de-acesso-negado)
10. [Configuração e Execução](#configuração-e-execução)
    - [Arquivo `application.properties`](#arquivo-applicationproperties)
11. [Conceitos Aplicados](#conceitos-aplicados)
12. [Conclusão](#conclusão)
    
## Descrição Geral

O **Sistema de Cadastro de Produtos** é uma aplicação desenvolvida em **Java com Spring Boot e MySQL**. Seu objetivo é gerenciar **categorias de produtos**, **produtos** e **usuários com controle de acesso**, permitindo o cadastro, listagem, atualização e exclusão de registros.

O sistema foi projetado com **autenticação HTTP Basic** e **autorização baseada em perfis** (COMUM e ADMIN), garantindo segurança e controle de acesso às operações.

## Documentação do Projeto

A documentação completa do sistema está disponível nos links abaixo:

- [**Documentação do Projeto – Sistema de Cadastro de Produtos (PDF)**](https://github.com/jmbraz/proj-cat-e-prod/blob/main/docs/Documenta%C3%A7%C3%A3o%20do%20Projeto%20%E2%80%93%20Sistema%20de%20Cadastro%20de%20Produtos.pdf)
- [**Apresentação do Projeto – Sistema de Cadastro de Produtos (PDF)**](https://github.com/jmbraz/proj-cat-e-prod/blob/main/docs/Apresenta%C3%A7%C3%A3o%20Cadastro%20de%20Produtos.pdf)
- [**Apresentação do Projeto – Sistema de Cadastro de Produtos (PPTX)**](https://github.com/jmbraz/proj-cat-e-prod/blob/main/docs/Apresenta%C3%A7%C3%A3o%20Cadastro%20de%20Produtos.pptx)

## Integrantes

- [Gabriel Carlos Rezende Nazario](https://github.com/)
- [José Eliomax Pereira Mariano](https://github.com/)
- [João Marcos Da Silva Braz](https://github.com/jmbraz)
- [Luana Alexandre da Silva](https://github.com/lualexan0)
- [Rodrigo Vieira Fagundes](https://github.com/rodrigovieira1234a-spec)


## Dependências do Projeto

```xml
<dependencies>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <!-- MySQL Connector -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- H2 Database (para testes) -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- DevTools -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Lombok (opcional) -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    
    <!-- Spring Boot Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

## Arquitetura do Sistema (Completa)

```
com.cadastro.produtos/
│
├── configs/
│   ├── SecurityConfigurations.java  → Configuração de segurança (autenticação/autorização)
│   └── AuthService.java             → Serviço de autenticação (carrega usuário)
│
├── controller/                      → Camada de apresentação (API REST)
│   ├── ProdutoController.java       → Endpoints de produtos
│   ├── CategoriaController.java     → Endpoints de categorias
│   └── UsuarioController.java       → Endpoints de usuários
│
├── entity/                          → Camada de modelo (entidades do banco)
│   ├── Produto.java                 → Entidade Produto
│   ├── Categoria.java               → Entidade Categoria
│   ├── Usuario.java                 → Entidade Usuario (implementa UserDetails)
│   └── Perfil.java                  → Entidade Perfil (implementa GrantedAuthority)
│
├── repository/                      → Camada de persistência (acesso ao banco)
│   ├── ProdutoRepository.java       → Interface JPA para Produto
│   ├── CategoriaRepository.java     → Interface JPA para Categoria
│   ├── UsuarioRepository.java       → Interface JPA para Usuario
│   └── PerfilRepository.java        → Interface JPA para Perfil
│
├── service/                         → Camada de negócio (lógica da aplicação)
│   ├── ProdutoService.java          → Regras de negócio para Produto
│   ├── CategoriaService.java        → Regras de negócio para Categoria
│   └── UsuarioService.java          → Regras de negócio para Usuario
│
├── exception/                       → Camada de tratamento de exceções
│   ├── ResourceNotFoundException.java    → Exceção para recurso não encontrado
│   ├── ValidationException.java          → Exceção para validações
│   └── GlobalExceptionHandler.java       → Tratamento global de exceções
│
└── Cadastroprodutos5Application.java     → Classe principal do Spring Boot
```

---

## Principais Funcionalidades

### Categoria
- Cadastrar nova categoria **(Requer ADMIN)**
- Listar todas as categorias **(COMUM e ADMIN)**
- Buscar categoria por ID **(COMUM e ADMIN)**
- Atualizar categoria existente **(Requer ADMIN)**
- Excluir categoria **(Requer ADMIN)**

### Produto
- Cadastrar novo produto (associando a uma categoria existente) **(Requer ADMIN)**
- Listar todos os produtos **(COMUM e ADMIN)**
- Buscar produto por ID **(COMUM e ADMIN)**
- Atualizar produto existente **(Requer ADMIN)**
- Excluir produto **(Requer ADMIN)**

### Usuário
- Cadastrar novo usuário com senha criptografada **(Requer ADMIN)**
- Listar todos os usuários **(Requer ADMIN)**
- Buscar usuário por ID **(Requer ADMIN)**
- Buscar dados do próprio usuário **(COMUM e ADMIN)**
- Atualizar usuário existente **(Requer ADMIN)**
- Excluir usuário **(Requer ADMIN)**

---

## Rotas Principais (Endpoints)

### Categoria (`/categorias`)

| Método | Endpoint | Descrição | Permissão |
|--------|----------|-----------|-----------|
| GET | `/categorias` | Lista todas as categorias | COMUM, ADMIN |
| GET | `/categorias/{id}` | Retorna uma categoria específica | COMUM, ADMIN |
| POST | `/categorias` | Cadastra uma nova categoria | ADMIN |
| PUT | `/categorias/{id}` | Atualiza uma categoria existente | ADMIN |
| DELETE | `/categorias/{id}` | Exclui uma categoria | ADMIN |

---

### Produto (`/produtos`)

| Método | Endpoint | Descrição | Permissão |
|--------|----------|-----------|-----------|
| GET | `/produtos` | Lista todos os produtos | COMUM, ADMIN |
| GET | `/produtos/{id}` | Retorna um produto específico | COMUM, ADMIN |
| POST | `/produtos` | Cadastra um novo produto | ADMIN |
| PUT | `/produtos/{id}` | Atualiza um produto existente | ADMIN |
| DELETE | `/produtos/{id}` | Exclui um produto | ADMIN |

---

### Usuário (`/usuarios`)

| Método | Endpoint | Descrição | Permissão |
|--------|----------|-----------|-----------|
| GET | `/usuarios/me` | Retorna dados do próprio usuário | COMUM, ADMIN |
| GET | `/usuarios` | Lista todos os usuários | ADMIN |
| GET | `/usuarios/{id}` | Retorna um usuário específico | ADMIN |
| POST | `/usuarios` | Cadastra um novo usuário | ADMIN |
| PUT | `/usuarios/{id}` | Atualiza um usuário existente | ADMIN |
| DELETE | `/usuarios/{id}` | Exclui um usuário | ADMIN |

---

## Estrutura das Tabelas

### Tabela: `usuario`

| Campo | Tipo | Chave | Descrição |
|-------|------|-------|-----------|
| id | INTEGER | PK | Identificador único do usuário |
| nome | VARCHAR(255) | - | Nome completo do usuário |
| email | VARCHAR(255) | UNIQUE | Email do usuário (usado como username) |
| senha | VARCHAR(255) | - | Senha criptografada (BCrypt) |

---

### Tabela: `perfil`

| Campo | Tipo | Chave | Descrição |
|-------|------|-------|-----------|
| id | INTEGER | PK | Identificador único do perfil |
| nome | VARCHAR(50) | - | Nome do perfil (COMUM ou ADMIN) |

---

### Tabela: `usuario_perfil` (Tabela de Junção)

| Campo | Tipo | Chave | Descrição |
|-------|------|-------|-----------|
| usuario_id | INTEGER | FK, PK | Referencia usuario.id |
| perfil_id | INTEGER | FK, PK | Referencia perfil.id |

**PK Composta:** (usuario_id, perfil_id)

---

### Tabela: `categorias`

| Campo | Tipo | Chave | Descrição |
|-------|------|-------|-----------|
| id | BIGINT | PK | Identificador único da categoria |
| nome | VARCHAR(255) | - | Nome da categoria (ex: Eletrônicos, Roupas) |

---

### Tabela: `produtos`

| Campo | Tipo | Chave | Descrição |
|-------|------|-------|-----------|
| id | BIGINT | PK | Identificador único do produto |
| nome | VARCHAR(255) | - | Nome do produto (ex: Celular, Camisa) |
| preco | DECIMAL(10,2) | - | Preço do produto |
| categoria_id | BIGINT | FK | Referencia categorias.id |


## Testes de API

### Ferramentas Recomendadas:
- **Insomnia**
- **Postman**
- **cURL** (linha de comando)
- **Thunder Client** (VS Code)

### Autenticação HTTP Basic

Todas as requisições requerem autenticação. No header:

```
Authorization: Basic base64(email:senha)
```

---

### Exemplo 1: Criar Categoria (ADMIN)

**Requisição:**
```http
POST http://localhost:8080/categorias
Authorization: Basic YWRtaW5AZW1haWwuY29tOnNlbmhhMTIz
Content-Type: application/json

{
  "nome": "Eletrônicos"
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "nome": "Eletrônicos",
  "produtos": []
}
```

---

### Exemplo 2: Criar Produto (ADMIN)

**Requisição:**
```http
POST http://localhost:8080/produtos
Authorization: Basic YWRtaW5AZW1haWwuY29tOnNlbmhhMTIz
Content-Type: application/json

{
  "nome": "Notebook Dell",
  "preco": 3500.00,
  "categoria": {
    "id": 1
  }
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "nome": "Notebook Dell",
  "preco": 3500.00,
  "categoria": {
    "id": 1,
    "nome": "Eletrônicos"
  }
}
```

---

### Exemplo 3: Listar Produtos (COMUM ou ADMIN)

**Requisição:**
```http
GET http://localhost:8080/produtos
Authorization: Basic dXN1YXJpb0BlbWFpbC5jb206c2VuaGExMjM=
```

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "nome": "Notebook Dell",
    "preco": 3500.00,
    "categoria": {
      "id": 1,
      "nome": "Eletrônicos"
    }
  },
  {
    "id": 2,
    "nome": "Mouse Logitech",
    "preco": 150.00,
    "categoria": {
      "id": 1,
      "nome": "Eletrônicos"
    }
  }
]
```

---

### Exemplo 4: Criar Usuário (ADMIN)

**Requisição:**
```http
POST http://localhost:8080/usuarios
Authorization: Basic YWRtaW5AZW1haWwuY29tOnNlbmhhMTIz
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "senha123",
  "perfil": [
    {"id": 1}
  ]
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
  "perfil": [
    {
      "id": 1,
      "nome": "COMUM"
    }
  ]
}
```

**Nota:** A senha é automaticamente criptografada com BCrypt pelo `UsuarioService`.

---

### Exemplo 5: Buscar Próprio Perfil

**Requisição:**
```http
GET http://localhost:8080/usuarios/me
Authorization: Basic am9hb0BlbWFpbC5jb206c2VuaGExMjM=
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
  "perfil": [
    {
      "id": 1,
      "nome": "COMUM"
    }
  ]
}
```

---

### Exemplo 6: Tentativa de Acesso Negado

**Requisição (usuário COMUM tentando criar produto):**
```http
POST http://localhost:8080/produtos
Authorization: Basic am9hb0BlbWFpbC5jb206c2VuaGExMjM=
Content-Type: application/json

{
  "nome": "Teclado",
  "preco": 200.00,
  "categoria": {"id": 1}
}
```

**Resposta (403 Forbidden):**
```json
{
  "timestamp": "2025-11-11T10:30:00",
  "status": 403,
  "error": "Forbidden",
  "message": "Access Denied"
}
```

---

## Configuração e Execução

### application.properties

```properties
# Configuração do Banco de Dados MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/cadastro_produtos
spring.datasource.username=root
spring.datasource.password=[INSIRA SUA SENHA]

# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Porta do servidor
server.port=8080
```

---

## Conceitos Aplicados

- **RESTful API** - Arquitetura REST com recursos bem definidos
- **Spring Security** - Autenticação HTTP Basic e Autorização baseada em roles
- **BCrypt** - Criptografia de senhas
- **JPA/Hibernate** - ORM para persistência de dados
- **Relacionamentos JPA** - OneToMany, ManyToOne, ManyToMany
- **Arquitetura em Camadas** - Separação de responsabilidades
- **Exception Handling** - Tratamento centralizado de erros
- **DTO Pattern** - Transferência de dados via JSON
- **Repository Pattern** - Abstração do acesso a dados
- **Service Layer** - Lógica de negócio isolada
- **UserDetails** - Integração com Spring Security
- **GrantedAuthority** - Sistema de permissões

---

## Conclusão

Este projeto exemplifica um **CRUD completo e seguro**, com:

- Modelagem simples e eficiente
- Relacionamentos entre entidades
- **Sistema de autenticação e autorização robusto**
- **Controle de acesso granular por perfis**
- Persistência via Spring Data JPA
- Estrutura organizada e de fácil manutenção
- **Segurança com criptografia de senhas**
- **Separação entre subsistema de autenticação e negócio**

O sistema está pronto para uso e pode ser expandido com novas funcionalidades, validações avançadas, paginação e filtros.
