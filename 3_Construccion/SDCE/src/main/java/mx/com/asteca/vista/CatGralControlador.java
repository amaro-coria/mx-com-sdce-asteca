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

import org.apache.commons.collections.CollectionUtils;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.EstadoDTO;
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

	/* Constructor default */

	public CatGralControlador() {
		catalogoSelected = new CatGralDTO();
		catalogoNuevo = new CatGralDTO();
	}

	private void initListaSelectCatalogosCve() {
		if (CollectionUtils.isEmpty(listaSelectCatalogosCve)) {
			listaSelectCatalogosCve = new ArrayList<SelectItem>();
			for (CatGralDTO dto : getListaCatalogos()) {
				SelectItem item = new SelectItem(dto.getCveRegistro(),
						dto.getCveRegistro());
				listaSelectCatalogosCve.add(item);
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
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_DELETE_REGISTRO);
			return;
		}
		setSelectedClaveCatGral("");
		setSelectedDscCatGral("");
		setSelectedEstatusCatGral("");
		super.addInfoMessage(Constantes.DELETE_REGISTRO_EXITOSO);
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
		try {
			catGralFachada.update(catalogoSelected);
			int indexListFilter = listaCatalogos.indexOf(catalogoSelected);
			if(indexListFilter > 0){
				listaCatalogos.set(indexListFilter, catalogoSelected);
			}
			cambiaDescSelect();
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_UPDATE_REGISTRO);
			return;
		}
		super.addInfoMessage(Constantes.UPDATE_REGISTRO_EXITOSO);
	}
	
	/**
	 * Guarda el nuevo catalogo en BD
	 * @param e
	 */
	public void saveCat(ActionEvent e) {
		catalogoNuevo
				.setActivo(nuevoCatalogoActivo == true ? (short) 1 : (short) 0);
		try {
			catGralFachada.save(catalogoNuevo);
			listaCatalogos.add(catalogoNuevo);
			cambiaDescSelect();
			//refreshEstados();
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NUEVO_REGISTRO);
			return;
		}
		catalogoNuevo = new CatGralDTO();
		super.addInfoMessage(Constantes.NUEVO_REGISTRO_EXITOSO);
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

}
