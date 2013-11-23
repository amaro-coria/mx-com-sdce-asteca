package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.comun.dto.PaisDTO;
import mx.com.asteca.comun.dto.PermisosBooleanDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.MunicipioFachada;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SelectableDataModel;

@ManagedBean(name = Constantes.BEAN_MUNICIPIOS)
@ViewScoped
public class MunicipioControlador extends BaseController implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String modulo = Constantes.MODULO_MUNICIPIO;

	@ManagedProperty("#{municipioFachadaImpl}")
	private transient MunicipioFachada fachadaMunicipio;

	private MunicipioDTO municipioSelected;
	private MunicipioDTO municipioNuevo;
	private PaisDTO paisSelected;
	private int paisSelectedId;
	private int estadoSelectedId;
	private int municipioSelectedId;
	private int idPaisFilter;
	private int idEdoFilter;
	private int idMunicipioFilter;
	private int idPaisNuevo;
	private int idEdoNuevo;
	private int idPaisEdit;
	private int idEdoEdit;
	private boolean selectedMunicipioActivo;
	private boolean nuevoMunicipioActivo;
	private EstadoDTO estadoBuscarSelected;
	private MunicipioDTO municipioBuscarSelected;
	private EstadoDTO estadoSelected;
	private String activoNuevo;
	private String activoEditar;
	private List<EstadoDTO> listaEstados;
	private List<PaisDTO> listaPaises;
	private List<MunicipioDTO> listaMunicipios;
	private List<SelectItem> listaSelectPaises;
	private List<SelectItem> listaSelectEstados;
	private List<SelectItem> listaSelectMunicipios;
	private List<MunicipioDTO> listaMunicipioFiltered;
	private String selectedMunicipioNombre;
	private String selectedMunicipioClave;

	public MunicipioControlador() {
		municipioNuevo = new MunicipioDTO();
		municipioSelected = new MunicipioDTO();
		estadoSelected = new EstadoDTO();
		paisSelected = new PaisDTO();
		estadoBuscarSelected = new EstadoDTO();
		municipioBuscarSelected = new MunicipioDTO();
	}

	private PermisosBooleanDTO permisos;

	@PostConstruct
	public void populate() {
		setPermisos(super.stablishSessionPermissions());
	}

	/**
	 * @return the permisos
	 */
	public PermisosBooleanDTO getPermisos() {
		return permisos;
	}

	/**
	 * @param permisos
	 *            the permisos to set
	 */
	public void setPermisos(PermisosBooleanDTO permisos) {
		this.permisos = permisos;
		super.setAlta(permisos.isAlta());
		super.setBorrar(permisos.isBorrar());
		super.setCambios(permisos.isEdicion());
		super.setConsulta(permisos.isConsulta());
		super.setImpresion(permisos.isImpresion());
	}

	private void initListaSelectPaises() {
		if (CollectionUtils.isEmpty(listaSelectPaises)) {
			listaSelectPaises = new ArrayList<SelectItem>();
			for (PaisDTO pais : getListaPaises()) {
				SelectItem select = new SelectItem(pais.getIdPais(),
						pais.getNombrePais());
				listaSelectPaises.add(select);
			}
		}
	}

	private void initListaSelectEstados() {
		if (CollectionUtils.isEmpty(listaSelectEstados)) {
			listaSelectEstados = new ArrayList<SelectItem>();
			for (EstadoDTO estado : getListaEstados()) {
				SelectItem select = new SelectItem(estado.getIdEstado(),
						estado.getNombre());
				listaSelectEstados.add(select);
			}
		}
	}

	private void initListaSelectMunicipios() {
		if (CollectionUtils.isEmpty(listaSelectMunicipios)) {
			listaSelectMunicipios = new ArrayList<SelectItem>();
			for (MunicipioDTO municipio : getListaMunicipios()) {
				SelectItem select = new SelectItem(municipio.getIdMunicipio(),
						municipio.getNombre());
				listaSelectMunicipios.add(select);
			}
		}
	}

	private void initListaMunicipios() {
		if (CollectionUtils.isEmpty(listaMunicipios)) {
			try {
				listaMunicipios = fachadaMunicipio.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initListaEstados() {
		if (CollectionUtils.isEmpty(listaEstados)) {
			try {
				listaEstados = fachadaMunicipio.getListaEstados();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initListaPaises() {
		if (CollectionUtils.isEmpty(listaPaises)) {
			try {
				listaPaises = fachadaMunicipio.getListaPaises();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	/**
	 * Cambia el valor de la lista de estados dependiendo del pais seleccionado
	 */
	public void cambiaPaisSelect() {
		if (idPaisFilter != 0) {
			listaSelectEstados = new ArrayList<SelectItem>();
			List<EstadoDTO> listaDTOs;
			try {
				listaDTOs = fachadaMunicipio.getFromPais(idPaisFilter);
				for (EstadoDTO dto : listaDTOs) {
					SelectItem item = new SelectItem(dto.getIdEstado(),
							dto.getNombre());
					listaSelectEstados.add(item);
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		} else {
			listaSelectEstados = new ArrayList<SelectItem>();
		}
	}

	/**
	 * Cambia el valor de la lista de municipios dependiendo del edo
	 * seleccionado
	 */
	public void cambiaEdoSelect() {
		if (idPaisFilter != 0) {
			if (idEdoFilter != 0) {
				listaSelectMunicipios = new ArrayList<SelectItem>();
				List<MunicipioDTO> listaDTOs;
				try {
					listaDTOs = fachadaMunicipio.getFromEstado(idEdoFilter);
					for (MunicipioDTO dto : listaDTOs) {
						SelectItem item = new SelectItem(dto.getIdMunicipio(),
								dto.getNombre());
						listaSelectMunicipios.add(item);
					}
				} catch (FachadaException ex) {
					super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
							Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
				}
			}
		} else {
			listaSelectMunicipios = new ArrayList<SelectItem>();
		}
	}

	// HASTA AQUI

	/**
	 * Limpia los valores de busqueda
	 * 
	 * @param e
	 */
	public void limpiarFiltrado(ActionEvent e) {
		listaEstados = null;
		initListaEstados();
		listaSelectEstados = null;
		initListaSelectEstados();
	}

	/**
	 * Realiza la busqueda y actualiza valores para el datatable
	 * 
	 * @param e
	 */
	public void buscarFiltrado(ActionEvent e) {
		if (idPaisFilter == 0 && idEdoFilter == 0) {
			initListaSelectEstados();
		} else if (idPaisFilter != 0 && idEdoFilter == 0) {
			cambiaPaisSelect();
		} else if (idPaisFilter != 0 && idEdoFilter != 0
				&& idMunicipioFilter == 0) {
			cambiaEdoSelect();
		} else if (idPaisFilter != 0 && idEdoFilter != 0
				&& idMunicipioFilter != 0) {
			try {
				MunicipioDTO municipio = fachadaMunicipio.getFromMunicipioEdo(
						idEdoFilter, idMunicipioFilter);
				listaMunicipios = new ArrayList<MunicipioDTO>();
				listaMunicipios.add(municipio);
			} catch (FachadaException e1) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	/**
	 * Cancela el borrado del municipio seleccionado.
	 * 
	 * @param e
	 */
	public void cancelDeleteMunicipio(ActionEvent e) {
		setSelectedMunicipioClave("");
		setSelectedMunicipioNombre("");
	}

	/**
	 * Borra el estado seleccionado
	 * 
	 * @param e
	 */
	public void deleteMunicipio(ActionEvent e) {
		try {
			fachadaMunicipio.remove(municipioSelected);
			listaMunicipios.remove(municipioSelected);
			cambiaPaisSelect();
			cambiaEdoSelect();
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_DELETE_REGISTRO);
			addBitacora(Constantes.ACCION_DELETE_REGISTRO,
					Constantes.ACCION_DELETE_REGISTRO_FALLIDO_MENSAJE
							+ ":Municipio" + municipioSelected.getIdMunicipio()
							+ ":");
			return;
		}
		setSelectedMunicipioClave("");
		setSelectedMunicipioNombre("");
		super.addInfoMessage(Constantes.DELETE_REGISTRO_EXITOSO);
		addBitacora(Constantes.ACCION_DELETE_REGISTRO,
				Constantes.ACCION_DELETE_REGISTRO_EXITOSO_MENSAJE
						+ ":Municipio" + municipioSelected.getIdMunicipio()
						+ ":");
	}

	/**
	 * Actualiza el estado seleccionado
	 * 
	 * @param e
	 */
	public void updateMunicipio(ActionEvent e) {
		if (idPaisEdit == 0) {
			super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING,
					Constantes.ERROR_NECESITAS_SELECCIONAR_UN_PAIS);
			return;
		}
		if (idEdoEdit == 0) {
			super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING,
					Constantes.ERROR_NECESITAS_SELECCIONAR_UN_EDO);
			return;
		}

		municipioSelected.setIdPais(idPaisEdit);
		municipioSelected.setActivo(selectedMunicipioActivo == true ? (short) 1
				: (short) 0);
		if (selectedMunicipioNombre != null
				&& !selectedMunicipioNombre.isEmpty()) {
			estadoSelected.setNombre(selectedMunicipioNombre);
		}
		if (selectedMunicipioClave != null && !selectedMunicipioClave.isEmpty()) {
			estadoSelected.setClave(selectedMunicipioClave);
		}
		try {
			fachadaMunicipio.update(municipioSelected);
			int indexListFilter = listaMunicipios.indexOf(municipioSelected);
			if (indexListFilter > 0) {
				listaMunicipios.set(indexListFilter, municipioSelected);
			}
			cambiaPaisSelect();
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_UPDATE_REGISTRO);
			addBitacora(Constantes.ACCION_UPDATE_REGISTRO,
					Constantes.ACCION_UPDATE_REGISTRO_FALLIDO_MENSAJE
							+ ":Municipio:");
			return;
		}
		super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO,
				Constantes.UPDATE_REGISTRO_EXITOSO);
		addBitacora(Constantes.ACCION_UPDATE_REGISTRO,
				Constantes.ACCION_UPDATE_REGISTRO_EXITOSO_MENSAJE
						+ ":Municipio " + municipioSelected.getIdMunicipio()
						+ ":");
	}

	/**
	 * Guarda el nuevo estado en BD
	 * 
	 * @param e
	 */
	public void saveEstado(ActionEvent e) {
		if (idPaisNuevo == 0) {
			super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING,
					Constantes.ERROR_NECESITAS_SELECCIONAR_UN_PAIS);
			return;
		}

		if (idEdoNuevo == 0) {
			super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING,
					Constantes.ERROR_NECESITAS_SELECCIONAR_UN_EDO);
			return;
		}
		if (!validate()) {
			super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING,
					Constantes.WARNING_NECESITAS_LLENAR_CAMPOS_REQUERIDOS);
			return;
		}
		municipioNuevo.setIdPais(idPaisNuevo);
		municipioNuevo.setActivo(nuevoMunicipioActivo == true ? (short) 1
				: (short) 0);
		try {
			int pk = fachadaMunicipio.save(municipioNuevo);
			municipioNuevo.setIdMunicipio(pk);
			listaMunicipios.add(municipioNuevo);
			cambiaPaisSelect();
			super.addInfoMessage(Constantes.NUEVO_REGISTRO_EXITOSO);
			RequestContext.getCurrentInstance().execute("nuevoDialog.hide()");
			addBitacora(Constantes.ACCION_NUEVO_REGISTRO,
					Constantes.ACCION_NUEVO_REGISTRO_EXITOSO_MENSAJE
							+ ":Municipio " + municipioNuevo.getIdMunicipio()
							+ ":");
			// refreshEstados();
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_NUEVO_REGISTRO);
			addBitacora(Constantes.ACCION_NUEVO_REGISTRO,
					Constantes.ACCION_NUEVO_REGISTRO_FALLIDO_MENSAJE
							+ ":Municipio:");
			return;
		}
		municipioNuevo = new MunicipioDTO();
	}

	public boolean validate() {
		if (municipioNuevo.getNombre() == null
				|| municipioNuevo.getNombre().isEmpty()) {
			return false;
		}
		return true;
	}

	public void saveMunicipioCancel(ActionEvent e) {
		municipioNuevo = new MunicipioDTO();
	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("OBJECT:::::::::" + event.getObject());
		System.out.println("Source::::::::::::" + event.getSource());
	}

	// --------------------- GETTERS & SETTERS -------------------------------
	// //

	/**
	 * @return the municipioSelected
	 */
	public MunicipioDTO getMunicipioSelected() {
		return municipioSelected;
	}

	/**
	 * @param municipioSelected
	 *            the municipioSelected to set
	 */
	public void setMunicipioSelected(MunicipioDTO municipioSelected) {
		this.municipioSelected = municipioSelected;
	}

	/**
	 * @return the municipioNuevo
	 */
	public MunicipioDTO getMunicipioNuevo() {
		return municipioNuevo;
	}

	/**
	 * @param municipioNuevo
	 *            the municipioNuevo to set
	 */
	public void setMunicipioNuevo(MunicipioDTO municipioNuevo) {
		this.municipioNuevo = municipioNuevo;
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
	 * @return the listaMunicipios
	 */
	public List<MunicipioDTO> getListaMunicipios() {
		initListaMunicipios();
		return listaMunicipios;
	}

	/**
	 * @param listaMunicipios
	 *            the listaMunicipios to set
	 */
	public void setListaMunicipios(List<MunicipioDTO> listaMunicipios) {
		this.listaMunicipios = listaMunicipios;
	}

	/**
	 * @param fachadaMunicipio
	 *            the fachadaMunicipio to set
	 */
	public void setFachadaMunicipio(MunicipioFachada fachadaMunicipio) {
		this.fachadaMunicipio = fachadaMunicipio;
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
	}

	/**
	 * @return the activoNuevo
	 */
	public String getActivoNuevo() {
		return activoNuevo;
	}

	/**
	 * @param activoNuevo
	 *            the activoNuevo to set
	 */
	public void setActivoNuevo(String activoNuevo) {
		this.activoNuevo = activoNuevo;
	}

	/**
	 * @return the activoEditar
	 */
	public String getActivoEditar() {
		return activoEditar;
	}

	/**
	 * @param activoEditar
	 *            the activoEditar to set
	 */
	public void setActivoEditar(String activoEditar) {
		this.activoEditar = activoEditar;
	}

	/**
	 * @return the estadoBuscarSelected
	 */
	public EstadoDTO getEstadoBuscarSelected() {
		return estadoBuscarSelected;
	}

	/**
	 * @param estadoBuscarSelected
	 *            the estadoBuscarSelected to set
	 */
	public void setEstadoBuscarSelected(EstadoDTO estadoBuscarSelected) {
		this.estadoBuscarSelected = estadoBuscarSelected;
	}

	/**
	 * @return the municipioBuscarSelected
	 */
	public MunicipioDTO getMunicipioBuscarSelected() {
		return municipioBuscarSelected;
	}

	/**
	 * @param municipioBuscarSelected
	 *            the municipioBuscarSelected to set
	 */
	public void setMunicipioBuscarSelected(MunicipioDTO municipioBuscarSelected) {
		this.municipioBuscarSelected = municipioBuscarSelected;
	}

	/**
	 * @return the listaSelectPaises
	 */
	public List<SelectItem> getListaSelectPaises() {
		initListaSelectPaises();
		return listaSelectPaises;
	}

	/**
	 * @param listaSelectPaises
	 *            the listaSelectPaises to set
	 */
	public void setListaSelectPaises(List<SelectItem> listaSelectPaises) {
		this.listaSelectPaises = listaSelectPaises;
	}

	/**
	 * @return the listSelectEstados
	 */
	public List<SelectItem> getListaSelectEstados() {
		initListaSelectEstados();
		return listaSelectEstados;
	}

	/**
	 * @param listSelectEstados
	 *            the listSelectEstados to set
	 */
	public void setListaSelectEstados(List<SelectItem> listSelectEstados) {
		this.listaSelectEstados = listSelectEstados;
	}

	/**
	 * @return the listSelectMunicipios
	 */
	public List<SelectItem> getListaSelectMunicipios() {
		initListaSelectMunicipios();
		return listaSelectMunicipios;
	}

	/**
	 * @param listSelectMunicipios
	 *            the listSelectMunicipios to set
	 */
	public void setListaSelectMunicipios(List<SelectItem> listSelectMunicipios) {
		this.listaSelectMunicipios = listSelectMunicipios;
	}

	/**
	 * @return the paisSelectedId
	 */
	public int getPaisSelectedId() {
		return paisSelectedId;
	}

	/**
	 * @param paisSelectedId
	 *            the paisSelectedId to set
	 */
	public void setPaisSelectedId(int paisSelectedId) {
		this.paisSelectedId = paisSelectedId;
	}

	/**
	 * @return the estadoSelectedId
	 */
	public int getEstadoSelectedId() {
		return estadoSelectedId;
	}

	/**
	 * @param estadoSelectedId
	 *            the estadoSelectedId to set
	 */
	public void setEstadoSelectedId(int estadoSelectedId) {
		this.estadoSelectedId = estadoSelectedId;
	}

	/**
	 * @return the municipioSelectedId
	 */
	public int getMunicipioSelectedId() {
		return municipioSelectedId;
	}

	/**
	 * @param municipioSelectedId
	 *            the municipioSelectedId to set
	 */
	public void setMunicipioSelectedId(int municipioSelectedId) {
		this.municipioSelectedId = municipioSelectedId;
	}

	/**
	 * @return the idPaisNuevo
	 */
	public int getIdPaisNuevo() {
		return idPaisNuevo;
	}

	/**
	 * @param idPaisNuevo
	 *            the idPaisNuevo to set
	 */
	public void setIdPaisNuevo(int idPaisNuevo) {
		this.idPaisNuevo = idPaisNuevo;
	}

	/**
	 * @return the idEdoNuevo
	 */
	public int getIdEdoNuevo() {
		return idEdoNuevo;
	}

	/**
	 * @param idEdoNuevo
	 *            the idEdoNuevo to set
	 */
	public void setIdEdoNuevo(int idEdoNuevo) {
		this.idEdoNuevo = idEdoNuevo;
	}

	/**
	 * @return the idPaisEdit
	 */
	public int getIdPaisEdit() {
		return idPaisEdit;
	}

	/**
	 * @param idPaisEdit
	 *            the idPaisEdit to set
	 */
	public void setIdPaisEdit(int idPaisEdit) {
		this.idPaisEdit = idPaisEdit;
	}

	/**
	 * @return the idEdoEdit
	 */
	public int getIdEdoEdit() {
		return idEdoEdit;
	}

	/**
	 * @param idEdoEdit
	 *            the idEdoEdit to set
	 */
	public void setIdEdoEdit(int idEdoEdit) {
		this.idEdoEdit = idEdoEdit;
	}

	/**
	 * @return the idPaisFilter
	 */
	public int getIdPaisFilter() {
		return idPaisFilter;
	}

	/**
	 * @param idPaisFilter
	 *            the idPaisFilter to set
	 */
	public void setIdPaisFilter(int idPaisFilter) {
		this.idPaisFilter = idPaisFilter;
	}

	/**
	 * @return the idEdoFilter
	 */
	public int getIdEdoFilter() {
		return idEdoFilter;
	}

	/**
	 * @param idEdoFilter
	 *            the idEdoFilter to set
	 */
	public void setIdEdoFilter(int idEdoFilter) {
		this.idEdoFilter = idEdoFilter;
	}

	/**
	 * @return the idMunicipioFilter
	 */
	public int getIdMunicipioFilter() {
		return idMunicipioFilter;
	}

	/**
	 * @param idMunicipioFilter
	 *            the idMunicipioFilter to set
	 */
	public void setIdMunicipioFilter(int idMunicipioFilter) {
		this.idMunicipioFilter = idMunicipioFilter;
	}

	/**
	 * @return the listaMunicipioFiltered
	 */
	public List<MunicipioDTO> getListaMunicipioFiltered() {
		return listaMunicipioFiltered;
	}

	/**
	 * @param listaMunicipioFiltered
	 *            the listaMunicipioFiltered to set
	 */
	public void setListaMunicipioFiltered(
			List<MunicipioDTO> listaMunicipioFiltered) {
		this.listaMunicipioFiltered = listaMunicipioFiltered;
	}

	/**
	 * @return the selectedMunicipioNombre
	 */
	public String getSelectedMunicipioNombre() {
		return selectedMunicipioNombre;
	}

	/**
	 * @param selectedMunicipioNombre
	 *            the selectedMunicipioNombre to set
	 */
	public void setSelectedMunicipioNombre(String selectedMunicipioNombre) {
		this.selectedMunicipioNombre = selectedMunicipioNombre;
	}

	/**
	 * @return the selectedMunicipioClave
	 */
	public String getSelectedMunicipioClave() {
		return selectedMunicipioClave;
	}

	/**
	 * @param selectedMunicipioClave
	 *            the selectedMunicipioClave to set
	 */
	public void setSelectedMunicipioClave(String selectedMunicipioClave) {
		this.selectedMunicipioClave = selectedMunicipioClave;
	}

	/**
	 * @return the selectedMunicipioActivo
	 */
	public boolean isSelectedMunicipioActivo() {
		return selectedMunicipioActivo;
	}

	/**
	 * @param selectedMunicipioActivo
	 *            the selectedMunicipioActivo to set
	 */
	public void setSelectedMunicipioActivo(boolean selectedMunicipioActivo) {
		this.selectedMunicipioActivo = selectedMunicipioActivo;
	}

	/**
	 * @return the nuevoMunicipioActivo
	 */
	public boolean isNuevoMunicipioActivo() {
		return nuevoMunicipioActivo;
	}

	/**
	 * @param nuevoMunicipioActivo
	 *            the nuevoMunicipioActivo to set
	 */
	public void setNuevoMunicipioActivo(boolean nuevoMunicipioActivo) {
		this.nuevoMunicipioActivo = nuevoMunicipioActivo;
	}

	@Override
	String getModulo() {
		return modulo;
	}
}
