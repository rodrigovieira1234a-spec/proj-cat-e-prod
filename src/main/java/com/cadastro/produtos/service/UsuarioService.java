package com.cadastro.produtos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.cadastro.produtos.entity.Usuario;
import com.cadastro.produtos.exception.ResourceNotFoundException;
import com.cadastro.produtos.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // Listar todos os usuários
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    // Buscar usuário por ID
    public Usuario buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID inválido! Usuário não encontrado."));
    }

    // Buscar usuário por email
    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com email: " + email));
    }

    // Criar novo usuário
    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    // Atualizar usuário existente
    public Usuario atualizar(Integer id, Usuario usuario) {
        Usuario response = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID inválido! Usuário não encontrado."));

        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        
        // Se a senha foi enviada, criptografar
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            response.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        }
        
        // Atualizar perfis se foram enviados
        if (usuario.getPerfil() != null && !usuario.getPerfil().isEmpty()) {
            response.setPerfil(usuario.getPerfil());
        }

        return repository.save(response);
    }

    // Deletar usuário
    public void deletar(Integer id) {
        Usuario response = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID inválido! Usuário não encontrado."));
        repository.delete(response);
    }
}