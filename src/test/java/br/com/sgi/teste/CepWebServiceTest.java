/*package br.com.sgi.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.webservice.CepWebService;

*//***
 * 
 * @author cicer
 *
 *//*
public class CepWebServiceTest {

	
	private CepWebService cws;
	private String cep;
	
	@Before
	public void inicializar() throws Exception{
	     cep = "01001-000";
	     cws = new CepWebService(cep);
	}
	@Test
    public void verificaCepValido() throws Exception {
        assertEquals(1, cws.getResultado());
        assertEquals("SP", cws.getEstado());
        assertEquals("São Paulo", cws.getCidade());
        assertEquals("Sé", cws.getBairro());
        assertEquals("Praça da Sé", cws.getLogradouro());

    }
}
*/