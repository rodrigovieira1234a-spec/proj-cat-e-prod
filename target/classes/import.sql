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
