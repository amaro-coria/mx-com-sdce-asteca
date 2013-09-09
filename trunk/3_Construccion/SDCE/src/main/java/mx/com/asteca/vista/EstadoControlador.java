package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.PaisDTO;
import mx.com.asteca.fachada.EstadoFachada;
import mx.com.asteca.fachada.FachadaException;

import org.apache.commons.collections.CollectionUtils;
/* La clase es serializable porque se guarda en la vista */
/**
 * Clase para controlar la pantalla de Estados.
 * @author JAMARO
 * @version 1.0
 * @since 1.0
 * 
 */
@ManagedBean(name = Constantes.BEAN_ESTADOS)
@ViewScoped
public class EstadoControlador extends BaseController implements Serializable {

	/**
	 * Declara ID serializable
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Tiene que ser transient para no generar excepcion de no serializacion
	 * */
	@ManagedProperty("#{estadoFachadaImpl}")
	private transient EstadoFachada fachadaEstado;
	private EstadoDTO estadoSelected;
	private EstadoDTO estadoNuevo;
	private PaisDTO paisSelected;

	private List<EstadoDTO> listaEstados;
	private List<PaisDTO> listaPaises;
	private List<SelectItem> listaSelectPaises;
	private List<SelectItem> listaSelectEdos;
	private String paisId;
	private int idPais;
	private int idPaisNuevo;
	private int idPaisFilter;
	private int idEdoFilter;
	private boolean nuevoEstadoActivo;
	private boolean selectedEstadoActivo;
	private String selectedEstadoNombre;
	private String selectedEstadoClave;
	private List<EstadoDTO> filteredEstados;

	/**
	 * Constructor default
	 */
	public EstadoControlador() {
		estadoNuevo = new EstadoDTO();
		estadoSelected = new EstadoDTO();
		paisId = "";
		paisSelected = new PaisDTO();
	}


	/**
	 * Inicializa la lista de paises de <code>SelectItem</code>
	 * {@link SelectItem} 
	 */
	private void initListaSelectPaises() {
		if (CollectionUtils.isEmpty(listaSelectPaises)) {
			listaSelectPaises = new ArrayList<SelectItem>();
			for (PaisDTO dto : getListaPaises()) {
				SelectItem item = new SelectItem(dto.getIdPais(),
						dto.getNombrePais());
				listaSelectPaises.add(item);
			}
		}
	}

	/**
	 * Inicializa la lista de estados de <code>SelectItem</code>
	 * {@link SelectItem} 
	 */
	private void initListaSelectEdos() {
		if (CollectionUtils.isEmpty(listaSelectEdos)) {
			listaSelectEdos = new ArrayList<SelectItem>();
			for (EstadoDTO dto : getListaEstados()) {
				SelectItem item = new SelectItem(dto.getIdEstado(),
						dto.getNombre());
				listaSelectEdos.add(item);
			}
		}
	}

	/**
	 * Inicializa la lista de estados de DTOs
	 */
	private void initListaEstados() {
		if (CollectionUtils.isEmpty(listaEstados)) {
			try {
				listaEstados = fachadaEstado.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	/**
	 * Inicializa la lista de paises de DTOs
	 */
	private void initListaPaises() {
		if (CollectionUtils.isEmpty(listaPaises)) {
			try {
				listaPaises = fachadaEstado.getPaises();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	/**
	 * Cambia el valor de la lista de estados dependiendo del pais seleccionado
	 */
	public void cambiaPaisSelect() {
		if (idPaisFilter != 0) {
			listaSelectEdos = new ArrayList<SelectItem>();
			List<EstadoDTO> listaDTOs;
			try {
				listaDTOs = fachadaEstado.getFromPais(idPaisFilter);
				for (EstadoDTO dto : listaDTOs) {
					SelectItem item = new SelectItem(dto.getIdEstado(), dto.getNombre());
					listaSelectEdos.add(item);
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		} else {
			listaSelectEdos = new ArrayList<SelectItem>();
		}
	}
	
	/**
	 * Limpia los valores de busqueda
	 * @param e
	 */
	public void limpiarFiltrado(ActionEvent e){
		listaEstados = null;
		initListaEstados();
		listaSelectEdos = null;
		initListaSelectEdos();
	}

	/**
	 * Realiza la busqueda y actualiza valores para el datatable
	 * @param e
	 */
	public void buscarFiltrado(ActionEvent e){
		if(idPaisFilter == 0 && idEdoFilter == 0){
			initListaSelectEdos();
		}else if(idPaisFilter != 0 && idEdoFilter ==0){
			cambiaPaisSelect();
		}else if(idPaisFilter != 0 && idEdoFilter != 0){
			try {
				EstadoDTO selected = fachadaEstado.findByPK(idEdoFilter);
				listaEstados = new ArrayList<EstadoDTO>();
				listaEstados.add(selected);
			} catch (FachadaException e1) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	/**
	 * Cancela el borrado del estado seleccionado. 
	 * @param e
	 */
	public void cancelDeleteEstado(ActionEvent e){
		setSelectedEstadoClave("");
		setSelectedEstadoNombre("");
	}
	
	/**
	 * Borra el estado seleccionado
	 * @param e
	 */
	public void deleteEstado(ActionEvent e) {
		try {
			fachadaEstado.remove(estadoSelected);
			listaEstados.remove(estadoSelected);
			cambiaPaisSelect();
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_DELETE_REGISTRO);
			return;
		}
		setSelectedEstadoClave("");
		setSelectedEstadoNombre("");
		super.addInfoMessage(Constantes.DELETE_REGISTRO_EXITOSO);
	}

	/**
	 * Actualiza el estado seleccionado
	 * @param e
	 */
	public void updateEstado(ActionEvent e) {
		if (idPais == 0) {
			super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING, Constantes.ERROR_NECESITAS_SELECCIONAR_UN_PAIS);
			return;
		}
		
		estadoSelected.setIdPais(idPais);
		estadoSelected.setActivo(selectedEstadoActivo == true ? (short) 1
				: (short) 0);
		if(selectedEstadoNombre != null && !selectedEstadoNombre.isEmpty()){
			estadoSelected.setNombre(selectedEstadoNombre);
		}
		if(selectedEstadoClave != null && !selectedEstadoClave.isEmpty()){
			estadoSelected.setClave(selectedEstadoClave);
		}
		try {
			fachadaEstado.update(estadoSelected);
			int indexListFilter = listaEstados.indexOf(estadoSelected);
			if(indexListFilter > 0){
				listaEstados.set(indexListFilter, estadoSelected);
			}
			cambiaPaisSelect();
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_UPDATE_REGISTRO);
			return;
		}
		super.addInfoMessage(Constantes.UPDATE_REGISTRO_EXITOSO);
	}

	/**
	 * Guarda el nuevo estado en BD
	 * @param e
	 */
	public void saveEstado(ActionEvent e) {
		if (idPaisNuevo == 0) {
			super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING, Constantes.ERROR_NECESITAS_SELECCIONAR_UN_PAIS);
			return;
		}
		estadoNuevo.setIdPais(idPaisNuevo);
		estadoNuevo
				.setActivo(nuevoEstadoActivo == true ? (short) 1 : (short) 0);
		try {
			fachadaEstado.save(estadoNuevo);
			listaEstados.add(estadoNuevo);
			cambiaPaisSelect();
			//refreshEstados();
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NUEVO_REGISTRO);
			return;
		}
		estadoNuevo = new EstadoDTO();
		super.addInfoMessage(Constantes.NUEVO_REGISTRO_EXITOSO);
	}
	
	public void saveEstadoCancel(ActionEvent e){
		estadoNuevo = new EstadoDTO();
	}	
	

	// --------------------- GETTERS & SETTERS  ------------------------------- //
	
	
	/**
	 * @return the listaEstados
	 */
	public List<EstadoDTO> getListaEstados() {
		initListaEstados();
		return listaEstados;
	}

	/**
	 * @param listaEstados
	 *            the listaEstados to set
	 */
	public void setListaEstados(List<EstadoDTO> listaEstados) {
		this.listaEstados = listaEstados;
	}

	/**
	 * @param fachadaEstado
	 *            the fachadaEstado to set
	 */
	public void setFachadaEstado(EstadoFachada fachadaEstado) {
		this.fachadaEstado = fachadaEstado;
	}

	/**
	 * @return the estadoSelected
	 */
	public EstadoDTO getEstadoSelected() {
		return estadoSelected;
	}

	/**
	 * @param estadoSelected
	 *            the estadoSelected to set
	 */
	public void setEstadoSelected(EstadoDTO estadoSelected) {		
		this.estadoSelected = estadoSelected;
		if(estadoSelected == null){
			return;
		}
		if(estadoSelected.getActivo() == 0){
			setSelectedEstadoActivo(false);
		}else{
			setSelectedEstadoActivo(true);
		}
	}

	/**
	 * @return the listaPaises
	 */
	public List<PaisDTO> getListaPaises() {
		initListaPaises();
		return listaPaises;
	}

	/**
	 * @param listaPaises
	 *            the listaPaises to set
	 */
	public void setListaPaises(List<PaisDTO> listaPaises) {
		this.listaPaises = listaPaises;
	}

	/**
	 * @return the paisSelected
	 */
	public PaisDTO getPaisSelected() {
		return paisSelected;
	}

	/**
	 * @param paisSelected
	 *            the paisSelected to set
	 */
	public void setPaisSelected(PaisDTO paisSelected) {
		this.paisSelected = paisSelected;
	}

	/**
	 * @return the estadoNuevo
	 */
	public EstadoDTO getEstadoNuevo() {
		return estadoNuevo;
	}

	/**
	 * @param estadoNuevo
	 *            the estadoNuevo to set
	 */
	public void setEstadoNuevo(EstadoDTO estadoNuevo) {
		this.estadoNuevo = estadoNuevo;
	}
	
	/**
	 * @return
	 */
	public String getPaisId() {
		return paisId;
	}

	/**
	 * @param paisId
	 */
	public void setPaisId(String paisId) {
		this.paisId = paisId;
	}

	/**
	 * @return
	 */
	public boolean isNuevoEstadoActivo() {
		return nuevoEstadoActivo;
	}

	/**
	 * @param nuevoEstadoActivo
	 */
	public void setNuevoEstadoActivo(boolean nuevoEstadoActivo) {
		this.nuevoEstadoActivo = nuevoEstadoActivo;
	}

	/**
	 * @return
	 */
	public boolean isSelectedEstadoActivo() {
		return selectedEstadoActivo;
	}

	/**
	 * @param selectedEstadoActivo
	 */
	public void setSelectedEstadoActivo(boolean selectedEstadoActivo) {
		this.selectedEstadoActivo = selectedEstadoActivo;
	}

	/**
	 * @return
	 */
	public String getSelectedEstadoNombre() {
		return selectedEstadoNombre;
	}

	/**
	 * @param selectedEstadoNombre
	 */
	public void setSelectedEstadoNombre(String selectedEstadoNombre) {
		this.selectedEstadoNombre = selectedEstadoNombre;
	}

	/**
	 * @return
	 */
	public List<SelectItem> getListaSelectPaises() {
		initListaSelectPaises();
		return listaSelectPaises;
	}

	/**
	 * @param listaSelectPaises
	 */
	public void setListaSelectPaises(List<SelectItem> listaSelectPaises) {
		this.listaSelectPaises = listaSelectPaises;
	}

	/**
	 * @return
	 */
	public int getIdPais() {
		return idPais;
	}

	/**
	 * @param idPais
	 */
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	/**
	 * @return
	 */
	public int getIdPaisFilter() {
		return idPaisFilter;
	}

	/**
	 * @param idPaisFilter
	 */
	public void setIdPaisFilter(int idPaisFilter) {
		this.idPaisFilter = idPaisFilter;
	}

	/**
	 * @return
	 */
	public int getIdEdoFilter() {
		return idEdoFilter;
	}

	/**
	 * @param idEdoFilter
	 */
	public void setIdEdoFilter(int idEdoFilter) {
		this.idEdoFilter = idEdoFilter;
	}

	/**
	 * @return
	 */
	public List<SelectItem> getListaSelectEdos() {
		return listaSelectEdos;
	}

	/**
	 * @param listaSelectEdos
	 */
	public void setListaSelectEdos(List<SelectItem> listaSelectEdos) {
		this.listaSelectEdos = listaSelectEdos;
	}

	/**
	 * @return
	 */
	public String getSelectedEstadoClave() {
		return selectedEstadoClave;
	}

	/**
	 * @param selectedEstadoClave
	 */
	public void setSelectedEstadoClave(String selectedEstadoClave) {
		this.selectedEstadoClave = selectedEstadoClave;
	}

	public int getIdPaisNuevo() {
		return idPaisNuevo;
	}

	public void setIdPaisNuevo(int idPaisNuevo) {
		this.idPaisNuevo = idPaisNuevo;
	}

	public List<EstadoDTO> getFilteredEstados() {
		return filteredEstados;
	}

	public void setFilteredEstados(List<EstadoDTO> filteredEstados) {
		this.filteredEstados = filteredEstados;
	}

}
