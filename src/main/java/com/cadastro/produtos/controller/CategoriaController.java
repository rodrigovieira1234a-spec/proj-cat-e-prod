package com.cadastro.produtos.controller;

import com.cadastro.produtos.entity.Categoria;
import com.cadastro.produtos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // GET - Listar todas as categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    // GET - Buscar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoria);
    }

    // POST - Criar nova categoria
    @PostMapping
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria) {
        Categoria nova = categoriaService.salvar(categoria);
        return ResponseEntity.status(201).body(nova);
    }

    // PUT - Atualizar categoria
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria atualizada = categoriaService.atualizar(id, categoria);
        return ResponseEntity.ok(atualizada);
    }

    // DELETE - Remover categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
