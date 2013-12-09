/**
 * 
 */
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
import javax.servlet.http.HttpServletRequest;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.comun.dto.PermisosBooleanDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.MateriaRegistroFachada;

import org.primefaces.context.RequestContext;
import org.springframework.util.CollectionUtils;

/**
 * @author JAMARO
 *
 */
@ManagedBean(name = Constantes.BEAN_MAERIAS_REGISTRO)
@ViewScoped
public class MateriaRegistroControlador extends BaseController implements Serializable{

	private static final String modulo = Constantes.MODULO_MATERIAS;
	
	@ManagedProperty("#{materiaRegistroFachadaImpl}")
	private transient MateriaRegistroFachada fachada;
	
	private List<MateriaRegistroDTO> listItems;
	
	private List<SelectItem> listSelect1;
	
	private MateriaRegistroDTO itemSelected;
	private MateriaRegistroDTO itemNuevo;
	
	private String nuevoNombre;
	private String editarNombre;
	private String selectedNombre;
	
	private List<MateriaRegistroDTO> filteredList;
	
	public MateriaRegistroControlador(){
		itemSelected = new MateriaRegistroDTO();
		itemNuevo = new MateriaRegistroDTO();
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
	private void initListItems(){
		if(CollectionUtils.isEmpty(listItems)){
			try {
				listItems = fachada.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	private void initListSelect1(){
		if(CollectionUtils.isEmpty(listSelect1)){
			listSelect1 = new ArrayList<SelectItem>();
			if(!CollectionUtils.isEmpty(getListItems())){
				for(MateriaRegistroDTO dto : getListItems()){
					SelectItem item = new SelectItem(dto.getNombre(), dto.getNombre());
					listSelect1.add(item);
				}
			}
		}
	}
	
	/**
	 * Limpia los valores de busqueda
	 * 
	 * @param e
	 */
	public void limpiarFiltrado(ActionEvent e) {
		listItems = null;
		initListItems();
	}

	/**
	 * Realiza la busqueda y actualiza valores para el datatable
	 * 
	 * @param e
	 */
	public void buscarFiltrado(ActionEvent e) {
		try {
			if(selectedNombre != null && !selectedNombre.isEmpty()){
				listItems = fachada.findByNombre(selectedNombre);
			}
		} catch (FachadaException ex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}

	}
	
	public void cancelDelete(){
		editarNombre = "";
	}
	
	public void delete(){
		try{
			fachada.remove(itemSelected);
			addBitacora(Constantes.ACCION_DELETE_REGISTRO, Constantes.ACCION_DELETE_REGISTRO_EXITOSO_MENSAJE+":Materia"+itemSelected.getIdMateria()+":");
			super.addInfoMessage(Constantes.DELETE_REGISTRO_EXITOSO);
		}catch(FachadaException e){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_DELETE_REGISTRO);
			addBitacora(Constantes.ACCION_DELETE_REGISTRO, Constantes.ACCION_DELETE_REGISTRO_FALLIDO_MENSAJE+":Materia"+itemSelected.getIdMateria()+":");
		}
	}
	
	public void update(){
		try{
			if(editarNombre != null && !editarNombre.isEmpty()){
				itemSelected.setNombre(editarNombre);
				fachada.update(itemSelected);
				int index = listItems.indexOf(itemSelected);
				if(index > 0){
					listItems.set(index, itemSelected);
				}
				listSelect1 = null;
				initListSelect1();
			}else{
				super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING, Constantes.WARNING_NECESITAS_LLENAR_CAMPOS_REQUERIDOS);
				return;
			}
		}catch(FachadaException e){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_UPDATE_REGISTRO);
			addBitacora(Constantes.ACCION_UPDATE_REGISTRO, Constantes.ACCION_UPDATE_REGISTRO_FALLIDO_MENSAJE);
			return;
		}
		super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO, Constantes.UPDATE_REGISTRO_EXITOSO);		
		addBitacora(Constantes.ACCION_UPDATE_REGISTRO, Constantes.ACCION_UPDATE_REGISTRO_EXITOSO_MENSAJE+":Materia "+itemSelected.getIdMateria()+":");
	}
	
	public void save(ActionEvent e){
		try{
			if (validaDatos()) {
				itemNuevo.setNombre(nuevoNombre);
				int pk = fachada.save(itemNuevo);
				itemNuevo.setIdMateria(pk);
				listItems.add(itemNuevo);
				listSelect1 = null;
				initListSelect1();
				RequestContext.getCurrentInstance().execute("nuevoDialog.hide()");
				super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO, Constantes.NUEVO_REGISTRO_EXITOSO);
				addBitacora(Constantes.ACCION_NUEVO_REGISTRO, Constantes.ACCION_NUEVO_REGISTRO_EXITOSO_MENSAJE+":Materia "+pk+":");
			} else {
				super.addWarningMessage(Constantes.WARNING_NECESITAS_LLENAR_CAMPOS_REQUERIDOS);
			}
		}catch(FachadaException ex){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NUEVO_REGISTRO);
			addBitacora(Constantes.ACCION_NUEVO_REGISTRO, Constantes.ACCION_NUEVO_REGISTRO_FALLIDO_MENSAJE+":Estado:");
		}
	}
	
	private boolean validaDatos(){
		return(!(nuevoNombre == null) && !nuevoNombre.isEmpty());
	}
	
	public void saveCancel(){
		itemNuevo = new MateriaRegistroDTO();
		nuevoNombre = "";
	}
	
	
	
	// ------------------- Getters & Setters --------------------------//
	/**
	 * @return the fachada
	 */
	public MateriaRegistroFachada getFachada() {
		return fachada;
	}

	/**
	 * @param fachada the fachada to set
	 */
	public void setFachada(MateriaRegistroFachada fachada) {
		this.fachada = fachada;
	}

	/**
	 * @return the listItems
	 */
	public List<MateriaRegistroDTO> getListItems() {
		initListItems();
		return listItems;
	}

	/**
	 * @param listItems the listItems to set
	 */
	public void setListItems(List<MateriaRegistroDTO> listItems) {
		this.listItems = listItems;
	}

	/**
	 * @return the listSelect1
	 */
	public List<SelectItem> getListSelect1() {
		initListSelect1();
		return listSelect1;
	}

	/**
	 * @param listSelect1 the listSelect1 to set
	 */
	public void setListSelect1(List<SelectItem> listSelect1) {
		this.listSelect1 = listSelect1;
	}

	/**
	 * @return the itemSelected
	 */
	public MateriaRegistroDTO getItemSelected() {
		return itemSelected;
	}

	/**
	 * @param itemSelected the itemSelected to set
	 */
	public void setItemSelected(MateriaRegistroDTO itemSelected) {
		this.itemSelected = itemSelected;
	}

	/**
	 * @return the itemNuevo
	 */
	public MateriaRegistroDTO getItemNuevo() {
		return itemNuevo;
	}

	/**
	 * @param itemNuevo the itemNuevo to set
	 */
	public void setItemNuevo(MateriaRegistroDTO itemNuevo) {
		this.itemNuevo = itemNuevo;
	}

	/**
	 * @return the nuevoNombre
	 */
	public String getNuevoNombre() {
		return nuevoNombre;
	}

	/**
	 * @param nuevoNombre the nuevoNombre to set
	 */
	public void setNuevoNombre(String nuevoNombre) {
		this.nuevoNombre = nuevoNombre;
	}

	/**
	 * @return the editarNombre
	 */
	public String getEditarNombre() {
		return editarNombre;
	}

	/**
	 * @param editarNombre the editarNombre to set
	 */
	public void setEditarNombre(String editarNombre) {
		this.editarNombre = editarNombre;
	}

	/**
	 * @return the selectedNombre
	 */
	public String getSelectedNombre() {
		return selectedNombre;
	}

	/**
	 * @param selectedNombre the selectedNombre to set
	 */
	public void setSelectedNombre(String selectedNombre) {
		this.selectedNombre = selectedNombre;
	}

	/**
	 * @return the filteredList
	 */
	public List<MateriaRegistroDTO> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList the filteredList to set
	 */
	public void setFilteredList(List<MateriaRegistroDTO> filteredList) {
		this.filteredList = filteredList;
	}

	@Override
	String getModulo() {
		return modulo;
	}
	
	
 }