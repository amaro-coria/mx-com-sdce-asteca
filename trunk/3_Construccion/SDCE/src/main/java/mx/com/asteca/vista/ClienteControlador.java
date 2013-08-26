package mx.com.asteca.vista;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.comun.dto.TipoClienteDTO;
import mx.com.asteca.fachada.ClienteFachada;
import mx.com.asteca.fachada.FachadaException;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = Constantes.BEAN_CLIENTES)
@ViewScoped
public class ClienteControlador extends BaseController{

	private static Logger LOGGER = LoggerFactory.getLogger(ClienteControlador.class);
	
	private List<ClienteDTO> listaClientes;
	private List<TipoClienteDTO> listaTipoClientes;
	private short nuevoClienteTipo;
	private String nuevoClienteClave;
	private String nuevoClienteNombre;
	private String nuevoClienteResponsable;
	private String nuevoClienteTelefono;
	private String nuevoClienteEmail;

	@ManagedProperty("#{clienteFachadaImpl}")
	private ClienteFachada clienteFachada;
	
	private void initListaClientes() throws FachadaException{
		if(CollectionUtils.isEmpty(listaClientes)){		
			listaClientes = clienteFachada.getAll();
		}
	}
	
	private void initListaTipoClientes() throws FachadaException{
		if(CollectionUtils.isEmpty(listaTipoClientes)){		
			listaTipoClientes = clienteFachada.getTiposCliente();			
		}
	}
	
	public String saveCliente(){
		ClienteDTO nuevoCliente = new ClienteDTO();
			nuevoCliente.setClave(nuevoClienteClave);
			nuevoCliente.setEmail(nuevoClienteEmail);
			nuevoCliente.setNombre(nuevoClienteNombre);
			nuevoCliente.setResponsable(nuevoClienteResponsable);
			nuevoCliente.setTelefono(nuevoClienteTelefono);
			nuevoCliente.setTipoCliente(nuevoClienteTipo);
		try {
			clienteFachada.save(nuevoCliente);
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.ERROR_NUEVO_REGISTRO);
		}
		return "clientes.xhtml";
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

	/**
	 * @return the listaTipoClientes
	 */
	public List<TipoClienteDTO> getListaTipoClientes() {
		try {
			initListaTipoClientes();
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
		return listaTipoClientes;
	}

	/**
	 * @param listaTipoClientes the listaTipoClientes to set
	 */
	public void setListaTipoClientes(List<TipoClienteDTO> listaTipoClientes) {
		this.listaTipoClientes = listaTipoClientes;
	}

	/**
	 * @return the nuevoClienteTipo
	 */
	public short getNuevoClienteTipo() {
		return nuevoClienteTipo;
	}

	/**
	 * @param nuevoClienteTipo the nuevoClienteTipo to set
	 */
	public void setNuevoClienteTipo(short nuevoClienteTipo) {
		this.nuevoClienteTipo = nuevoClienteTipo;
	}

	/**
	 * @return the nuevoClienteClave
	 */
	public String getNuevoClienteClave() {
		return nuevoClienteClave;
	}

	/**
	 * @param nuevoClienteClave the nuevoClienteClave to set
	 */
	public void setNuevoClienteClave(String nuevoClienteClave) {
		this.nuevoClienteClave = nuevoClienteClave;
	}

	/**
	 * @return the nuevoClienteNombre
	 */
	public String getNuevoClienteNombre() {
		return nuevoClienteNombre;
	}

	/**
	 * @param nuevoClienteNombre the nuevoClienteNombre to set
	 */
	public void setNuevoClienteNombre(String nuevoClienteNombre) {
		this.nuevoClienteNombre = nuevoClienteNombre;
	}

	/**
	 * @return the nuevoClienteResponsable
	 */
	public String getNuevoClienteResponsable() {
		return nuevoClienteResponsable;
	}

	/**
	 * @param nuevoClienteResponsable the nuevoClienteResponsable to set
	 */
	public void setNuevoClienteResponsable(String nuevoClienteResponsable) {
		this.nuevoClienteResponsable = nuevoClienteResponsable;
	}

	/**
	 * @return the nuevoClienteTelefono
	 */
	public String getNuevoClienteTelefono() {
		return nuevoClienteTelefono;
	}

	/**
	 * @param nuevoClienteTelefono the nuevoClienteTelefono to set
	 */
	public void setNuevoClienteTelefono(String nuevoClienteTelefono) {
		this.nuevoClienteTelefono = nuevoClienteTelefono;
	}

	/**
	 * @return the nuevoClienteEmail
	 */
	public String getNuevoClienteEmail() {
		return nuevoClienteEmail;
	}

	/**
	 * @param nuevoClienteEmail the nuevoClienteEmail to set
	 */
	public void setNuevoClienteEmail(String nuevoClienteEmail) {
		this.nuevoClienteEmail = nuevoClienteEmail;
	}

	
}
