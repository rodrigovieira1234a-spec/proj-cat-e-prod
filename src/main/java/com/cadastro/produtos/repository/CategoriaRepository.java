package com.cadastro.produtos.repository;

import com.cadastro.produtos.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Métodos personalizados também podem ser adicionados
    // Exemplo:
    // Optional<Categoria> findByNome(String nome);
}
