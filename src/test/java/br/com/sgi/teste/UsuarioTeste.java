package br.com.sgi.teste;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.sgi.constantes.PERFIL;
import com.sgi.constantes.SEXO;
import com.sgi.model.Endereco;
import com.sgi.model.Usuario;
import com.sgi.rn.EnderecoRN;
import com.sgi.rn.UsuarioRN;
import com.sgi.utils.CPFUtils;
import com.sgi.utils.EmailUtils;
import com.sgi.utils.JsfUtils;

public class UsuarioTeste {

	private Usuario usuario = new Usuario();
	Endereco endereco = new Endereco();
	private Integer idPerfil;

	@Test
	public void salvar() {
		boolean isGerente = false;
		usuario.setNome("Flávia Raquel");
		usuario.setCpf("456.629.630-02");
		usuario.setEmail("ciceroregis25@gmail.com");
		usuario.setTelefone("(27) 2729-9960");
		usuario.setCelular("(27) 98439-0300");
		usuario.setSexo(SEXO.FEMININO);
		usuario.setPerfil(PERFIL.GERENTE);
		usuario.setAtivo(true);
		endereco.setCep("29165-045");
		endereco.setLogradouro("Rua Guaçuí");
		endereco.setLocalidade("taguatinga");
		endereco.setNumero("419");
		endereco.setBairro("Parque Residencial Laranjeiras");
		endereco.setUf("ES");

		// verificar e monitorar a localização dos objeto atravez do endereço

		String cpf = null;
		if (this.usuario.getEmail() == null) {
			JsfUtils.addErrorMessage(null, "Para cadastrar este usuário é necessário informar um email.");
		} else {
			isGerente = true;
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
					if (isGerente == true) {
						String nomeUsuario = this.usuario.getNome();
						 String pattern = "^([a-zA-ZÈ-Úè-ú]+)\\s+";

					      Pattern p = Pattern.compile(pattern);
					      Matcher m = p.matcher(nomeUsuario);
						/*if (m.find()) {
							System.out.println(m.group(0));
						}*/
							Usuario usuario = UsuarioRN.carregaUsuarioPorCpf(cpf);
							StringBuilder mensagem = new StringBuilder();
							
							/*Calendar c = Calendar.getInstance();
							
							int hora = c.get(Calendar.HOUR_OF_DAY);
								
							if(hora > 6 && hora < 12){
					            //System.out.println("Bom Dia");
					            mensagem.append("Bom Dia");
					        }else if(hora > 12 && hora < 18){
					        	mensagem.append("Boa Tarde");
					            //System.out.println("Boa Tarde");
					        }else{
					        	mensagem.append("Boa Noite");
					            //System.out.println("Boa Noite");
					        }
							*/
                   mensagem.append("<p>Olá, " + this.usuario.getNome() + ". Seja Bem-vindo(a) a nossa equipe. <br />"
                   		+ "Você está recebendo sua senha provisória de acesso ao sistema: <a href='http://www.dfvitoria.com.br:8080/sgp' target='_blank'>SGI - Gestão</a>");
					mensagem.append("<p>Sua Senha: <strong>" + usuario.getPw() + "</strong></p>");
					mensagem.append("<h3><strong> " + this.usuario.getNome() +"</strong> Para sua segurança, ao acessar o sistema altere sua senha!</h3><br />");
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
	}}