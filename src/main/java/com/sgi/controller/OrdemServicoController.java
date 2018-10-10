package com.sgi.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotBlank;

import org.primefaces.event.SelectEvent;

import com.sgi.model.Cliente;
import com.sgi.model.OrdemServico;
import com.sgi.model.Usuario;
import com.sgi.rn.ClienteRN;
import com.sgi.rn.OrdemServicoRN;
import com.sgi.rn.UsuarioRN;

@ManagedBean
@SessionScoped
public class OrdemServicoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private OrdemServico ordemServico = new OrdemServico();
	private List<OrdemServico> listar;
	private OrdemServicoRN osRN = new OrdemServicoRN();
	private Usuario usuario = new Usuario();
	private UsuarioRN usuarioRN = new UsuarioRN();
	private Cliente cliente = new Cliente();
	private ClienteRN clienteRN = new ClienteRN();


	public String novo() {
		this.ordemServico =  new OrdemServico();
		this.ordemServico.setDataEntrada(new Date());
		return "/os/os_cad?faces-redirect=true";
	}
	
	@PostConstruct
	public void init() {
		OrdemServicoRN os = new OrdemServicoRN();
		this.listar = os.listar();
	}

	public String editar() {
		if (this.ordemServico != null) {
			this.ordemServico = osRN.preencherForm(this.ordemServico.getId());

			System.out.println("preenchendo Formulario com a Ordem de Serviço do Cliente >>>"
					+ ordemServico.getCliente().getNome());
		}
		return "/os/os_cad?faces-redirect=true";
	}

	public String salvar() {
		try {
			//Thread.sleep(1200);
			this.osRN.salvar(ordemServico);
			System.out.println("Salvando Ordem de Seviço do Cliente >>>" + ordemServico.getCliente().getNome());

			this.ordemServico = new OrdemServico();
		} catch (Exception e) {
			System.out.println("Erro ao tentar salvar a Ordem de serviço do Cliente>> " + e.getMessage());
		}
		return "/os/os_list?faces-redirect=true";
	}

	public String excluir() {
		try {
			this.osRN.excluir(ordemServico);
			System.out.println("Excluir a Ordem de Serviço do Cliente >>>" + ordemServico.getCliente().getNome());
		} catch (Exception e) {
			System.out.println("Erro ao tentar excluir ordem de Seriviço do cliente>> " + e.getMessage());
		}
		return "/os/os_list?faces-redirect=true";
	}

	public void clienteSelecionado(SelectEvent event) {
		Cliente cliente = (Cliente) event.getObject();
		ordemServico.setCliente(cliente);
	}
	public String visualizarOS() {
		if(ordemServico !=null) {
			ordemServico.getCliente().getNome();
			ordemServico.setDataEntrada(ordemServico.getDataEntrada());
		ordemServico = osRN.preencherForm(ordemServico.getId());
		
		System.out.println("Carrengando Ordem de Serviço do Cliente >>>"
				+ ordemServico.getCliente().getNome());
		}
		return "/os/visualizarOS_cad?faces-redirect=true";
	}

	@NotBlank
	public String getNomeCliente() {
		return ordemServico.getCliente() == null 
				? null : ordemServico.getCliente().getNome();
	}
	

	public void setNomeCliente(String nome) {
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public List<OrdemServico> getListar() {
		return listar;
	}

	public void setListar(List<OrdemServico> listar) {
		this.listar = listar;
	}

	public OrdemServicoRN getOsRN() {
		return osRN;
	}

	public void setOsRN(OrdemServicoRN osRN) {
		this.osRN = osRN;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioRN getUsuarioRN() {
		return usuarioRN;
	}

	public void setUsuarioRN(UsuarioRN usuarioRN) {
		this.usuarioRN = usuarioRN;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteRN getClienteRN() {
		return clienteRN;
	}

	public void setClienteRN(ClienteRN clienteRN) {
		this.clienteRN = clienteRN;
	}

}
