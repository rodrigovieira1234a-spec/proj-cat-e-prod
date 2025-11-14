package com.cadastro.produtos.controller;

import com.cadastro.produtos.entity.Produto;
import com.cadastro.produtos.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(
        summary = "Listar todos os produtos",
        description = "Retorna uma lista com todos os produtos cadastrados"
    )
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @Operation(
        summary = "Buscar produto por ID",
        description = "Retorna um produto específico baseado no ID fornecido"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @Operation(
        summary = "Criar novo produto",
        description = "Cadastra um novo produto no sistema"
    )
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto novo = produtoService.salvar(produto);
        return ResponseEntity.status(201).body(novo);
    }

    @Operation(
        summary = "Atualizar produto",
        description = "Atualiza os dados de um produto existente"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        Produto atualizado = produtoService.atualizar(id, produto);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(
        summary = "Deletar produto",
        description = "Remove um produto do sistema"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}