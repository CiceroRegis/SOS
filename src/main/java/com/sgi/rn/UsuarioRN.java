package com.sgi.rn;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import com.sgi.dao.UsuarioDAO;
import com.sgi.model.Perfil;
import com.sgi.model.Usuario;
import com.sgi.utils.SenhaUtils;

/**
 * 
 * @author Cícero Régis
 *
 */

public class UsuarioRN {

	private static UsuarioDAO usuarioDAO = new UsuarioDAO();
	/*public void carregar(int id) {
		usuarioDAO.carregar(id);
	}*/

	public static boolean salvar(Usuario usuario) {
		Long id = usuario.getIdUsuario();
		if (id == null || id == 0) {
			try {
				String senhaAleatoria = SenhaUtils.produzirSenha();
				String senha = SenhaUtils.produzirChaveMD5(senhaAleatoria);
				usuario.setPw(senhaAleatoria);
				usuario.setSenha(senha);
				usuario.setData_cadastro(new Date());
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("Erro ao gerar a senha do usuário");
			} catch (Exception e) {
				throw new RuntimeException("Erro ao cadastrar o usuário");
			}
		}
		
		return usuarioDAO.salvar(usuario);
	}
	
	
	
	
	public static boolean alterarUsuario(Usuario usuario) {
		return usuarioDAO.atualizar(usuario);
	}

	public Usuario visualizarUsuario(String nome) {
		return usuarioDAO.visualizarUsuario(nome);
	}
	
	public static Usuario autenticarUsuarioPorCpf(String cpf) {
		return usuarioDAO.autenticarUsuarioPorCpf(cpf);
	}
	public static Perfil buscarPorPerfilId(long idPerfil) {
		return usuarioDAO.carregarPerfilPorId(idPerfil);
	}

	public void excluir(Integer id) {
		usuarioDAO.excluir(id);
	}

	public static List<Usuario> listar() {
		return usuarioDAO.getList();
	}

	public static Usuario porId(Long idUsuario) {
		return usuarioDAO.buscarId(idUsuario);
	}
	public static Usuario carregaUsuarioPorCpf(String cpf) {
		return usuarioDAO.carregaUsuarioPorCpf(cpf);
	}

}
