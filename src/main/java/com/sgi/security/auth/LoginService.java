package com.sgi.security.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sgi.model.Perfil;
import com.sgi.model.Usuario;


/**
 * @author Wenderson Ferreira
 *
 */
@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private UserService usuarioService;
	
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		UserDetails user = null;
		Usuario usuario = usuarioService.pesquisarPorCpf(cpf);
		if(usuario != null){
			user = new User(usuario.getCpf(), usuario.getSenha(), true, true, true, true, getGrantedAuthorities(usuario.getPerfilId()));
		}
		return user;
	}
	
	private static List<GrantedAuthority> getGrantedAuthorities(Perfil role) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	     authorities.add(new SimpleGrantedAuthority(role.getNome()));		
		 return authorities;
	}


}
