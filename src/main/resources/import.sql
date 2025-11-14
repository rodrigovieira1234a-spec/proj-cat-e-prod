-- Inserindo categorias
INSERT INTO categorias (nome) VALUES ('Eletrônicos');
INSERT INTO categorias (nome) VALUES ('Roupas');
INSERT INTO categorias (nome) VALUES ('Livros');
INSERT INTO categorias (nome) VALUES ('Alimentos');

-- Inserindo produtos
INSERT INTO produtos (nome, preco, categoria_id) VALUES ('Notebook Lenovo', 3500.00, 1);
INSERT INTO produtos (nome, preco, categoria_id) VALUES ('Smartphone Samsung', 2500.00, 1);
INSERT INTO produtos (nome, preco, categoria_id) VALUES ('Camisa Polo', 120.00, 2);
INSERT INTO produtos (nome, preco, categoria_id) VALUES ('Calça Jeans', 200.00, 2);
INSERT INTO produtos (nome, preco, categoria_id) VALUES ('O Senhor dos Anéis', 89.90, 3);
INSERT INTO produtos (nome, preco, categoria_id) VALUES ('Clean Code', 150.00, 3);
INSERT INTO produtos (nome, preco, categoria_id) VALUES ('Arroz 5kg', 25.90, 4);
INSERT INTO produtos (nome, preco, categoria_id) VALUES ('Feijão Carioca 1kg', 8.50, 4);

-- ============ PASSO 1: CRIAR PERFIS ============
INSERT INTO perfil (nome) VALUES ('COMUM');
INSERT INTO perfil (nome) VALUES ('ADMIN');

-- ============ PASSO 2: CRIAR USUÁRIOS ============

-- ADMIN 1: Administrador Principal
-- Email: admin@email.com
-- Senha: 123
INSERT INTO usuario (nome, email, senha) VALUES 
('Administrador', 
 'admin@email.com', 
 '$2a$12$Q.kaRuAcAT7ilWKnp4KhsuQU/AlzefYkbJF7XgzreZj4AGwGOA9fm');

-- ADMIN 2: Gerente
-- Email: gerente@email.com  
-- Senha: 123
INSERT INTO usuario (nome, email, senha) VALUES 
('Carlos Gerente', 
 'gerente@email.com', 
 '$2a$12$Q.kaRuAcAT7ilWKnp4KhsuQU/AlzefYkbJF7XgzreZj4AGwGOA9fm');

-- COMUM 1: João Silva
-- Email: joao@email.com
-- Senha: 123
INSERT INTO usuario (nome, email, senha) VALUES 
('João Silva', 
 'joao@email.com', 
 '$2a$12$Q.kaRuAcAT7ilWKnp4KhsuQU/AlzefYkbJF7XgzreZj4AGwGOA9fm');

-- COMUM 2: Maria Santos
-- Email: maria@email.com
-- Senha: 123
INSERT INTO usuario (nome, email, senha) VALUES 
('Maria Santos', 
 'maria@email.com', 
 '$2a$12$Q.kaRuAcAT7ilWKnp4KhsuQU/AlzefYkbJF7XgzreZj4AGwGOA9fm');

-- COMUM 3: Pedro Oliveira
-- Email: pedro@email.com
-- Senha: 123
INSERT INTO usuario (nome, email, senha) VALUES 
('Pedro Oliveira', 
 'pedro@email.com', 
 '$2a$12$Q.kaRuAcAT7ilWKnp4KhsuQU/AlzefYkbJF7XgzreZj4AGwGOA9fm');

-- ============ PASSO 3: ASSOCIAR PERFIS ============

-- Administrador → ADMIN (perfil_id = 2)
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (1, 2);

-- Carlos Gerente → ADMIN (perfil_id = 2)
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (2, 2);

-- João Silva → COMUM (perfil_id = 1)
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (3, 1);

-- Maria Santos → COMUM (perfil_id = 1)
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (4, 1);

-- Pedro Oliveira → COMUM (perfil_id = 1)
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (5, 1);