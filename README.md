🛍️ Sistema de Cadastro de Produtos - Spring Boot
📋 Descrição

Projeto desenvolvido em Java com Spring Boot para gerenciar o cadastro de produtos.
Permite criar, listar, atualizar e excluir produtos, além de cadastrar usuários
O sistema utiliza o MySQL (produção) e o H2 Database (testes locais).

🧰 Funcionalidades

✅ Cadastrar produtos
🔍 Listar todos os produtos
✏️ Atualizar produtos
❌ Deletar produtos
🧱 Integração com MySQL ou H2

## ⚙️ Configuração

#### MySQL (Produção)

1. Crie um banco de dados no MySQL:

```sql
CREATE DATABASE produtos_db;
```

2. Configure o arquivo `application.properties`:
```properties
# Configuração do MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/produtos_db
spring.datasource.username=s[INSIRA SEU USUÁRIO]
spring.datasource.password=[INSIRA SUA SENHA]
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## ✅ Endpoints da API

### 👤 Usuários (`/usuarios`)

| Método | Endpoint | Ação | Acesso | Descrição |
|--------|----------|------|--------|-----------|
| GET | `/usuarios` | Listar todos os usuários | ADMIN | Retorna lista completa de usuários |
| GET | `/usuarios/{id}` | Buscar usuário por ID | ADMIN | Busca usuário específico |
| GET | `/usuarios/me` | Buscar perfil do usuário logado | ADMIN/COMUM | Retorna dados do usuário autenticado |
| POST | `/usuarios` | Criar novo usuário | ADMIN | Cadastra novo usuário (senha é criptografada) |
| PUT | `/usuarios/{id}` | Atualizar usuário | ADMIN | Atualiza dados do usuário |
| DELETE | `/usuarios/{id}` | Excluir usuário | ADMIN | Remove usuário do sistema |

#### Estrutura do Usuário

```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "senha123",
  "perfil": [
    {
      "id": 1,
      "nome": "ADMIN"
    }
  ]
}
```

#### Exemplo: Criar Usuário

**POST** `/usuarios`

```json
{
  "nome": "Maria Santos",
  "email": "maria@email.com",
  "senha": "senha123",
  "perfil": [
    {
      "id": 2,
      "nome": "COMUM"
    }
  ]
}
```

**Observação:** A senha é automaticamente criptografada com BCrypt antes de ser salva.

### 📦 Produtos (`/produtos`)

| Método | Endpoint | Ação | Acesso | Descrição |
|--------|----------|------|--------|-----------|
| GET | `/produtos` | Listar todos produtos | ADMIN/COMUM | Lista todos os produtos com suas categorias |
| GET | `/produtos/{id}` | Buscar produto por ID | ADMIN/COMUM | Busca produto específico |
| POST | `/produtos` | Criar produto | ADMIN | Cadastra novo produto |
| PUT | `/produtos/{id}` | Atualizar produto | ADMIN | Atualiza dados do produto |
| DELETE | `/produtos/{id}` | Excluir produto | ADMIN | Remove produto do sistema |

#### Estrutura do Produto

```json
{
  "id": 1,
  "nome": "Notebook Dell Inspiron",
  "preco": 3500.00,
  "categoria": {
    "id": 1,
    "nome": "Eletrônicos"
  }
}
```

#### Exemplo: Criar Produto

**POST** `/produtos`

```json
{
  "nome": "Mouse Logitech MX Master",
  "preco": 450.00,
  "categoria": {
    "id": 1
  }
}
```

#### Exemplo: Atualizar Produto

**PUT** `/produtos/1`

```json
{
  "nome": "Notebook Dell Inspiron 15",
  "preco": 3200.00,
  "categoria": {
    "id": 1
  }
}
```

### 🏷️ Categorias (`/categorias`)

| Método | Endpoint | Ação | Acesso | Descrição |
|--------|----------|------|--------|-----------|
| GET | `/categorias` | Listar categorias | ADMIN/COMUM | Lista todas as categorias com produtos |
| GET | `/categorias/{id}` | Buscar categoria por ID | ADMIN/COMUM | Busca categoria específica |
| POST | `/categorias` | Criar categoria | ADMIN | Cadastra nova categoria |
| PUT | `/categorias/{id}` | Atualizar categoria | ADMIN | Atualiza dados da categoria |
| DELETE | `/categorias/{id}` | Excluir categoria | ADMIN | Remove categoria (apenas se não tiver produtos) |

#### Estrutura da Categoria

```json
{
  "id": 1,
  "nome": "Eletrônicos",
  "produtos": [
    {
      "id": 1,
      "nome": "Notebook Dell",
      "preco": 3500.00
    }
  ]
}
```

#### Exemplo: Criar Categoria

**POST** `/categorias`

```json
{
  "nome": "Livros"
}
```

#### Exemplo: Atualizar Categoria

**PUT** `/categorias/1`

```json
{
  "nome": "Eletrônicos e Tecnologia"
}
```

## 🧪 Testando a API

### 1. Configuração no Postman/Insomnia

#### Criando um Usuário ADMIN (primeiro acesso)

Como não há usuários no sistema inicialmente, você precisará criar manualmente no banco:

```sql
-- Senha criptografada para "admin123"
INSERT INTO usuario (nome, email, senha) 
VALUES ('Administrador', 'admin@email.com', '$2a$10$XptfskLsT.yRbRq2NvcVV.n8ZzN.lBxJhkMjJqzM8kqvLaJYhrPmC');

-- Associar perfil ADMIN ao usuário
INSERT INTO usuario_perfil (usuario_id, perfil_id) 
VALUES (1, 1);
```

#### Autenticando no Postman/Insomnia

1. Selecione o tipo de autenticação: **Basic Auth**
2. Username: `admin@email.com`
3. Password: `admin123`

### 2. Fluxo de Testes Completo

#### Passo 1: Criar Categorias

**POST** `/categorias`

```json
{
  "nome": "Eletrônicos"
}
```

#### Passo 2: Criar Produtos

**POST** `/produtos`

```json
{
  "nome": "Smartphone Samsung Galaxy",
  "preco": 2500.00,
  "categoria": {
    "id": 1
  }
}
```

#### Passo 3: Listar Produtos

**GET** `/produtos`

#### Passo 4: Criar Usuário COMUM

**POST** `/usuarios`

```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "senha123",
  "perfil": [
    {
      "id": 2,
      "nome": "COMUM"
    }
  ]
}
```
#### Passo 5: Testar Acesso com Usuário COMUM

Mude a autenticação para:
- Username: `joao@email.com`
- Password: `senha123`

Tente:
- ✅ **GET** `/produtos` - Deve funcionar
- ✅ **GET** `/usuarios/me` - Deve retornar dados do João
- ❌ **POST** `/produtos` - Deve retornar 403 Forbidden
- ❌ **GET** `/usuarios` - Deve retornar 403 Forbidden
