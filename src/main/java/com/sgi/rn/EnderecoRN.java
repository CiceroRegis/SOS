package com.sgi.rn;

import com.sgi.dao.EnderecoDAO;
import com.sgi.model.Endereco;

public class EnderecoRN {
	private static EnderecoDAO enderecoDAO =  new EnderecoDAO();
	public static boolean salvarEndereco(Endereco endereco) {
			Long id = endereco.getId();
			if(id == null || id == 0) {
				endereco.setNumero(endereco.getNumero());
			}
		return enderecoDAO.salvar(endereco);
	}
	
}
