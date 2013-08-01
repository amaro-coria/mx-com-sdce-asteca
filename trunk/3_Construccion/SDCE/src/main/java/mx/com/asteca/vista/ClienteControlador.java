package mx.com.asteca.vista;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.fachada.ClienteFachada;
import mx.com.asteca.fachada.FachadaException;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;


@ManagedBean
@ViewScoped
public class ClienteControlador extends BaseController{

	private List<ClienteDTO> listaClientes;

	@Autowired
	private ClienteFachada clienteFachada;
	
	private void initListaClientes() throws FachadaException{
		if(CollectionUtils.isEmpty(listaClientes)){
			listaClientes = clienteFachada.getAllClientes();
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
	
	
	
}
