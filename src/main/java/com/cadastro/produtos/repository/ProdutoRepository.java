package com.cadastro.produtos.repository;

import com.cadastro.produtos.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Aqui você pode criar métodos personalizados, se quiser
    // Exemplo:
    // List<Produto> findByNomeContaining(String nome);
}
