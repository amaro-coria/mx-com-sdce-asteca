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

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.PermisosBooleanDTO;
import mx.com.asteca.comun.dto.TipoCatGralDTO;
import mx.com.asteca.fachada.CatGralFachada;
import mx.com.asteca.fachada.FachadaException;

/**
 * @author Javier
 * 
 */
@ManagedBean(name = Constantes.BEAN_CAT_GRAL)
@ViewScoped
public class CatGralControlador extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String modulo = Constantes.MODULO_CAT_GRAL;
	@ManagedProperty("#{catGralFachadaImpl}")
	private transient CatGralFachada catGralFachada;
	private CatGralDTO catalogoSelected;
	private CatGralDTO catalogoNuevo;

	private List<CatGralDTO> listaCatalogos;
	private List<SelectItem> listaSelectCatalogosCve;
	private List<SelectItem> listaSelectCatalogosDsc;
	private List<SelectItem> listaSelectCatalogosEst;
	private int idTipoCatalogoSelected;
	private int idTipoCatalogoNuevo;
	private String catalogoCveFilter;
	private String catalogoDscFilter;
	private String catalogoEstFilter;
	private boolean selectedCatalogoActivo;
	private boolean nuevoCatalogoActivo;
	private List<CatGralDTO> listaFiltered;
	private String selectedClaveCatGral;
	private String selectedDscCatGral;
	private String selectedEstatusCatGral;
	private List<SelectItem> listSelectTipoCat;
	private short idTipoCatGralNuevo;
	private short idTipoCatGralEdit;

	/* Constructor default */

	public CatGralControlador() {
		catalogoSelected = new CatGralDTO();
		catalogoNuevo = new CatGralDTO();
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
	
	
	private void initListaSelectTipoCat(){
		if(CollectionUtils.isEmpty(listSelectTipoCat)){
			listSelectTipoCat = new ArrayList<SelectItem>();
			List<TipoCatGralDTO> lista = null;
			try {
				lista = catGralFachada.getTiposCatGral();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
			if(!CollectionUtils.isEmpty(lista)){
				for(TipoCatGralDTO dto : lista){
					SelectItem item = new SelectItem(dto.getIdCatGral(), dto.getTipo());
					listSelectTipoCat.add(item);
				}
			}
		}
	}

	private void initListaSelectCatalogosCve() {
		if (CollectionUtils.isEmpty(listaSelectCatalogosCve)) {
			listaSelectCatalogosCve = new ArrayList<SelectItem>();
			if(!CollectionUtils.isEmpty(getListaCatalogos())){
				for (CatGralDTO dto : getListaCatalogos()) {
					SelectItem item = new SelectItem(dto.getCveRegistro(),
							dto.getCveRegistro());
					listaSelectCatalogosCve.add(item);
				}
			}
		}
	}

	private void initListaCatalogos() {
		if (CollectionUtils.isEmpty(listaCatalogos)) {
			try {
				listaCatalogos = catGralFachada.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	/**
	 * Cambia el valor de la lista de descripciones dependiendo de la clave
	 * seleccionada
	 */
	public void cambiaDescSelect() {
		if (catalogoCveFilter != null && !catalogoCveFilter.isEmpty()) {
			listaSelectCatalogosDsc = new ArrayList<SelectItem>();
			List<CatGralDTO> listaDTOs;
			try {
				listaDTOs = catGralFachada.findByCve(catalogoCveFilter);
				for (CatGralDTO dto : listaDTOs) {
					SelectItem item = new SelectItem(dto.getDsc(), dto.getDsc());
					listaSelectCatalogosDsc.add(item);
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}

		} else {
			listaSelectCatalogosDsc = new ArrayList<SelectItem>();
		}
	}

	/**
	 * Cambia el valor de la lista de estatus dependiendo de la descripcion y
	 * clve seleccionada
	 */
	public void cambiaEstatusSelect() {
		if (catalogoDscFilter != null && !catalogoDscFilter.isEmpty()
				&& catalogoCveFilter != null && !catalogoCveFilter.isEmpty()) {
			listaSelectCatalogosEst = new ArrayList<SelectItem>();
			List<CatGralDTO> listaDTOs;
			try {
				listaDTOs = catGralFachada.findByCveAndDsc(catalogoCveFilter,
						catalogoDscFilter);
				for (CatGralDTO dto : listaDTOs) {
					SelectItem item = new SelectItem(dto.getEstatus(),
							dto.getEstatus());
					listaSelectCatalogosEst.add(item);
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}

		} else {
			listaSelectCatalogosEst = new ArrayList<SelectItem>();
		}
	}

	/**
	 * Limpia los valores de busqueda
	 * 
	 * @param e
	 */
	public void limpiarFiltrado(ActionEvent e) {
		listaCatalogos = null;
		initListaCatalogos();
		listaSelectCatalogosCve = null;
		initListaSelectCatalogosCve();
	}

	/**
	 * Realiza la busqueda y actualiza valores para el datatable
	 * 
	 * @param e
	 */
	public void buscarFiltrado(ActionEvent e) {
		try {
			if (catalogoCveFilter == null || catalogoCveFilter.isEmpty()) {
				listaCatalogos = null;
				listaSelectCatalogosCve = null;
				initListaCatalogos();
				initListaSelectCatalogosCve();
			} else if (catalogoDscFilter == null || catalogoDscFilter.isEmpty()) {
				listaCatalogos = catGralFachada.findByCve(catalogoCveFilter);
			} else if (catalogoEstFilter == null || catalogoEstFilter.isEmpty()) {
				listaCatalogos = catGralFachada.findByCveAndDsc(
						catalogoCveFilter, catalogoDscFilter);
			} else {
				listaCatalogos = catGralFachada
						.findByCveDscAndEstatus(catalogoCveFilter,
								catalogoDscFilter, catalogoEstFilter);
			}
		} catch (FachadaException ex) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}

	}
	
	/**
	 * Cancela el borrado del catalogoGral seleccionado. 
	 * @param e
	 */
	public void cancelDeleteCat(ActionEvent e){
		setSelectedClaveCatGral("");
		setSelectedDscCatGral("");
		setSelectedEstatusCatGral("");
	}

	/**
	 * Borra el estado seleccionado
	 * @param e
	 */
	public void deleteCat(ActionEvent e) {
		try {
			catGralFachada.remove(catalogoSelected);
			listaCatalogos.remove(catalogoSelected);
			cambiaDescSelect();
			super.addInfoMessage(Constantes.DELETE_REGISTRO_EXITOSO);
			addBitacora(Constantes.ACCION_DELETE_REGISTRO, Constantes.ACCION_DELETE_REGISTRO_EXITOSO_MENSAJE+":CatGral"+catalogoSelected.getIdCatGral()+":");
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_DELETE_REGISTRO);
			addBitacora(Constantes.ACCION_DELETE_REGISTRO, Constantes.ACCION_DELETE_REGISTRO_FALLIDO_MENSAJE+":CatGral"+catalogoSelected.getIdCatGral()+":");
			return;
		}
		setSelectedClaveCatGral("");
		setSelectedDscCatGral("");
		setSelectedEstatusCatGral("");
	}
	
	
	/**
	 * Actualiza el catalogo seleccionado
	 * @param e
	 */
	public void updateCat(ActionEvent e) {
		catalogoSelected.setActivo(selectedCatalogoActivo == true ? (short) 1
				: (short) 0);
		if(selectedClaveCatGral != null && !selectedClaveCatGral.isEmpty()){
			catalogoSelected.setCveRegistro(selectedClaveCatGral);
		}
		if(selectedDscCatGral != null && !selectedDscCatGral.isEmpty()){
			catalogoSelected.setDsc(selectedDscCatGral);
		}
		if(selectedEstatusCatGral != null && !selectedEstatusCatGral.isEmpty()){
			catalogoSelected.setEstatus(selectedEstatusCatGral);
		}
		if(idTipoCatGralEdit != 0){
			catalogoSelected.setIdTipoCatGral(idTipoCatGralEdit);
		}
		try {
			catGralFachada.update(catalogoSelected);
			int indexListFilter = listaCatalogos.indexOf(catalogoSelected);
			if(indexListFilter > 0){
				listaCatalogos.set(indexListFilter, catalogoSelected);
			}
			cambiaDescSelect();
			super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO, Constantes.UPDATE_REGISTRO_EXITOSO);		
			addBitacora(Constantes.ACCION_UPDATE_REGISTRO, Constantes.ACCION_UPDATE_REGISTRO_EXITOSO_MENSAJE+":CatGral "+catalogoSelected.getIdCatGral()+":");
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_UPDATE_REGISTRO);
			addBitacora(Constantes.ACCION_UPDATE_REGISTRO, Constantes.ACCION_UPDATE_REGISTRO_FALLIDO_MENSAJE);
			return;
		}
	}
	
	/**
	 * Guarda el nuevo catalogo en BD
	 * @param e
	 */
	public void saveCat(ActionEvent e) {
		boolean b = validaDatos();
		if(b){
			catalogoNuevo.setIdTipoCatGral(idTipoCatGralNuevo);
			catalogoNuevo
					.setActivo(nuevoCatalogoActivo == true ? (short) 1 : (short) 0);
			try {
				int pk = catGralFachada.save(catalogoNuevo);
				catalogoNuevo.setIdCatGral(pk);
				RequestContext.getCurrentInstance().execute("nuevoDialog.hide()");
				super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO, Constantes.NUEVO_REGISTRO_EXITOSO);
				addBitacora(Constantes.ACCION_NUEVO_REGISTRO, Constantes.ACCION_NUEVO_REGISTRO_EXITOSO_MENSAJE+":CatGral "+pk+":");
				if(!CollectionUtils.isEmpty(listaCatalogos)){
					listaCatalogos.add(catalogoNuevo);
				}else{
					listaCatalogos = new ArrayList<CatGralDTO>();
					listaCatalogos.add(catalogoNuevo);
				}
				cambiaDescSelect();
				//refreshEstados();
			} catch (FachadaException e1) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NUEVO_REGISTRO);
				addBitacora(Constantes.ACCION_NUEVO_REGISTRO, Constantes.ACCION_NUEVO_REGISTRO_FALLIDO_MENSAJE+":CatGral:");
				return;
			}
			catalogoNuevo = new CatGralDTO();
		}else{
			super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING, Constantes.WARNING_NECESITAS_LLENAR_CAMPOS_REQUERIDOS);
		}
	}
	
	public boolean validaDatos(){
		if(idTipoCatGralNuevo == 0){
			return false;
		}else if(catalogoNuevo.getCveRegistro() == null || catalogoNuevo.getCveRegistro().isEmpty()){ 
			return false;
		}else if(catalogoNuevo.getDsc() == null || catalogoNuevo.getDsc().isEmpty()){
			return false;
		}
		return true;
	}
	
	/**
	 * Cancela el guardar nuevo registro
	 * @param e
	 */
	public void saveCatCancel(ActionEvent e){
		catalogoNuevo = new  CatGralDTO();
	}	
	
	// ------------------ GETTERS & SETTERS --------------------- //
	/**
	 * @return the catalogoSelected
	 */
	public CatGralDTO getCatalogoSelected() {
		return catalogoSelected;
	}

	/**
	 * @param catalogoSelected
	 *            the catalogoSelected to set
	 */
	public void setCatalogoSelected(CatGralDTO catalogoSelected) {
		this.catalogoSelected = catalogoSelected;
	}

	/**
	 * @return the catalogoNuevo
	 */
	public CatGralDTO getCatalogoNuevo() {
		return catalogoNuevo;
	}

	/**
	 * @param catalogoNuevo
	 *            the catalogoNuevo to set
	 */
	public void setCatalogoNuevo(CatGralDTO catalogoNuevo) {
		this.catalogoNuevo = catalogoNuevo;
	}

	/**
	 * @return the listaCatalogos
	 */
	public List<CatGralDTO> getListaCatalogos() {
		initListaCatalogos();
		return listaCatalogos;
	}

	/**
	 * @param listaCatalogos
	 *            the listaCatalogos to set
	 */
	public void setListaCatalogos(List<CatGralDTO> listaCatalogos) {
		this.listaCatalogos = listaCatalogos;
	}

	/**
	 * @return the listaSelectCatalogosCve
	 */
	public List<SelectItem> getListaSelectCatalogosCve() {
		initListaSelectCatalogosCve();
		return listaSelectCatalogosCve;
	}

	/**
	 * @param listaSelectCatalogosCve
	 *            the listaSelectCatalogosCve to set
	 */
	public void setListaSelectCatalogosCve(
			List<SelectItem> listaSelectCatalogosCve) {
		this.listaSelectCatalogosCve = listaSelectCatalogosCve;
	}

	/**
	 * @return the listaSelectCatalogosDsc
	 */
	public List<SelectItem> getListaSelectCatalogosDsc() {
		return listaSelectCatalogosDsc;
	}

	/**
	 * @param listaSelectCatalogosDsc
	 *            the listaSelectCatalogosDsc to set
	 */
	public void setListaSelectCatalogosDsc(
			List<SelectItem> listaSelectCatalogosDsc) {
		this.listaSelectCatalogosDsc = listaSelectCatalogosDsc;
	}

	/**
	 * @return the listaSelectCatalogosEst
	 */
	public List<SelectItem> getListaSelectCatalogosEst() {
		return listaSelectCatalogosEst;
	}

	/**
	 * @param listaSelectCatalogosEst
	 *            the listaSelectCatalogosEst to set
	 */
	public void setListaSelectCatalogosEst(
			List<SelectItem> listaSelectCatalogosEst) {
		this.listaSelectCatalogosEst = listaSelectCatalogosEst;
	}

	/**
	 * @return the idTipoCatalogoSelected
	 */
	public int getIdTipoCatalogoSelected() {
		return idTipoCatalogoSelected;
	}

	/**
	 * @param idTipoCatalogoSelected
	 *            the idTipoCatalogoSelected to set
	 */
	public void setIdTipoCatalogoSelected(int idTipoCatalogoSelected) {
		this.idTipoCatalogoSelected = idTipoCatalogoSelected;
	}

	/**
	 * @return the idTipoCatalogoNuevo
	 */
	public int getIdTipoCatalogoNuevo() {
		return idTipoCatalogoNuevo;
	}

	/**
	 * @param idTipoCatalogoNuevo
	 *            the idTipoCatalogoNuevo to set
	 */
	public void setIdTipoCatalogoNuevo(int idTipoCatalogoNuevo) {
		this.idTipoCatalogoNuevo = idTipoCatalogoNuevo;
	}

	/**
	 * @return the catalogoCveFilter
	 */
	public String getCatalogoCveFilter() {
		return catalogoCveFilter;
	}

	/**
	 * @param catalogoCveFilter
	 *            the catalogoCveFilter to set
	 */
	public void setCatalogoCveFilter(String catalogoCveFilter) {
		this.catalogoCveFilter = catalogoCveFilter;
	}

	/**
	 * @return the catalogoDscFilter
	 */
	public String getCatalogoDscFilter() {
		return catalogoDscFilter;
	}

	/**
	 * @param catalogoDscFilter
	 *            the catalogoDscFilter to set
	 */
	public void setCatalogoDscFilter(String catalogoDscFilter) {
		this.catalogoDscFilter = catalogoDscFilter;
	}

	/**
	 * @return the catalogoEstFilter
	 */
	public String getCatalogoEstFilter() {
		return catalogoEstFilter;
	}

	/**
	 * @param catalogoEstFilter
	 *            the catalogoEstFilter to set
	 */
	public void setCatalogoEstFilter(String catalogoEstFilter) {
		this.catalogoEstFilter = catalogoEstFilter;
	}

	/**
	 * @return the selectedCatalogoActivo
	 */
	public boolean isSelectedCatalogoActivo() {
		return selectedCatalogoActivo;
	}

	/**
	 * @param selectedCatalogoActivo
	 *            the selectedCatalogoActivo to set
	 */
	public void setSelectedCatalogoActivo(boolean selectedCatalogoActivo) {
		this.selectedCatalogoActivo = selectedCatalogoActivo;
	}

	/**
	 * @return the nuevoCatalogoActivo
	 */
	public boolean isNuevoCatalogoActivo() {
		return nuevoCatalogoActivo;
	}

	/**
	 * @param nuevoCatalogoActivo
	 *            the nuevoCatalogoActivo to set
	 */
	public void setNuevoCatalogoActivo(boolean nuevoCatalogoActivo) {
		this.nuevoCatalogoActivo = nuevoCatalogoActivo;
	}

	/**
	 * @return the listaFiltered
	 */
	public List<CatGralDTO> getListaFiltered() {
		return listaFiltered;
	}

	/**
	 * @param listaFiltered
	 *            the listaFiltered to set
	 */
	public void setListaFiltered(List<CatGralDTO> listaFiltered) {
		this.listaFiltered = listaFiltered;
	}

	/**
	 * @param catGralFachada
	 *            the catGralFachada to set
	 */
	public void setCatGralFachada(CatGralFachada catGralFachada) {
		this.catGralFachada = catGralFachada;
	}

	/**
	 * @return the selectedClaveCatGral
	 */
	public String getSelectedClaveCatGral() {
		return selectedClaveCatGral;
	}

	/**
	 * @param selectedClaveCatGral the selectedClaveCatGral to set
	 */
	public void setSelectedClaveCatGral(String selectedClaveCatGral) {
		this.selectedClaveCatGral = selectedClaveCatGral;
	}

	/**
	 * @return the selectedDscCatGral
	 */
	public String getSelectedDscCatGral() {
		return selectedDscCatGral;
	}

	/**
	 * @param selectedDscCatGral the selectedDscCatGral to set
	 */
	public void setSelectedDscCatGral(String selectedDscCatGral) {
		this.selectedDscCatGral = selectedDscCatGral;
	}

	/**
	 * @return the selectedEstatusCatGral
	 */
	public String getSelectedEstatusCatGral() {
		return selectedEstatusCatGral;
	}

	/**
	 * @param selectedEstatusCatGral the selectedEstatusCatGral to set
	 */
	public void setSelectedEstatusCatGral(String selectedEstatusCatGral) {
		this.selectedEstatusCatGral = selectedEstatusCatGral;
	}

	@Override
	String getModulo() {
		return modulo;
	}

	/**
	 * @return the listSelectTipoCat
	 */
	public List<SelectItem> getListSelectTipoCat() {
		initListaSelectTipoCat();
		return listSelectTipoCat;
	}

	/**
	 * @param listSelectTipoCat the listSelectTipoCat to set
	 */
	public void setListSelectTipoCat(List<SelectItem> listSelectTipoCat) {
		this.listSelectTipoCat = listSelectTipoCat;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the catGralFachada
	 */
	public CatGralFachada getCatGralFachada() {
		return catGralFachada;
	}

	/**
	 * @return the idTipoCatGralNuevo
	 */
	public short getIdTipoCatGralNuevo() {
		return idTipoCatGralNuevo;
	}

	/**
	 * @param idTipoCatGralNuevo the idTipoCatGralNuevo to set
	 */
	public void setIdTipoCatGralNuevo(short idTipoCatGralNuevo) {
		this.idTipoCatGralNuevo = idTipoCatGralNuevo;
	}

	/**
	 * @return the idTipoCatGralEdit
	 */
	public short getIdTipoCatGralEdit() {
		return idTipoCatGralEdit;
	}

	/**
	 * @param idTipoCatGralEdit the idTipoCatGralEdit to set
	 */
	public void setIdTipoCatGralEdit(short idTipoCatGralEdit) {
		this.idTipoCatGralEdit = idTipoCatGralEdit;
	}

}
