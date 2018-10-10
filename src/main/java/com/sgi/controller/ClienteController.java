package com.sgi.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.sgi.model.Cliente;
import com.sgi.model.Produto;
import com.sgi.rn.ClienteRN;

@ManagedBean
@SessionScoped
public class ClienteController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Cliente cliente = new Cliente();
	private String nome;
	private Produto aparelhos =  new Produto();
	private List<Cliente> listaClientes;
	private List<Cliente> clientesFiltrados;
	private ClienteRN clienteRN = new ClienteRN();
	
	@PostConstruct
	public void init() {
		if(this.listaClientes == null) {
		ClienteRN listarTodos = new ClienteRN();
		this.listaClientes = listarTodos.listar();
		}
	}

	public String novo() {
		this.cliente = new Cliente();
		return "/clientes/cliente_cad?faces-redirect=true";
	}

	public String editar() {
		this.cliente = clienteRN.carregar(this.cliente.getId());
		return "/clientes/cliente_cad?faces-redirect=true";
	}

	public String salvar() {
		
		clienteRN.salvar(this.cliente);
		this.cliente = new Cliente();
		
		return "/clientes/clientes_list?faces-redirect=true";
	}
	public void pesquisar() {
		clientesFiltrados = clienteRN.porNomesSemelhante(nome);
	}
	
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
        opcoes.put("responsive", true);
        opcoes.put("draggable", false);
        opcoes.put("width", 750);
        opcoes.put("height", 500);
        opcoes.put("contentWidth", "100%");
        opcoes.put("contentHeight", "90%");
        opcoes.put("headerElement", "customheader");
		RequestContext.getCurrentInstance().openDialog("selecaoCliente", opcoes, null);
	}
	
	public void selecionar(Cliente cliente) {
		RequestContext.getCurrentInstance().closeDialog(cliente);
	}
	
	
	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public Produto getAparelhos() {
		return aparelhos;
	}

	public void setAparelhos(Produto aparelhos) {
		this.aparelhos = aparelhos;
	}

	public ClienteRN getClienteRN() {
		return clienteRN;
	}

	public void setClienteRN(ClienteRN clienteRN) {
		this.clienteRN = clienteRN;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
