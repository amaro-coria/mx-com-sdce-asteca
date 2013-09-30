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
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.EquipoDTO;
import mx.com.asteca.fachada.EquipoFachada;
import mx.com.asteca.fachada.FachadaException;

import org.apache.commons.collections.CollectionUtils;

@ManagedBean(name = Constantes.BEAN_EQUIPO)
@ViewScoped
public class EquipoControlador extends BaseController implements Serializable {

	/**
	 * Declara ID serializable
	 */
	private static final long serialVersionUID = 1L;
	private static final String modulo = Constantes.MODULO_EQUIPO;
	/*
	 * Tiene que ser transient para no generar excepcion de no serializacion
	 */
	@ManagedProperty("#{equipoFachadaImpl}")
	private transient EquipoFachada fachadaEquipo;
	private EquipoDTO equipoSelected;
	private EquipoDTO equipoNuevo;
	private CatGralDTO catGralSelected;
	private CatGralDTO catGralNuevo;
	private List<EquipoDTO> listaEquipos;
	private List<CatGralDTO> listaCatGral;
	private List<SelectItem> listaSelectCve;
	private List<SelectItem> listaSelectDsc;
	private List<SelectItem> listaSelectTipo;
	private List<SelectItem> listaSelectTiposEquipoCatGral;
	private int idCatGralSelected;
	private int idCatGralNuevo;
	private String selectedDsc;
	private String selectedClave;
	private String selectedTipo;
	private String selectedDscEdit;
	private String selectedClaveEdit;
	private String selectedTipoEdit;
	private List<EquipoDTO> filteredList;
	private boolean selectedActivo;
	private boolean nuevoActivo;

	public EquipoControlador() {
		equipoSelected = new EquipoDTO();
		equipoNuevo = new EquipoDTO();
		catGralSelected = new CatGralDTO();
		catGralNuevo = new CatGralDTO();
	}

	/**
	 * Inicializa la lista de equipos
	 */
	private void initListaEquipos() {
		if (CollectionUtils.isEmpty(listaEquipos)) {
			try {
				listaEquipos = fachadaEquipo.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	/**
	 * Inicializa la lista de catalogoGeneral con la lista de tipos de equipos
	 */
	private void initListaCatGral() {
		if (CollectionUtils.isEmpty(listaCatGral)) {
			try {
				listaCatGral = fachadaEquipo.getListaTipoEquipos();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	/**
	 * Inicializa la lista de <code>SelecItem</code> para la seleccion de items en combo
	 */
	private void initListaSelectTiposEquipoCatGral(){
		if(CollectionUtils.isEmpty(listaSelectTiposEquipoCatGral)){
			listaSelectTiposEquipoCatGral = new ArrayList<SelectItem>();
			for(CatGralDTO dto : getListaCatGral()){
				SelectItem item = new SelectItem(dto.getIdCatGral(), dto.getDsc());
				listaSelectTiposEquipoCatGral.add(item);
			}
		}
	}
	
	/**
	 * Inicializa la lista de <code>SelecItem</code> para la seleccion de items en combo
	 */
	private void initListaSelectCve() {
		if (CollectionUtils.isEmpty(listaSelectCve)) {
			listaSelectCve = new ArrayList<SelectItem>();
			for (EquipoDTO dto : getListaEquipos()) {
				SelectItem item = new SelectItem(dto.getClave(), dto.getClave());
				listaSelectCve.add(item);
			}
		}
	}

	/**
	 * Cambia el valor de la lista de descripciones dependiendo de la clave
	 * seleccionada
	 */
	public void cambiaDescSelect() {
		if (selectedClave != null && !selectedClave.isEmpty()) {
			listaSelectDsc = new ArrayList<SelectItem>();
			List<EquipoDTO> listaDTOs;
			try {
				listaDTOs = fachadaEquipo.findByClave(selectedClave);
				for (EquipoDTO dto : listaDTOs) {
					SelectItem item = new SelectItem(dto.getDsc(), dto.getDsc());
					listaSelectDsc.add(item);
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		} else {
			listaSelectDsc = new ArrayList<SelectItem>();
		}
	}

	/**
	 * Cambia el valor de la lista de descripciones dependiendo de la clave y descripcion
	 * seleccionada
	 */
	public void cambiaTipoEquipoSelect() {
		if (selectedClave != null && !selectedClave.isEmpty()
				&& selectedDsc != null && !selectedDsc.isEmpty()) {
			listaSelectTipo = new ArrayList<SelectItem>();
			List<EquipoDTO> listaDTOs;
			try {
				listaDTOs = fachadaEquipo.findByClaveAndDsc(
						selectedClave, selectedDsc);
				for (EquipoDTO dto : listaDTOs) {
					SelectItem item = new SelectItem(dto.getDscTipo(),
							dto.getDscTipo());
					listaSelectTipo.add(item);
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		} else {
			listaSelectTipo = new ArrayList<SelectItem>();
		}
	}

	/**
	 * Limpia los valores de busqueda
	 * 
	 * @param e
	 */
	public void limpiarFiltrado(ActionEvent e) {
		listaEquipos = null;
		initListaEquipos();
		listaSelectCve = null;
		initListaSelectCve();
	}

	/**
	 * Realiza la busqueda y actualiza valores para el datatable
	 * 
	 * @param e
	 */
	public void buscarFiltrado(ActionEvent e) {
		try {
			if (selectedClave == null || selectedClave.isEmpty()) {
				listaEquipos = null;
				listaSelectCve = null;
				initListaEquipos();
				initListaSelectCve();
			} else if (selectedDsc == null || selectedDsc.isEmpty()) {
				listaEquipos = fachadaEquipo.findByClave(selectedClave);
			} else if (selectedTipo == null
					|| selectedTipo.isEmpty()) {
				listaEquipos = fachadaEquipo.findByClaveAndDsc(
						selectedClave, selectedDsc);
			} else {
				listaEquipos = fachadaEquipo.findByClaveDscAndTipo(
						selectedClave, selectedDsc,
						selectedTipo);
			}
		} catch (FachadaException ex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}

	}
	
	/**
	 * Cancela el borrado del registro seleccionado. 
	 * @param e
	 */
	public void cancelDelete(ActionEvent e){
		setSelectedClave("");
		setSelectedDsc("");
		setSelectedTipo("");
	}
	
	/**
	 * Borra el estado seleccionado
	 * @param e
	 */
	public void delete(ActionEvent e) {
		try {
			fachadaEquipo.remove(equipoSelected);
			listaEquipos.remove(equipoSelected);
			cambiaDescSelect();
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_DELETE_REGISTRO);
			return;
		}
		setSelectedClave("");
		setSelectedDsc("");
		setSelectedTipo("");
		super.addInfoMessage(Constantes.DELETE_REGISTRO_EXITOSO);
	}
	
	/**
	 * Actualiza el registro seleccionado
	 * @param e
	 */
	public void update(ActionEvent e) {
		equipoSelected.setActivo(selectedActivo == true ? (short) 1
				: (short) 0);
		if(selectedClaveEdit != null && !selectedClaveEdit.isEmpty()){
			equipoSelected.setClave(selectedClaveEdit);
		}
		if(selectedDscEdit != null && !selectedDscEdit.isEmpty()){
			equipoSelected.setDsc(selectedDscEdit);
		}
		if(idCatGralSelected ==0){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NECESITAS_SELECCIONAR_UN_TIPO_EQUIPO);
			return;
		}else{
			equipoSelected.setIdTipo(idCatGralSelected);
		}
		try {
			fachadaEquipo.update(equipoSelected);
				CatGralDTO temp = new CatGralDTO();
				temp.setIdCatGral(idCatGralSelected);
				int index = listaCatGral.indexOf(temp);
				CatGralDTO temp2 = listaCatGral.get(index);
				equipoSelected.setDscTipo(temp2.getDsc());				
			int indexListFilter = listaEquipos.indexOf(equipoSelected);
			if(indexListFilter > 0){
				listaEquipos.set(indexListFilter, equipoSelected);
			}
			cambiaDescSelect();
			setSelectedClaveEdit("");
			setSelectedDscEdit("");
			setSelectedTipoEdit("");
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_UPDATE_REGISTRO);
			return;
		}
		super.addInfoMessage(Constantes.UPDATE_REGISTRO_EXITOSO);
	}
	
	/**
	 * Guarda el nuevo registro en BD
	 * @param e
	 */
	public void save(ActionEvent e) {
		equipoNuevo
				.setActivo(nuevoActivo == true ? (short) 1 : (short) 0);
		if(idCatGralNuevo ==0){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NECESITAS_SELECCIONAR_UN_TIPO_EQUIPO);
			return;
		}else{
			equipoNuevo.setIdTipo(idCatGralNuevo);
		}
		try {
			int pk = fachadaEquipo.save(equipoNuevo);
			//TODO propagar este cambio a los controladores anteriores, estado, municipio, ctes, catGral
			CatGralDTO temp = new CatGralDTO();
			temp.setIdCatGral(idCatGralNuevo);
			int index = listaCatGral.indexOf(temp);
			CatGralDTO temp2 = listaCatGral.get(index);
			equipoNuevo.setDscTipo(temp2.getDsc());
			equipoNuevo.setIdEquipo(pk);
			listaEquipos.add(equipoNuevo);
			cambiaDescSelect();
			//refreshEstados();
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NUEVO_REGISTRO);
			return;
		}
		equipoNuevo = new EquipoDTO();
		super.addInfoMessage(Constantes.NUEVO_REGISTRO_EXITOSO);
	}
	
	/**
	 * Cancela el guardar nuevo registro
	 * @param e
	 */
	public void saveCancel(ActionEvent e){
		equipoNuevo = new  EquipoDTO();
	}	

	//  ---------------------- GETTERS AND SETTERS -------------------------   //
	
	/**
	 * @return the equipoSelected
	 */
	public EquipoDTO getEquipoSelected() {
		return equipoSelected;
	}

	/**
	 * @param equipoSelected
	 *            the equipoSelected to set
	 */
	public void setEquipoSelected(EquipoDTO equipoSelected) {
		this.equipoSelected = equipoSelected;
	}

	/**
	 * @return the equipoNuevo
	 */
	public EquipoDTO getEquipoNuevo() {
		return equipoNuevo;
	}

	/**
	 * @param equipoNuevo
	 *            the equipoNuevo to set
	 */
	public void setEquipoNuevo(EquipoDTO equipoNuevo) {
		this.equipoNuevo = equipoNuevo;
	}

	/**
	 * @return the catGralSelected
	 */
	public CatGralDTO getCatGralSelected() {
		return catGralSelected;
	}

	/**
	 * @param catGralSelected
	 *            the catGralSelected to set
	 */
	public void setCatGralSelected(CatGralDTO catGralSelected) {
		this.catGralSelected = catGralSelected;
	}

	/**
	 * @return the listaEquipos
	 */
	public List<EquipoDTO> getListaEquipos() {
		initListaEquipos();
		return listaEquipos;
	}

	/**
	 * @param listaEquipos
	 *            the listaEquipos to set
	 */
	public void setListaEquipos(List<EquipoDTO> listaEquipos) {
		this.listaEquipos = listaEquipos;
	}

	/**
	 * @return the listaCatGral
	 */
	public List<CatGralDTO> getListaCatGral() {
		initListaCatGral();
		return listaCatGral;
	}

	/**
	 * @param listaCatGral
	 *            the listaCatGral to set
	 */
	public void setListaCatGral(List<CatGralDTO> listaCatGral) {
		this.listaCatGral = listaCatGral;
	}

	/**
	 * @return the listaSelectEquiposCve
	 */
	public List<SelectItem> getListaSelectCve() {
		initListaSelectCve();
		return listaSelectCve;
	}

	/**
	 * @param listaSelectEquiposCve
	 *            the listaSelectEquiposCve to set
	 */
	public void setListaSelectCve(List<SelectItem> listaSelectEquiposCve) {
		this.listaSelectCve = listaSelectEquiposCve;
	}

	/**
	 * @return the listaSelectEquiposDsc
	 */
	public List<SelectItem> getListaSelectDsc() {
		return listaSelectDsc;
	}

	/**
	 * @param listaSelectEquiposDsc
	 *            the listaSelectEquiposDsc to set
	 */
	public void setListaSelectDsc(List<SelectItem> listaSelectEquiposDsc) {
		this.listaSelectDsc = listaSelectEquiposDsc;
	}

	/**
	 * @return the listaSelectEquiposTipo
	 */
	public List<SelectItem> getListaSelectTipo() {
		return listaSelectTipo;
	}

	/**
	 * @param listaSelectEquiposTipo
	 *            the listaSelectEquiposTipo to set
	 */
	public void setListaSelectTipo(
			List<SelectItem> listaSelectEquiposTipo) {
		this.listaSelectTipo = listaSelectEquiposTipo;
	}

	/**
	 * @return the idCatGralSelected
	 */
	public int getIdCatGralSelected() {
		return idCatGralSelected;
	}

	/**
	 * @param idCatGralSelected
	 *            the idCatGralSelected to set
	 */
	public void setIdCatGralSelected(int idCatGralSelected) {
		this.idCatGralSelected = idCatGralSelected;
	}

	/**
	 * @return the idCatGralNuevo
	 */
	public int getIdCatGralNuevo() {
		return idCatGralNuevo;
	}

	/**
	 * @param idCatGralNuevo
	 *            the idCatGralNuevo to set
	 */
	public void setIdCatGralNuevo(int idCatGralNuevo) {
		this.idCatGralNuevo = idCatGralNuevo;
	}

	/**
	 * @return the selectedEquipoDsc
	 */
	public String getSelectedDsc() {
		return selectedDsc;
	}

	/**
	 * @param selectedEquipoDsc
	 *            the selectedEquipoDsc to set
	 */
	public void setSelectedDsc(String selectedEquipoDsc) {
		this.selectedDsc = selectedEquipoDsc;
	}

	/**
	 * @return the selectedEquipoClave
	 */
	public String getSelectedClave() {
		return selectedClave;
	}

	/**
	 * @param selectedEquipoClave
	 *            the selectedEquipoClave to set
	 */
	public void setSelectedClave(String selectedEquipoClave) {
		this.selectedClave = selectedEquipoClave;
	}


	/**
	 * @param fachadaEquipo
	 *            the fachadaEquipo to set
	 */
	public void setFachadaEquipo(EquipoFachada fachadaEquipo) {
		this.fachadaEquipo = fachadaEquipo;
	}

	/**
	 * @return the catGralNuevo
	 */
	public CatGralDTO getCatGralNuevo() {
		return catGralNuevo;
	}

	/**
	 * @param catGralNuevo
	 *            the catGralNuevo to set
	 */
	public void setCatGralNuevo(CatGralDTO catGralNuevo) {
		this.catGralNuevo = catGralNuevo;
	}

	/**
	 * @return the equipoSelectedActivo
	 */
	public boolean isSelectedActivo() {
		return selectedActivo;
	}

	/**
	 * @param equipoSelectedActivo
	 *            the equipoSelectedActivo to set
	 */
	public void setSelectedActivo(boolean equipoSelectedActivo) {
		this.selectedActivo = equipoSelectedActivo;
	}

	/**
	 * @return the equipoNuevoActivo
	 */
	public boolean isNuevoActivo() {
		return nuevoActivo;
	}

	/**
	 * @param equipoNuevoActivo
	 *            the equipoNuevoActivo to set
	 */
	public void setNuevoActivo(boolean equipoNuevoActivo) {
		this.nuevoActivo = equipoNuevoActivo;
	}

	/**
	 * @return the selectedEquipoTipo
	 */
	public String getSelectedTipo() {
		return selectedTipo;
	}

	/**
	 * @param selectedEquipoTipo
	 *            the selectedEquipoTipo to set
	 */
	public void setSelectedTipo(String selectedEquipoTipo) {
		this.selectedTipo = selectedEquipoTipo;
	}

	/**
	 * @return the filteredList
	 */
	public List<EquipoDTO> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList the filteredList to set
	 */
	public void setFilteredList(List<EquipoDTO> filteredList) {
		this.filteredList = filteredList;
	}

	/**
	 * @return the listaSelectTiposEquipoCatGral
	 */
	public List<SelectItem> getListaSelectTiposEquipoCatGral() {
		initListaSelectTiposEquipoCatGral();
		return listaSelectTiposEquipoCatGral;
	}

	/**
	 * @param listaSelectTiposEquipoCatGral the listaSelectTiposEquipoCatGral to set
	 */
	public void setListaSelectTiposEquipoCatGral(
			List<SelectItem> listaSelectTiposEquipoCatGral) {
		this.listaSelectTiposEquipoCatGral = listaSelectTiposEquipoCatGral;
	}

	/**
	 * @return the selectedDscEdit
	 */
	public String getSelectedDscEdit() {
		return selectedDscEdit;
	}

	/**
	 * @param selectedDscEdit the selectedDscEdit to set
	 */
	public void setSelectedDscEdit(String selectedDscEdit) {
		this.selectedDscEdit = selectedDscEdit;
	}

	/**
	 * @return the selectedClaveEdit
	 */
	public String getSelectedClaveEdit() {
		return selectedClaveEdit;
	}

	/**
	 * @param selectedClaveEdit the selectedClaveEdit to set
	 */
	public void setSelectedClaveEdit(String selectedClaveEdit) {
		this.selectedClaveEdit = selectedClaveEdit;
	}

	/**
	 * @return the selectedTipoEdit
	 */
	public String getSelectedTipoEdit() {
		return selectedTipoEdit;
	}

	/**
	 * @param selectedTipoEdit the selectedTipoEdit to set
	 */
	public void setSelectedTipoEdit(String selectedTipoEdit) {
		this.selectedTipoEdit = selectedTipoEdit;
	}

	@Override
	String getModulo() {
		return modulo;
	}

}
