package com.cadastro.produtos.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cadastro.produtos.entity.Usuario;
import com.cadastro.produtos.exception.ResourceNotFoundException;
import com.cadastro.produtos.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthService implements UserDetailsService{
	
	@Autowired
	UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("Não encontrado"));
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
	}

}