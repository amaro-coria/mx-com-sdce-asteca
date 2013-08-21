package mx.com.asteca.vista;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.fachada.ClienteFachada;
import mx.com.asteca.fachada.FachadaException;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ManagedBean(name = Constantes.BEAN_CLIENTES)
@ViewScoped
public class ClienteControlador extends BaseController{

	private static Logger LOGGER = LoggerFactory.getLogger(ClienteControlador.class);
	
	private List<ClienteDTO> listaClientes;

	@ManagedProperty("#{clienteFachadaImpl}")
	private ClienteFachada clienteFachada;
	
	private void initListaClientes() throws FachadaException{
		if(CollectionUtils.isEmpty(listaClientes)){		
			LOGGER.debug("LLAMANDO A CLIENTE FACHADA:"+clienteFachada);
			if(clienteFachada == null){
				LOGGER.debug("CLIENTE FACHADA NULO");
			}
			listaClientes = clienteFachada.getAll();
			for (ClienteDTO cliente : listaClientes) {
				LOGGER.debug("INICIALIZANDO CLIENTE: "+cliente);
			}
		}else{
			LOGGER.debug("LISTA YA INICIALIZADA");
			LOGGER.debug("REGRESANDO UNA LISTA CON ELEMENTOS:"+listaClientes.size());
		}
	}
	
	/**
	 * @return the listaClientes
	 */
	public List<ClienteDTO> getListaClientes() {
		try {
			initListaClientes();
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
		return listaClientes;
	}

	/**
	 * @param listaClientes the listaClientes to set
	 */
	public void setListaClientes(List<ClienteDTO> listaClientes) {
		this.listaClientes = listaClientes;
	}

	/**
	 * @param clienteFachada the clienteFachada to set
	 */
	public void setClienteFachada(ClienteFachada clienteFachada) {
		this.clienteFachada = clienteFachada;
	}
	
	
	
}
