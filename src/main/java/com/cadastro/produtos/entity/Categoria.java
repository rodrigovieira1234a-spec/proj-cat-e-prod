package com.cadastro.produtos.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    // Um para muitos: uma categoria pode ter vários produtos
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonManagedReference  // controla a serialização para os produtos
    private List<Produto> produtos;

    // Construtores
    public Categoria() {}

    public Categoria(String nome) {
        this.nome = nome;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }
}
