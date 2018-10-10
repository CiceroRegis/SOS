package com.sgi.security.auth;

import org.springframework.stereotype.Service;

import com.sgi.model.Usuario;
import com.sgi.rn.UsuarioRN;


@Service
public class UserService {
	
	public Usuario pesquisarPorCpf(String cpf) {
		Usuario usuario = UsuarioRN.autenticarUsuarioPorCpf(cpf);
		if(usuario != null){
			long idUsuario = usuario.getIdUsuario();
			/*Perfil perfil = UsuarioRN.buscarPorPerfilId(usuario.getIdPerfil());
			usuario.setPerfilId(perfil);*/
			UsuarioRN.porId(idUsuario);
			usuario.setIdUsuario(idUsuario);
		}
		return usuario; 
	}
}
