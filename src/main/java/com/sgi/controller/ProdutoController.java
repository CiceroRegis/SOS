package com.sgi.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.sgi.model.Cliente;
import com.sgi.model.Produto;
import com.sgi.rn.ClienteRN;
import com.sgi.rn.ProdutoRN;

@ManagedBean
@SessionScoped
public class ProdutoController implements Serializable{

	private static final long serialVersionUID = 1L;

	private Produto produto =  new Produto();
	private List<Produto> produtos;
	private Cliente cliente =  new Cliente();
	private List<SelectItem> clienteSelect;
	
	private List<Cliente> clientes;
	
	private List<Produto> listaTodos =  null;
	ProdutoRN produtoRN =  new  ProdutoRN();
	
	public String salvar() {
		ClienteRN clienteRN = new ClienteRN();
		clienteRN.salvar(cliente);
		this.produtoRN.salvar(this.produto);
		this.produto =  new Produto();
		return null;
	}
	
	public List<SelectItem> getClienteSelect() {
		if(clienteSelect == null) {
			
			clienteSelect =  new ArrayList<SelectItem>();
			ClienteRN clienteRN =  new ClienteRN();
			this.clientes  =clienteRN.listar();
			
			if(cliente != null &&  !clientes.isEmpty()) {
				
				SelectItem item;
				for(Cliente clienteLista :clientes) {
					item = new SelectItem(clienteLista,clienteLista.getNome());
					clienteSelect.add(item);
				}
			}
		}
		return clienteSelect;
	}

	public Produto getproduto() {
		return produto;
	}


	public void setproduto(Produto produto) {
		this.produto = produto;
	}


	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClienteSelect(List<SelectItem> clienteSelect) {
		this.clienteSelect = clienteSelect;
	}


	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Produto> getListaTodos() {
		return listaTodos;
	}
	public void setListaTodos(List<Produto> listaTodos) {
		this.listaTodos = listaTodos;
	}
	public ProdutoRN getprodutoRN() {
		return produtoRN;
	}
	public void setprodutoRN(ProdutoRN produtoRN) {
		this.produtoRN = produtoRN;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
