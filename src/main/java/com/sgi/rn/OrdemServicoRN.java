package com.sgi.rn;

import java.util.Date;
import java.util.List;

import com.sgi.dao.OrdemServicoDAO;
import com.sgi.model.Cliente;
import com.sgi.model.OrdemServico;

public class OrdemServicoRN {

	private OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();

	public OrdemServico carregar() {
		 this.ordemServicoDAO.busca();
		return null;
	}
	
	public OrdemServico preencherForm(Integer id) {
		return this.ordemServicoDAO.carregar(id);
	}

	public void salvar(OrdemServico os) {
		Cliente cliente =  new Cliente();
		Integer id = os.getId();
		if (id == null || id == 0) {
			os.setDataEntrada(new Date());
			this.ordemServicoDAO.salvar(os);
		} else 	
			this.ordemServicoDAO.atualizar(os);
}
	
	public OrdemServico visualizarOS(Integer id) {
		 return this.ordemServicoDAO.visualizarOS(id);
		
	}

	public void excluir(OrdemServico ordemServico ) {
		this.ordemServicoDAO.excluir(ordemServico);
	}

	public List<OrdemServico> listar() {
		return this.ordemServicoDAO.getList();
	}

	public void atualizar(OrdemServico os) {
		this.ordemServicoDAO.atualizar(os);
	}
}
