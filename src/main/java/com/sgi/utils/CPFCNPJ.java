package com.sgi.utils;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CPFCNPJ implements Serializable{

	private static final long serialVersionUID = 1L;
	private String opcao;
    private String campo;
    
    public boolean isFISICA(){
        return getOpcao()!=null && getOpcao().equals("FISICA");
    }
    public boolean isJURIDICA(){
        return getOpcao()!=null && getOpcao().equals("JURIDICA");
    }
    
    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }
    
    
}
