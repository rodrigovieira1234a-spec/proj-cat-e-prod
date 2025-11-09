package com.cadastro.produtos.service;

import com.cadastro.produtos.entity.Produto;
import com.cadastro.produtos.entity.Categoria;
import com.cadastro.produtos.repository.ProdutoRepository;
import com.cadastro.produtos.repository.CategoriaRepository;
import com.cadastro.produtos.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Listar todos os produtos
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Buscar produto por ID
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
    }

    // Criar novo produto
    public Produto salvar(Produto produto) {
        // Validar se categoria existe
        Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + produto.getCategoria().getId()));
        produto.setCategoria(categoria);
        return produtoRepository.save(produto);
    }

    // Atualizar produto existente
    public Produto atualizar(Long id, Produto produto) {
        Produto existente = buscarPorId(id);
        existente.setNome(produto.getNome());
        existente.setPreco(produto.getPreco());

        // Atualizar categoria se necessário
        Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + produto.getCategoria().getId()));
        existente.setCategoria(categoria);

        return produtoRepository.save(existente);
    }

    // Deletar produto
    public void deletar(Long id) {
        Produto existente = buscarPorId(id);
        produtoRepository.delete(existente);
    }
}
