package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.comun.dto.PermisosBooleanDTO;
import mx.com.asteca.comun.dto.TipoClienteDTO;
import mx.com.asteca.fachada.ClienteFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.persistencia.entidades.Clientes;
import mx.com.asteca.util.EmailValidator;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;

@ManagedBean(name = Constantes.BEAN_CLIENTES)
@ViewScoped
public class ClienteControlador extends BaseController implements Serializable {

	/**
	 * Declara ID serializable
	 */
	private static final long serialVersionUID = 1L;
	private static final String modulo = Constantes.MODULO_CLIENTE;
	/*
	 * Tiene que ser transient para no generar excepcion de no serializacion
	 */
	@ManagedProperty("#{clienteFachadaImpl}")
	private transient ClienteFachada fachadaCliente;
	private ClienteDTO clienteSelected;
	private ClienteDTO clienteNuevo;
	private TipoClienteDTO tipoClienteSelected;

	private List<ClienteDTO> listaClientes;
	private List<TipoClienteDTO> listaTipoClientes;
	private List<SelectItem> listaSelectTiposClientes;
	private List<SelectItem> listaSelectClientes;
	private List<SelectItem> listaSelectNombres;
	private List<SelectItem> listaSelectReponsable;
	private List<SelectItem> listaSelectClave;
	private Integer idTipoCliente;
	private Integer idTipoClienteFilter;
	private Integer idClienteFilter;
	private Integer idCliente;
	private Integer selectedIdCliente;
	private Integer selectedTipoCliente;
	private String selectedClienteNombre;
	private String selectedClienteClave;
	private String selectedClienteResponsable;
	private String selectedClienteTelefono;
	private String selectedClienteEmail;
	private String claveSelected;
	private String nombreSelected;
	private String responsableSelected;
	private List<ClienteDTO> filteredClientes;

	public ClienteControlador() {
		clienteSelected = new ClienteDTO();
		clienteNuevo = new ClienteDTO();
		tipoClienteSelected = new TipoClienteDTO();
	}

private PermisosBooleanDTO permisos;
	
	@PostConstruct
	public void populate(){
		setPermisos(super.stablishSessionPermissions());
	}

	/**
	 * @return the permisos
	 */
	public PermisosBooleanDTO getPermisos() {
		return permisos;
	}



	/**
	 * @param permisos the permisos to set
	 */
	public void setPermisos(PermisosBooleanDTO permisos) {
		this.permisos = permisos;
		super.setAlta(permisos.isAlta());
		super.setBorrar(permisos.isBorrar());
		super.setCambios(permisos.isEdicion());
		super.setConsulta(permisos.isConsulta());
		super.setImpresion(permisos.isImpresion());
	}
	
	public void refreshClientes() {
		try {
			listaClientes = fachadaCliente.getAll();
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
	}

	public void initSelectClientes() {
		if (CollectionUtils.isEmpty(listaSelectClientes)) {
			listaSelectClientes = new ArrayList<SelectItem>();
			if (!CollectionUtils.isEmpty(getListaClientes())) {
				for (ClienteDTO dto : getListaClientes()) {
					SelectItem item = new SelectItem(dto.getIdCliente(),
							dto.getNombre());
					listaSelectClientes.add(item);
				}
			}
		}
	}

	public void initSelectClave() {
		if (CollectionUtils.isEmpty(listaSelectClave)) {
			listaSelectClave = new ArrayList<SelectItem>();
			try {
				List<ClienteDTO> listaClientes = fachadaCliente.getAll();
				if (!CollectionUtils.isEmpty(listaClientes)) {
					for (ClienteDTO clienteDTO : listaClientes) {
						SelectItem item = new SelectItem(clienteDTO.getClave(),
								clienteDTO.getClave());
						listaSelectClave.add(item);
					}
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	public void initSelectTiposClientes() {
		if (CollectionUtils.isEmpty(listaSelectTiposClientes)) {
			listaSelectTiposClientes = new ArrayList<SelectItem>();
			if (!CollectionUtils.isEmpty(getListaTipoClientes())) {
				for (TipoClienteDTO dto : getListaTipoClientes()) {
					SelectItem item = new SelectItem(dto.getIdTipoCliente(),
							dto.getNombre());
					listaSelectTiposClientes.add(item);
				}
			}
		}
	}

	public void initListaClientes() {
		if (CollectionUtils.isEmpty(listaClientes)) {
			try {
				listaClientes = fachadaCliente.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	public void addMessageTest(ActionEvent e) {
		super.addErrorMessage("HOLA MUNDO MENSAJE");
	}

	public void initListaTiposClientes() {
		if (CollectionUtils.isEmpty(listaTipoClientes)) {
			try {
				listaTipoClientes = fachadaCliente.getTiposCliente();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	public void cancelDeleteCliente(ActionEvent e) {
		setSelectedClienteClave("");
		setSelectedClienteNombre("");
		setSelectedClienteResponsable("");
	}

	public void deleteCliente(ActionEvent e) {
		try {
			fachadaCliente.remove(clienteSelected);
			listaClientes.remove(clienteSelected);
			initSelectClave();
			cambiaClaveSelected();
			cambiaNombreSelected();
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_DELETE_REGISTRO);
			addBitacora(Constantes.ACCION_DELETE_REGISTRO, Constantes.ACCION_DELETE_REGISTRO_FALLIDO_MENSAJE+":Cliente"+clienteSelected.getIdCliente()+":");
			return;
		}
		setSelectedClienteClave("");
		setSelectedClienteNombre("");
		setSelectedClienteResponsable("");
		super.addInfoMessage(Constantes.DELETE_REGISTRO_EXITOSO);
		addBitacora(Constantes.ACCION_DELETE_REGISTRO, Constantes.ACCION_DELETE_REGISTRO_EXITOSO_MENSAJE+":Cliente "+clienteSelected.getIdCliente()+":");
	}

	public void updateCliente(ActionEvent e) {
		if (clienteSelected.getIdCliente() == 0) {
			super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING,
					Constantes.ERROR_NECESITAS_SELECCIONAR_UN_CLIENTE);
			return;
		}
		if (selectedTipoCliente == 0) {
			super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING,
					Constantes.ERROR_NECESITAS_SELECCIONAR_UN_TIPO_CLIENTE);
			return;
		}
		if (selectedClienteNombre != null && !selectedClienteNombre.isEmpty()) {
			clienteSelected.setNombre(selectedClienteNombre);
		}
		if (selectedClienteResponsable != null
				&& !selectedClienteResponsable.isEmpty()) {
			clienteSelected.setResponsable(selectedClienteResponsable);
		}
		if (selectedClienteTelefono != null
				&& !selectedClienteTelefono.isEmpty()) {
			clienteSelected.setTelefono(selectedClienteTelefono);
		}
		if (selectedClienteEmail != null && !selectedClienteEmail.isEmpty()) {
			clienteSelected.setEmail(selectedClienteEmail);
		}
		clienteSelected.setTipoCliente(selectedTipoCliente);
		if (selectedClienteClave != null && !selectedClienteClave.isEmpty()) {
			clienteSelected.setClave(selectedClienteClave);
		}
		try {
			fachadaCliente.update(clienteSelected);
			int indexListSelected = listaClientes.indexOf(clienteSelected);
			if (indexListSelected > 0) {
				listaClientes.set(indexListSelected, clienteSelected);
			}
			initSelectClave();
			cambiaClaveSelected();
			cambiaNombreSelected();
			super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO, Constantes.UPDATE_REGISTRO_EXITOSO);		
			addBitacora(Constantes.ACCION_UPDATE_REGISTRO, Constantes.ACCION_UPDATE_REGISTRO_EXITOSO_MENSAJE+":Cliente "+clienteSelected.getIdCliente()+":");
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_UPDATE_REGISTRO);
			addBitacora(Constantes.ACCION_UPDATE_REGISTRO, Constantes.ACCION_UPDATE_REGISTRO_FALLIDO_MENSAJE+":Cliente:");
			return;
		}
	}

	public void saveCliente(ActionEvent e) {
		boolean b = validaDatos();
		if(b){
			if (idTipoCliente == 0) {
				super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING,
						Constantes.ERROR_NECESITAS_SELECCIONAR_UN_TIPO_CLIENTE);
				return;
			}
			clienteNuevo.setTipoCliente(idTipoCliente);
			try {
				int pk = fachadaCliente.save(clienteNuevo);
				clienteNuevo.setIdCliente(pk);
				if (!CollectionUtils.isEmpty(listaClientes)) {
					listaClientes.add(clienteNuevo);
				} else {
					listaClientes = new ArrayList<ClienteDTO>();
					listaClientes.add(clienteNuevo);
				}
				initSelectClave();
				cambiaClaveSelected();
				cambiaNombreSelected();
				RequestContext.getCurrentInstance().execute("nuevoDialog.hide()");
				super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO, Constantes.NUEVO_REGISTRO_EXITOSO);
				addBitacora(Constantes.ACCION_NUEVO_REGISTRO, Constantes.ACCION_NUEVO_REGISTRO_EXITOSO_MENSAJE+":Cliente "+pk+":");
			} catch (FachadaException e1) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_NUEVO_REGISTRO);
				addBitacora(Constantes.ACCION_NUEVO_REGISTRO, Constantes.ACCION_NUEVO_REGISTRO_FALLIDO_MENSAJE+":Cliente:");
				return;
			}
			clienteNuevo = new ClienteDTO();
		}else{
			super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING, Constantes.WARNING_NECESITAS_LLENAR_CAMPOS_REQUERIDOS);
		}
	}

	public boolean validaDatos() {
		if (clienteNuevo.getClave() == null
				|| clienteNuevo.getClave().isEmpty()) {
			return false;
		} else if (clienteNuevo.getNombre() == null
				|| clienteNuevo.getNombre().isEmpty()) {
			return false;
		} else if (clienteNuevo.getResponsable() == null
				|| clienteNuevo.getResponsable().isEmpty()) {
			return false;
		} else if (clienteNuevo.getTelefono() == null
				|| clienteNuevo.getTelefono().isEmpty()) {
			return false;
		} else if (clienteNuevo.getEmail() == null
				|| clienteNuevo.getTelefono().isEmpty()) {
			return false;
		} else if (clienteNuevo.getEmail() != null
				&& !clienteNuevo.getTelefono().isEmpty()) {
			boolean b = EmailValidator.getInstance().validate(
					clienteNuevo.getEmail());
			if (!b) {
				super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING,
						Constantes.WARNING_FORMATO_EMAIL);
				return false;
			}
		}
		return true;
	}

	public void saveClienteCancel(ActionEvent e) {
		clienteNuevo = new ClienteDTO();
	}

	public void cambiaClaveSelected() {
		listaSelectNombres = new ArrayList<SelectItem>();
		if (claveSelected != null && !claveSelected.isEmpty()) {
			try {
				List<ClienteDTO> listaClientesNombre = fachadaCliente
						.getClientesByClave(claveSelected);
				for (ClienteDTO clienteDTO : listaClientesNombre) {
					SelectItem item = new SelectItem(clienteDTO.getNombre(),
							clienteDTO.getNombre());
					listaSelectNombres.add(item);
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	public void cambiaNombreSelected() {
		listaSelectReponsable = new ArrayList<SelectItem>();
		if (nombreSelected != null && !nombreSelected.isEmpty()) {
			try {
				List<ClienteDTO> listaClientesResponsable = fachadaCliente
						.getClientesByClaveAndNombre(claveSelected,
								nombreSelected);
				for (ClienteDTO clienteDTO : listaClientesResponsable) {
					SelectItem item = new SelectItem(
							clienteDTO.getResponsable(),
							clienteDTO.getResponsable());
					listaSelectReponsable.add(item);
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	public void limpiarFiltrado(ActionEvent e) {
		listaClientes = null;
		listaSelectClientes = null;
		listaSelectClave = null;
		initListaClientes();
		initSelectClientes();
		initSelectClave();
		cambiaClaveSelected();
		cambiaNombreSelected();
	}

	public void buscarFiltrado(ActionEvent e) {
		try {
			if (responsableSelected != null && !responsableSelected.isEmpty()) {
				listaClientes = fachadaCliente.getClientesByClaveAndNombre(
						claveSelected, nombreSelected);
			} else if (nombreSelected != null && !nombreSelected.isEmpty()) {
				listaClientes = fachadaCliente
						.getClientesByClave(claveSelected);
			} else {
				listaClientes = fachadaCliente.getAll();
			}
		} catch (FachadaException ex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}

	}

	// --------------- GETTERS & SETTERS ---------------//

	/**
	 * @return the clienteSelected
	 */
	public ClienteDTO getClienteSelected() {
		return clienteSelected;
	}

	/**
	 * @param clienteSelected
	 *            the clienteSelected to set
	 */
	public void setClienteSelected(ClienteDTO clienteSelected) {
		this.clienteSelected = clienteSelected;
	}

	/**
	 * @return the clienteNuevo
	 */
	public ClienteDTO getClienteNuevo() {
		return clienteNuevo;
	}

	/**
	 * @param clienteNuevo
	 *            the clienteNuevo to set
	 */
	public void setClienteNuevo(ClienteDTO clienteNuevo) {
		this.clienteNuevo = clienteNuevo;
	}

	/**
	 * @return the tipoClienteSelected
	 */
	public TipoClienteDTO getTipoClienteSelected() {
		return tipoClienteSelected;
	}

	/**
	 * @param tipoClienteSelected
	 *            the tipoClienteSelected to set
	 */
	public void setTipoClienteSelected(TipoClienteDTO tipoClienteSelected) {
		this.tipoClienteSelected = tipoClienteSelected;
	}

	/**
	 * @return the listaClientes
	 */
	public List<ClienteDTO> getListaClientes() {
		initListaClientes();
		return listaClientes;
	}

	/**
	 * @param listaClientes
	 *            the listaClientes to set
	 */
	public void setListaClientes(List<ClienteDTO> listaClientes) {
		this.listaClientes = listaClientes;
	}

	/**
	 * @return the listaTipoClientes
	 */
	public List<TipoClienteDTO> getListaTipoClientes() {
		initListaTiposClientes();
		return listaTipoClientes;
	}

	/**
	 * @param listaTipoClientes
	 *            the listaTipoClientes to set
	 */
	public void setListaTipoClientes(List<TipoClienteDTO> listaTipoClientes) {
		this.listaTipoClientes = listaTipoClientes;
	}

	/**
	 * @return the listaSelectTiposClientes
	 */
	public List<SelectItem> getListaSelectTiposClientes() {
		initSelectTiposClientes();
		return listaSelectTiposClientes;
	}

	/**
	 * @param listaSelectTiposClientes
	 *            the listaSelectTiposClientes to set
	 */
	public void setListaSelectTiposClientes(
			List<SelectItem> listaSelectTiposClientes) {
		this.listaSelectTiposClientes = listaSelectTiposClientes;
	}

	/**
	 * @return the idTipoCliente
	 */
	public Integer getIdTipoCliente() {
		return idTipoCliente;
	}

	/**
	 * @param idTipoCliente
	 *            the idTipoCliente to set
	 */
	public void setIdTipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	/**
	 * @return the idTipoClienteFilter
	 */
	public Integer getIdTipoClienteFilter() {
		return idTipoClienteFilter;
	}

	/**
	 * @param idTipoClienteFilter
	 *            the idTipoClienteFilter to set
	 */
	public void setIdTipoClienteFilter(Integer idTipoClienteFilter) {
		this.idTipoClienteFilter = idTipoClienteFilter;
	}

	/**
	 * @return the idClienteFilter
	 */
	public Integer getIdClienteFilter() {
		return idClienteFilter;
	}

	/**
	 * @param idClienteFilter
	 *            the idClienteFilter to set
	 */
	public void setIdClienteFilter(Integer idClienteFilter) {
		this.idClienteFilter = idClienteFilter;
	}

	/**
	 * @return the selectedClienteNombre
	 */
	public String getSelectedClienteNombre() {
		return selectedClienteNombre;
	}

	/**
	 * @param selectedClienteNombre
	 *            the selectedClienteNombre to set
	 */
	public void setSelectedClienteNombre(String selectedClienteNombre) {
		this.selectedClienteNombre = selectedClienteNombre;
	}

	/**
	 * @return the selectedClienteClave
	 */
	public String getSelectedClienteClave() {
		return selectedClienteClave;
	}

	/**
	 * @param selectedClienteClave
	 *            the selectedClienteClave to set
	 */
	public void setSelectedClienteClave(String selectedClienteClave) {
		this.selectedClienteClave = selectedClienteClave;
	}

	/**
	 * @return the selectedClienteResponsable
	 */
	public String getSelectedClienteResponsable() {
		return selectedClienteResponsable;
	}

	/**
	 * @param selectedClienteResponsable
	 *            the selectedClienteResponsable to set
	 */
	public void setSelectedClienteResponsable(String selectedClienteResponsable) {
		this.selectedClienteResponsable = selectedClienteResponsable;
	}

	/**
	 * @param fachadaCliente
	 *            the fachadaCliente to set
	 */
	public void setFachadaCliente(ClienteFachada fachadaCliente) {
		this.fachadaCliente = fachadaCliente;
	}

	/**
	 * @return the listaSelectClientes
	 */
	public List<SelectItem> getListaSelectClientes() {
		initSelectClientes();
		return listaSelectClientes;
	}

	/**
	 * @param listaSelectClientes
	 *            the listaSelectClientes to set
	 */
	public void setListaSelectClientes(List<SelectItem> listaSelectClientes) {
		this.listaSelectClientes = listaSelectClientes;
	}

	/**
	 * @return the idCliente
	 */
	public Integer getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente
	 *            the idCliente to set
	 */
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return the selecteClienteTelefono
	 */
	public String getSelectedClienteTelefono() {
		return selectedClienteTelefono;
	}

	/**
	 * @param selecteClienteTelefono
	 *            the selecteClienteTelefono to set
	 */
	public void setSelectedClienteTelefono(String selecteClienteTelefono) {
		this.selectedClienteTelefono = selecteClienteTelefono;
	}

	/**
	 * @return the selectedClienteEmail
	 */
	public String getSelectedClienteEmail() {
		return selectedClienteEmail;
	}

	/**
	 * @param selectedClienteEmail
	 *            the selectedClienteEmail to set
	 */
	public void setSelectedClienteEmail(String selectedClienteEmail) {
		this.selectedClienteEmail = selectedClienteEmail;
	}

	/**
	 * @return the selectedTipoCliente
	 */
	public Integer getSelectedTipoCliente() {
		return selectedTipoCliente;
	}

	/**
	 * @param selectedTipoCliente
	 *            the selectedTipoCliente to set
	 */
	public void setSelectedTipoCliente(Integer selectedTipoCliente) {
		this.selectedTipoCliente = selectedTipoCliente;
	}

	/**
	 * @return the selectedIdCliente
	 */
	public Integer getSelectedIdCliente() {
		return selectedIdCliente;
	}

	/**
	 * @param selectedIdCliente
	 *            the selectedIdCliente to set
	 */
	public void setSelectedIdCliente(Integer selectedIdCliente) {
		this.selectedIdCliente = selectedIdCliente;
	}

	/**
	 * @return the claveSelected
	 */
	public String getClaveSelected() {
		return claveSelected;
	}

	/**
	 * @param claveSelected
	 *            the claveSelected to set
	 */
	public void setClaveSelected(String claveSelected) {
		this.claveSelected = claveSelected;
	}

	/**
	 * @return the nombreSelected
	 */
	public String getNombreSelected() {
		return nombreSelected;
	}

	/**
	 * @param nombreSelected
	 *            the nombreSelected to set
	 */
	public void setNombreSelected(String nombreSelected) {
		this.nombreSelected = nombreSelected;
	}

	/**
	 * @return the responsableSelected
	 */
	public String getResponsableSelected() {
		return responsableSelected;
	}

	/**
	 * @param responsableSelected
	 *            the responsableSelected to set
	 */
	public void setResponsableSelected(String responsableSelected) {
		this.responsableSelected = responsableSelected;
	}

	public List<SelectItem> getListaSelectNombres() {
		return listaSelectNombres;
	}

	public void setListaSelectNombres(List<SelectItem> listaSelectNombres) {
		this.listaSelectNombres = listaSelectNombres;
	}

	public List<SelectItem> getListaSelectReponsable() {
		return listaSelectReponsable;
	}

	public void setListaSelectReponsable(List<SelectItem> listaSelectReponsable) {
		this.listaSelectReponsable = listaSelectReponsable;
	}

	public List<SelectItem> getListaSelectClave() {
		initSelectClave();
		return listaSelectClave;
	}

	public void setListaSelectClave(List<SelectItem> listaSelectClave) {
		this.listaSelectClave = listaSelectClave;
	}

	public List<ClienteDTO> getFilteredClientes() {
		return filteredClientes;
	}

	public void setFilteredClientes(List<ClienteDTO> filteredClientes) {
		this.filteredClientes = filteredClientes;
	}

	@Override
	String getModulo() {
		return modulo;
	}

}
