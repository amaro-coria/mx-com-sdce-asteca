/**
 * 
 */
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
import mx.com.asteca.comun.dto.AulaDTO;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.fachada.AulaFachada;
import mx.com.asteca.fachada.FachadaException;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 *
 */
@ManagedBean(name = Constantes.BEAN_AULAS)
@ViewScoped
public class AulaControlador extends BaseController implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{aulaFachadaImpl}")
	private transient AulaFachada fachada;
	private AulaDTO itemSelected;
	private AulaDTO itemNuevo;
	private CatGralDTO catGralSelected;
	private CatGralDTO catGralNuevo;
	private List<AulaDTO> listaItems;
	private List<CatGralDTO> listaCatGral;
	/**
	 * Lista con el ID como llave
	 */
	private List<SelectItem> listaSelectTiposItemsCatGral;
	/**
	 * Lista con el nombre como llave
	 */
	private List<SelectItem> listaSelectItems;
	private int idCatGralSelected;
	private int idCatGralNuevo;
	private String selectedItemFilter;
	private String selectedDscEdit;
	private String selectedClaveEdit;
	private String selectedCapacidadEdit;
	private String capacidadNuevo;
	private List<AulaDTO> filteredList;
	private boolean selectedActivo;
	private boolean nuevoActivo;
	
	
	
	public AulaControlador() {
		itemSelected = new AulaDTO();
		itemNuevo = new AulaDTO();
		catGralSelected = new CatGralDTO();
		catGralNuevo = new CatGralDTO();
	}
	
	private void initListaItems(){
		if(CollectionUtils.isEmpty(listaItems)){
			try {
				listaItems = fachada.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	private void initListaCatGral(){
		if(CollectionUtils.isEmpty(listaCatGral)){
			try {
				listaCatGral = fachada.getSedes();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	private void initListaSelectSedeCatGral(){
		if(CollectionUtils.isEmpty(listaSelectTiposItemsCatGral)){
			listaSelectTiposItemsCatGral = new ArrayList<SelectItem>();
			for(CatGralDTO dto : getListaCatGral()){
				SelectItem item = new SelectItem(dto.getIdCatGral(), dto.getDsc());
				listaSelectTiposItemsCatGral.add(item);
			}
		}
	}
	
	private void initListaSelectItems(){
		if(CollectionUtils.isEmpty(listaSelectItems)){
			listaSelectItems = new ArrayList<SelectItem>();
			for(CatGralDTO dto : getListaCatGral()){
				SelectItem item = new SelectItem(dto.getDsc(), dto.getDsc());
				listaSelectItems.add(item);
			}
		}
	}
	
	/**
	 * Limpia los valores de busqueda
	 * 
	 * @param e
	 */
	public void limpiarFiltrado(ActionEvent e) {
		listaItems = null;
		initListaItems();
		listaSelectItems = null;
		initListaItems();
	}
	
	/**
	 * Realiza la busqueda y actualiza valores para el datatable
	 * 
	 * @param e
	 */
	public void buscarFiltrado(ActionEvent e) {
		try {
			if (selectedItemFilter == null || selectedItemFilter.isEmpty()) {
				listaItems = null;
				listaSelectItems = null;
				initListaItems();
				initListaItems();
				//TODO validar que pasa en el datatable cuando el resultado es null, no actualiza los registros
			} else {
				listaItems = fachada.findBySede(selectedItemFilter);
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
		setSelectedItemFilter("");
	}
	
	/**
	 * Borra el item seleccionado
	 * @param e
	 */
	public void delete(ActionEvent e) {
		try {
			fachada.remove(itemSelected);
			listaItems.remove(itemSelected);
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_DELETE_REGISTRO);
			return;
		}
		setSelectedItemFilter("");
		super.addInfoMessage(Constantes.DELETE_REGISTRO_EXITOSO);
	}
	
	/**
	 * Actualiza el registro seleccionado
	 * @param e
	 */
	public void update(ActionEvent e) {
		itemSelected.setActivo(selectedActivo == true ? (short) 1
				: (short) 0);
		if(idCatGralSelected ==0){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NECESITAS_SELECCIONAR_UNA_SEDE);
			return;
		}else{
			itemSelected.setIdSede(idCatGralSelected);
		}
		try{
			int capacidad = Integer.parseInt(selectedCapacidadEdit);
			itemSelected.setCapacidad(capacidad);
		}catch(NumberFormatException nex){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_FORMATO_NUMERO);
			return;
		}
		try {
			fachada.update(itemSelected);
				CatGralDTO temp = new CatGralDTO();
				temp.setIdCatGral(idCatGralSelected);
				int index = listaCatGral.indexOf(temp);
				CatGralDTO temp2 = listaCatGral.get(index);
				itemSelected.setSede(temp2.getDsc());				
			int indexListFilter = listaItems.indexOf(itemSelected);
			if(indexListFilter > 0){
				listaItems.set(indexListFilter, itemSelected);
			}
			setSelectedClaveEdit("");
			setSelectedDscEdit("");
			setSelectedCapacidadEdit("");
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
		itemNuevo
				.setActivo(nuevoActivo == true ? (short) 1 : (short) 0);
		if(idCatGralNuevo ==0){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NECESITAS_SELECCIONAR_UN_TIPO_EQUIPO);
			return;
		}else{
			itemNuevo.setIdSede(idCatGralNuevo);
		}
		try{
			int capacidad = Integer.parseInt(capacidadNuevo);
			itemNuevo.setCapacidad(capacidad);
		}catch(NumberFormatException nex){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_FORMATO_NUMERO);
			return;
		}
		try {
			int pk = fachada.save(itemNuevo);
			CatGralDTO temp = new CatGralDTO();
			temp.setIdCatGral(idCatGralNuevo);
			int index = listaCatGral.indexOf(temp);
			CatGralDTO temp2 = listaCatGral.get(index);
			itemNuevo.setSede(temp2.getDsc());
			itemNuevo.setIdAula(pk);
			if(CollectionUtils.isEmpty(listaItems)){
				listaItems = new ArrayList<AulaDTO>();
			}
			listaItems.add(itemNuevo);
			//refreshEstados();
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NUEVO_REGISTRO);
			return;
		}
		itemNuevo = new AulaDTO();
		super.addInfoMessage(Constantes.NUEVO_REGISTRO_EXITOSO);
	}
	
	/**
	 * Cancela el guardar nuevo registro
	 * @param e
	 */
	public void saveCancel(ActionEvent e){
		itemNuevo = new AulaDTO();
	}	
	
	// --------------------------- GETTERS & SETTERS --------------------------------------- //
	
	/**
	 * @param fachadaEquipo the fachadaEquipo to set
	 */
	public void setFachada(AulaFachada fachada) {
		this.fachada = fachada;
	}
	/**
	 * @return the itemSelected
	 */
	public AulaDTO getItemSelected() {
		return itemSelected;
	}
	/**
	 * @param itemSelected the itemSelected to set
	 */
	public void setItemSelected(AulaDTO itemSelected) {
		this.itemSelected = itemSelected;
	}
	/**
	 * @return the itemNuevo
	 */
	public AulaDTO getItemNuevo() {
		return itemNuevo;
	}
	/**
	 * @param itemNuevo the itemNuevo to set
	 */
	public void setItemNuevo(AulaDTO itemNuevo) {
		this.itemNuevo = itemNuevo;
	}
	/**
	 * @return the catGralSelected
	 */
	public CatGralDTO getCatGralSelected() {
		return catGralSelected;
	}
	/**
	 * @param catGralSelected the catGralSelected to set
	 */
	public void setCatGralSelected(CatGralDTO catGralSelected) {
		this.catGralSelected = catGralSelected;
	}
	/**
	 * @return the catGralNuevo
	 */
	public CatGralDTO getCatGralNuevo() {
		return catGralNuevo;
	}
	/**
	 * @param catGralNuevo the catGralNuevo to set
	 */
	public void setCatGralNuevo(CatGralDTO catGralNuevo) {
		this.catGralNuevo = catGralNuevo;
	}
	/**
	 * @return the listaItems
	 */
	public List<AulaDTO> getListaItems() {
		initListaItems();
		return listaItems;
	}
	/**
	 * @param listaItems the listaItems to set
	 */
	public void setListaItems(List<AulaDTO> listaItems) {
		this.listaItems = listaItems;
	}
	/**
	 * @return the listaCatGral
	 */
	public List<CatGralDTO> getListaCatGral() {
		initListaCatGral();
		return listaCatGral;
	}
	/**
	 * @param listaCatGral the listaCatGral to set
	 */
	public void setListaCatGral(List<CatGralDTO> listaCatGral) {
		this.listaCatGral = listaCatGral;
	}
	/**
	 * @return the listaSelectTiposItemsCatGral
	 */
	public List<SelectItem> getListaSelectTiposItemsCatGral() {
		initListaSelectSedeCatGral();
		return listaSelectTiposItemsCatGral;
	}
	/**
	 * @param listaSelectTiposItemsCatGral the listaSelectTiposItemsCatGral to set
	 */
	public void setListaSelectTiposItemsCatGral(
			List<SelectItem> listaSelectTiposItemsCatGral) {
		this.listaSelectTiposItemsCatGral = listaSelectTiposItemsCatGral;
	}
	/**
	 * @return the idCatGralSelected
	 */
	public int getIdCatGralSelected() {
		return idCatGralSelected;
	}
	/**
	 * @param idCatGralSelected the idCatGralSelected to set
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
	 * @param idCatGralNuevo the idCatGralNuevo to set
	 */
	public void setIdCatGralNuevo(int idCatGralNuevo) {
		this.idCatGralNuevo = idCatGralNuevo;
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
	 * @return the selectedCapacidadEdit
	 */
	public String getSelectedCapacidadEdit() {
		return selectedCapacidadEdit;
	}
	/**
	 * @param selectedCapacidadEdit the selectedCapacidadEdit to set
	 */
	public void setSelectedCapacidadEdit(String selectedCapacidadEdit) {
		this.selectedCapacidadEdit = selectedCapacidadEdit;
	}
	/**
	 * @return the filteredList
	 */
	public List<AulaDTO> getFilteredList() {
		return filteredList;
	}
	/**
	 * @param filteredList the filteredList to set
	 */
	public void setFilteredList(List<AulaDTO> filteredList) {
		this.filteredList = filteredList;
	}
	/**
	 * @return the selectedActivo
	 */
	public boolean isSelectedActivo() {
		return selectedActivo;
	}
	/**
	 * @param selectedActivo the selectedActivo to set
	 */
	public void setSelectedActivo(boolean selectedActivo) {
		this.selectedActivo = selectedActivo;
	}
	/**
	 * @return the nuevoActivo
	 */
	public boolean isNuevoActivo() {
		return nuevoActivo;
	}
	/**
	 * @param nuevoActivo the nuevoActivo to set
	 */
	public void setNuevoActivo(boolean nuevoActivo) {
		this.nuevoActivo = nuevoActivo;
	}

	/**
	 * @return the listaSelectItems
	 */
	public List<SelectItem> getListaSelectItems() {
		initListaSelectItems();
		return listaSelectItems;
	}

	/**
	 * @param listaSelectItems the listaSelectItems to set
	 */
	public void setListaSelectItems(List<SelectItem> listaSelectItems) {
		this.listaSelectItems = listaSelectItems;
	}

	/**
	 * @return the selectedItemFilter
	 */
	public String getSelectedItemFilter() {
		return selectedItemFilter;
	}

	/**
	 * @param selectedItemFilter the selectedItemFilter to set
	 */
	public void setSelectedItemFilter(String selectedItemFilter) {
		this.selectedItemFilter = selectedItemFilter;
	}

	/**
	 * @return the capacidadNuevo
	 */
	public String getCapacidadNuevo() {
		return capacidadNuevo;
	}

	/**
	 * @param capacidadNuevo the capacidadNuevo to set
	 */
	public void setCapacidadNuevo(String capacidadNuevo) {
		this.capacidadNuevo = capacidadNuevo;
	}
	
	
}
