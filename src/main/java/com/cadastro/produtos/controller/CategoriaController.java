package com.cadastro.produtos.controller;

import com.cadastro.produtos.entity.Categoria;
import com.cadastro.produtos.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(
        summary = "Listar todas as categorias",
        description = "Retorna uma lista com todas as categorias cadastradas"
    )
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @Operation(
        summary = "Buscar categoria por ID",
        description = "Retorna uma categoria específica baseada no ID fornecido"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @Operation(
        summary = "Criar nova categoria",
        description = "Cadastra uma nova categoria no sistema"
    )
    @PostMapping
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria) {
        Categoria nova = categoriaService.salvar(categoria);
        return ResponseEntity.status(201).body(nova);
    }

    @Operation(
        summary = "Atualizar categoria",
        description = "Atualiza os dados de uma categoria existente"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria atualizada = categoriaService.atualizar(id, categoria);
        return ResponseEntity.ok(atualizada);
    }

    @Operation(
        summary = "Deletar categoria",
        description = "Remove uma categoria do sistema"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}