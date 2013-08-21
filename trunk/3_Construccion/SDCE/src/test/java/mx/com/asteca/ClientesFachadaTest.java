package mx.com.asteca;

import java.util.List;

import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.fachada.ClienteFachada;
import mx.com.asteca.fachada.FachadaException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * {Insert class description here}
 * @version 1.0
 * @since Build 1.0
 * @author Jorge Amaro Coria
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ClientesFachadaTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientesFachadaTest.class);

	@Autowired
	private ClienteFachada clienteFachada;
	
	@Test
	public void testFachada(){
		try {
			List<ClienteDTO> listaClientes = clienteFachada.getAll();
			for (ClienteDTO clienteDTO : listaClientes) {
				LOGGER.debug("CLIENTE DTO: "+clienteDTO);
			}
		} catch (FachadaException e) {
			LOGGER.error("ERROR EN FACHADA DE CLIENTES TEST", e);
		}
		
	}
	
}
