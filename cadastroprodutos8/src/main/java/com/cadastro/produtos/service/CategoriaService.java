package com.cadastro.produtos.service;

import com.cadastro.produtos.entity.Categoria;
import com.cadastro.produtos.repository.CategoriaRepository;
import com.cadastro.produtos.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Listar todas as categorias
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    // Buscar categoria por ID
    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + id));
    }

    // Criar nova categoria
    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Atualizar categoria existente
    public Categoria atualizar(Long id, Categoria categoria) {
        Categoria existente = buscarPorId(id);
        existente.setNome(categoria.getNome());
        return categoriaRepository.save(existente);
    }

    // Deletar categoria
    public void deletar(Long id) {
        Categoria existente = buscarPorId(id);
        categoriaRepository.delete(existente);
    }
}
