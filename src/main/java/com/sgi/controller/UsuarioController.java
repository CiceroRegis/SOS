package com.sgi.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.sgi.constantes.PERFIL;
import com.sgi.constantes.SEXO;
import com.sgi.model.Endereco;
import com.sgi.model.Usuario;
import com.sgi.rn.EnderecoRN;
import com.sgi.rn.UsuarioRN;
import com.sgi.utils.CPFUtils;
import com.sgi.utils.EmailUtils;
import com.sgi.utils.JsfUtils;
import com.webservice.ConsultaCEP;

@ManagedBean
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	private Endereco endereco = new Endereco();
	private List<Usuario> listaUsuarios;
	private List<SelectItem> sexos;
	private List<SelectItem> perfis;

	private ConsultaCEP consultaCEP;

	@PostConstruct
	public void init() {
		this.listaUsuarios = UsuarioRN.listar();
	}

	public List<SEXO> listaSexos() {
		return Arrays.asList(SEXO.values());
	}

	public List<PERFIL> listaPerfils() {
		return Arrays.asList(PERFIL.values());
	}

	public String novo() {
		this.usuario = new Usuario();
		this.endereco = new Endereco();
		return "/admin/usuario_cad?faces-redirect=true";
	}

	public String editarUsuario() {
		try {
			Long idUsuario = this.usuario.getIdUsuario();

			Usuario usuario = UsuarioRN.porId(idUsuario);
			Endereco endereco = usuario.getEndereco();
			this.usuario = usuario;
			this.endereco = endereco;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/usuario_cad?faces-redirect=true";
	}

	public void salvar() {
		 boolean isValido = false;
		String cpf = null;
		try {
			if (this.usuario.getEmail() == null) {
				JsfUtils.addErrorMessage(null, "Para cadastrar este usuário é necessário informar um email.");
			} else {
				isValido = true;
			}

			if (this.usuario.getIdUsuario() == null) {
				this.usuario.setCpf(CPFUtils.clean(this.usuario.getCpf()));
				cpf = this.usuario.getCpf();
				Usuario usuarioConsulta = UsuarioRN.carregaUsuarioPorCpf(cpf);
				if (usuarioConsulta == null) {
					if (this.usuario.getIdUsuario() == null || this.usuario.getIdUsuario() == 0) {
						this.usuario.setEndereco(endereco);
						UsuarioRN.salvar(this.usuario);
						EnderecoRN.salvarEndereco(endereco);
						if (isValido == true) {
								Usuario usuario = UsuarioRN.carregaUsuarioPorCpf(cpf);
								StringBuilder mensagem = new StringBuilder();

								  mensagem.append("<p>Prezado "+usuario.getNome()+", você está recebendo sua senha provisória de acesso ao sistema: <a href='http://www.dfvitoria.com.br:8080/sgi' target='_blank'>SGI - Gestão</a>");
								  mensagem.append("<p>Sua Senha: <strong>" + usuario.getPw() + "</strong></p>");
								  mensagem.append("<h3>"+usuario.getNome()+", Para sua segurança, ao acessar o sistema recomendamos que altere sua senha!</h3><br />");
								  EmailUtils.enviarEmailGmail(mensagem.toString(), usuario.getEmail(), "Registro de usuário");
						}
						this.usuario = new Usuario();
						this.endereco = new Endereco();
						JsfUtils.addInfoMessage(null,
								"O cadastro foi salvo com sucesso. Em breve você Receberá um e-mail de confirmação!");
					} else {
						JsfUtils.addWarnMessage(null, "Ocorreu um erro ao salvar o cadastro.");
					}
				} else {
					JsfUtils.addErrorMessage(null, "Já existe um registro salvo no sistema com este cpf.");
					return;
				}
			} else {
				if (this.usuario.getIdUsuario() != null || this.usuario.getIdUsuario() != 0) {
					this.usuario.setCpf(CPFUtils.clean(this.usuario.getCpf()));
					UsuarioRN.alterarUsuario(usuario);
					this.usuario = new Usuario();
					this.endereco = new Endereco();
					JsfUtils.addInfoMessage(null, "O cadastro foi atualizado com sucesso.");
				} else {
					JsfUtils.addErrorMessage(null, "Ocorreu um erro ao atualizar o cadastro.");
				}
			}
		} catch (Exception e) {
			JsfUtils.addErrorMessage(null, "Ocorreu um erro ao salvar o cadastro.");
		}

	}

	public String visualizarUsuario() {
		if (this.usuario != null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.usuario.setCpf(CPFUtils.format(this.usuario.getCpf()));
			this.usuario = usuarioRN.visualizarUsuario(usuario.getNome());
		}
		return "/admin/visualizarUsuario_cad?faces-redirect=true";
	}

	// consultaCep
	public void consultaCEP() throws InterruptedException {

		String cep = endereco.getCep();

		try {
			consultaCEP = new ConsultaCEP();
			consultaCEP.retornaCEP(cep);
			//Thread.sleep(18000);
			endereco.setLogradouro(consultaCEP.getLogradouro());
			endereco.setBairro(consultaCEP.getBairro());
			endereco.setLocalidade(consultaCEP.getLocalidade());
			endereco.setUf(consultaCEP.getUf());
		} catch (IOException e) {
			JsfUtils.addWarnMessage(null, "Cep não encontrado ou erro no servidor");
			e.printStackTrace();
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<SelectItem> getSexos() {
		return sexos;
	}

	public void setSexos(List<SelectItem> sexos) {
		this.sexos = sexos;
	}

	public List<SelectItem> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<SelectItem> perfis) {
		this.perfis = perfis;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
