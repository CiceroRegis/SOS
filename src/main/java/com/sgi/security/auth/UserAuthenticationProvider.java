package com.sgi.security.auth;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sgi.utils.CPFUtils;
import com.sgi.utils.SenhaUtils;

@Service
public class UserAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private LoginService loginService;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String cpf = authentication.getName();
		cpf = CPFUtils.clean(cpf);
		String senha = authentication.getCredentials().toString();
		try {
			senha = SenhaUtils.produzirChaveMD5(senha);
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}		
		try{
			User usuario = (User) loginService.loadUserByUsername(cpf);
			if(usuario == null){
				throw new UsernameNotFoundException("Cpf não localizado");			
			}
			if(!usuario.getPassword().equals(senha)){
				throw new BadCredentialsException("Senha incorreta");
			}else{			
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(cpf,senha, usuario.getAuthorities());
				return token;
			}
		}catch(Exception ex){
			System.out.println("#>> [UserAuthenticationProvider] " + ex.getMessage());
		}
		return null;
	}

	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
