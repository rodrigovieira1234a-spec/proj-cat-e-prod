package com.cadastro.produtos.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				// LIBERAR SWAGGER E DOCUMENTAÇÃO - ADICIONE ESTAS LINHAS!
				.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").authenticated()

				// Usuários COMUM e ADMIN podem obter e ver detalhes
				.requestMatchers(HttpMethod.GET, "/produtos", "/produtos/**").hasAnyAuthority("COMUM", "ADMIN")
				// ADMIN pode criar, atualizar e deletar produtos
				.requestMatchers(HttpMethod.POST, "/produtos").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/produtos/**").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/produtos/**").hasAuthority("ADMIN")

				.requestMatchers(HttpMethod.GET, "/categorias", "/categorias/**").hasAnyAuthority("COMUM", "ADMIN")

				.requestMatchers(HttpMethod.POST, "/categorias").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/categorias/**").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/categorias/**").hasAuthority("ADMIN")

				// Usuários COMUM podem ver apenas seus próprios dados
				.requestMatchers(HttpMethod.GET, "/usuarios/me").hasAnyAuthority("COMUM", "ADMIN")

				// Apenas ADMIN pode gerenciar todos os usuários
				.requestMatchers(HttpMethod.GET, "/usuarios", "/usuarios/**").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.POST, "/usuarios").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasAuthority("ADMIN")

				// Qualquer outra requisição precisa estar autenticada
				.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}