/**
 * 
 */
package mx.com.asteca.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.springframework.util.CollectionUtils;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.BitacoraDTO;
import mx.com.asteca.fachada.BitacoraFachada;
import mx.com.asteca.fachada.FachadaException;

/**
 * @author JAMARO
 *
 */
@ManagedBean (name = Constantes.BEAN_BITACORA)
@ViewScoped
public class BitacoraControlador extends BaseController {

	private static final String modulo = Constantes.MODULO_BITACORA;
	
	private String accionSelected;
	private int usuarioSelected;
	
	private List<SelectItem> listSelectAccion;
	private List<SelectItem> listSelectUsuarios;
	
	private List<BitacoraDTO> listItems;
	private List<BitacoraDTO> filteredList;
	
	@ManagedProperty("#{bitacoraFachadaImpl}")
	private transient BitacoraFachada fachada;
	
	private BitacoraDTO itemSelected;
	
	private void initListItems(){
		if(CollectionUtils.isEmpty(listItems)){
			try {
				listItems = fachada.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	private void initSelectAccion(){
		if(CollectionUtils.isEmpty(listSelectAccion)){
			listSelectAccion = new ArrayList<SelectItem>();
			if(!CollectionUtils.isEmpty(getListItems())){
				for(BitacoraDTO dto : getListItems()){
					SelectItem item = new SelectItem(dto.getAccion(), dto.getAccion());
					listSelectAccion.add(item);
				}
			}
		}
	}
	
	private void initSelectUsuario(){
		if(CollectionUtils.isEmpty(listSelectUsuarios)){
			listSelectUsuarios = new ArrayList<SelectItem>();
			if(!CollectionUtils.isEmpty(getListItems())){
				for(BitacoraDTO dto : getListItems()){
					SelectItem item = new SelectItem(dto.getIdUsr(), dto.getUsuario());
					listSelectUsuarios.add(item);
				}
			}
		}
	}
	
	public void buscarFiltrado(){
		boolean usr = usuarioSelected != 0;
		boolean accion = (accionSelected != null && !accionSelected.isEmpty());
		try{
			if(usr && accion){
				listItems = fachada.findByIdUsuarioAndAccion(usuarioSelected, accionSelected);
			}else if(usr){
				listItems = fachada.findByIdUsuario(usuarioSelected);
			}else if(accion){
				listItems = fachada.findByAccion(accionSelected);
			}else{
				listItems = fachada.getAll();
			}
		}catch(FachadaException e){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
	}
	
	public void limpiarFiltrado(){
		try {
			listItems = fachada.getAll();
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
		usuarioSelected = 0;
		accionSelected = "";
	}
	
	@Override
	String getModulo() {
		return modulo;
	}



	/**
	 * @return the accionSelected
	 */
	public String getAccionSelected() {
		return accionSelected;
	}



	/**
	 * @param accionSelected the accionSelected to set
	 */
	public void setAccionSelected(String accionSelected) {
		this.accionSelected = accionSelected;
	}



	/**
	 * @return the usuarioSelected
	 */
	public int getUsuarioSelected() {
		return usuarioSelected;
	}



	/**
	 * @param usuarioSelected the usuarioSelected to set
	 */
	public void setUsuarioSelected(int usuarioSelected) {
		this.usuarioSelected = usuarioSelected;
	}



	/**
	 * @return the listSelectAccion
	 */
	public List<SelectItem> getListSelectAccion() {
		initSelectAccion();
		return listSelectAccion;
	}



	/**
	 * @param listSelectAccion the listSelectAccion to set
	 */
	public void setListSelectAccion(List<SelectItem> listSelectAccion) {
		this.listSelectAccion = listSelectAccion;
	}



	/**
	 * @return the listSelectUsuarios
	 */
	public List<SelectItem> getListSelectUsuarios() {
		initSelectUsuario();
		return listSelectUsuarios;
	}



	/**
	 * @param listSelectUsuarios the listSelectUsuarios to set
	 */
	public void setListSelectUsuarios(List<SelectItem> listSelectUsuarios) {
		this.listSelectUsuarios = listSelectUsuarios;
	}



	/**
	 * @return the listItems
	 */
	public List<BitacoraDTO> getListItems() {
		initListItems();
		return listItems;
	}



	/**
	 * @param listItems the listItems to set
	 */
	public void setListItems(List<BitacoraDTO> listItems) {
		this.listItems = listItems;
	}



	/**
	 * @return the filteredList
	 */
	public List<BitacoraDTO> getFilteredList() {
		return filteredList;
	}



	/**
	 * @param filteredList the filteredList to set
	 */
	public void setFilteredList(List<BitacoraDTO> filteredList) {
		this.filteredList = filteredList;
	}



	/**
	 * @return the fachada
	 */
	public BitacoraFachada getFachada() {
		return fachada;
	}



	/**
	 * @param fachada the fachada to set
	 */
	public void setFachada(BitacoraFachada fachada) {
		this.fachada = fachada;
	}

	/**
	 * @return the itemSelected
	 */
	public BitacoraDTO getItemSelected() {
		return itemSelected;
	}

	/**
	 * @param itemSelected the itemSelected to set
	 */
	public void setItemSelected(BitacoraDTO itemSelected) {
		this.itemSelected = itemSelected;
	}

}
