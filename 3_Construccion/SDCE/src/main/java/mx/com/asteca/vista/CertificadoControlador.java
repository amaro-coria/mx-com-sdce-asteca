/**
 * 
 */
package mx.com.asteca.vista;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.comun.dto.CertificadoDTO;
import mx.com.asteca.comun.dto.EstatusDTO;
import mx.com.asteca.comun.dto.PermisosBooleanDTO;
import mx.com.asteca.fachada.CertificadoFachada;
import mx.com.asteca.fachada.FachadaException;

import org.primefaces.context.RequestContext;
import org.springframework.util.CollectionUtils;

/**
 * @author JAMARO
 * 
 */
@ManagedBean(name = Constantes.BEAN_ADMIN_CERTIFICADOS)
@ViewScoped
public class CertificadoControlador extends BaseController {

	private static final String modulo = Constantes.MODULO_ADMIN_CERTIFICADOS;

	@ManagedProperty("#{certificadoFachadaImpl}")
	private transient CertificadoFachada fachada;
	
	private PermisosBooleanDTO permisos;
	private List<SelectItem> listSelect1;
	private List<SelectItem> listSelect2;
	private List<SelectItem> listSelect3;
	private List<CertificadoDTO> listCertificados;
	private List<AlumnoDTO> listAlumnos;
	private List<EstatusDTO> listEstatus;
	
	private String selectedItem1;
	private Integer selectedItem2;
	private Short selectedItem3;
	
	private List<CertificadoDTO> listItems;
	private List<CertificadoDTO> filteredList;
	
	private CertificadoDTO itemSelected;
	
	private CertificadoDTO itemToCancel;
	
	private boolean isCancelled;
	
	private String motivo;
	private boolean enableMotivo;
	
	public void limpiarFiltrado(){
		listItems = null;
		listSelect1 = null;
		listSelect2 = null;
		listSelect3 = null;
		initListItems();
		initListSelect1();
		initListaSelect2(); 
		initListaSelect3();
	}
	
	public void buscarFiltrado(){
		try {
			List<CertificadoDTO> list = fachada.buscarFiltrado(selectedItem1, selectedItem2, selectedItem3);
			listItems = list;
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
	}
	
	public void cancelarAction(){
		itemToCancel = null;
	}
	
	public void aceptarAction(){
		if(itemToCancel != null){
			try {
				itemToCancel.setMotivoCancelacion(motivo);
				fachada.cancelarCertificado(itemToCancel);
				super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO, Constantes.ACCION_CANCELAR_CERTIFICADO_EXITOSO);
				super.addBitacora(Constantes.ACCION_CANCELAR_CERTIFICADO, Constantes.ACCION_CANCELAR_CERTIFICADO_EXITOSO);
				RequestContext.getCurrentInstance().execute("verDialog.hide()");
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_CANCELAR_CERTIFICADO);
				super.addBitacora(Constantes.ACCION_CANCELAR_CERTIFICADO, Constantes.ACCION_CANCELAR_CERTIFICADO_FALLIDO);
			}
		}else{
			RequestContext.getCurrentInstance().execute("verDialog.hide()");
		}
	}
	
	public void cancelarCertificadoAction(){
			itemToCancel = itemSelected;
	}
	
	private void initListaCertificados(){
		try{
			if(CollectionUtils.isEmpty(listCertificados)){
				listCertificados = fachada.getAll();
			}
		}catch(FachadaException e){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
	}
	
	private void initListaAlumnos(){
		try{
			if(CollectionUtils.isEmpty(listAlumnos)){
				listAlumnos = fachada.getAlumnos();
			}
		}catch(FachadaException e){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
	}
	
	private void initListaEstatus(){
		try{
			if(CollectionUtils.isEmpty(listEstatus)){
				listEstatus = fachada.getEstatus();
			}
		}catch(FachadaException e){
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
	}
	
	private void initListSelect1(){
		if(CollectionUtils.isEmpty(listSelect1)){
			listSelect1 = new ArrayList<SelectItem>();
			if(!CollectionUtils.isEmpty(getListCertificados())){
				for(CertificadoDTO dto : getListCertificados()){
					SelectItem item = new SelectItem(dto.getNoCertificado(), dto.getNoCertificado());
					listSelect1.add(item);
				}
			}
		}
	}

	private void initListaSelect2(){
		if(CollectionUtils.isEmpty(listSelect2)){
			listSelect2 = new ArrayList<SelectItem>();
			if(!CollectionUtils.isEmpty(getListAlumnos())){
				for(AlumnoDTO dto : getListAlumnos()){
					SelectItem item = new SelectItem(dto.getIdAlumno(), dto.getNombre());
					listSelect2.add(item);
				}
			}
		}
	}
	
	private void initListaSelect3(){
		if(CollectionUtils.isEmpty(listSelect3)){
			listSelect3 = new ArrayList<SelectItem>();
			if(!CollectionUtils.isEmpty(getListEstatus())){
				for(EstatusDTO dto : getListEstatus()){
					SelectItem item = new SelectItem(dto.getIdEstatus(), dto.getDescEstatus());
					listSelect3.add(item);
				}
			}
		}
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

	@Override
	String getModulo() {
		return modulo;
	}

	/**
	 * @return the fachada
	 */
	public CertificadoFachada getFachada() {
		return fachada;
	}

	/**
	 * @param fachada the fachada to set
	 */
	public void setFachada(CertificadoFachada fachada) {
		this.fachada = fachada;
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
	 * @return the listSelect2
	 */
	public List<SelectItem> getListSelect2() {
		initListaSelect2();
		return listSelect2;
	}

	/**
	 * @param listSelect2 the listSelect2 to set
	 */
	public void setListSelect2(List<SelectItem> listSelect2) {
		this.listSelect2 = listSelect2;
	}

	/**
	 * @return the listSelect3
	 */
	public List<SelectItem> getListSelect3() {
		initListaSelect3();
		return listSelect3;
	}

	/**
	 * @param listSelect3 the listSelect3 to set
	 */
	public void setListSelect3(List<SelectItem> listSelect3) {
		this.listSelect3 = listSelect3;
	}

	/**
	 * @return the listCertificados
	 */
	public List<CertificadoDTO> getListCertificados() {
		initListaCertificados();
		return listCertificados;
	}

	/**
	 * @param listCertificados the listCertificados to set
	 */
	public void setListCertificados(List<CertificadoDTO> listCertificados) {
		this.listCertificados = listCertificados;
	}

	/**
	 * @return the listAlumnos
	 */
	public List<AlumnoDTO> getListAlumnos() {
		initListaAlumnos();
		return listAlumnos;
	}

	/**
	 * @param listAlumnos the listAlumnos to set
	 */
	public void setListAlumnos(List<AlumnoDTO> listAlumnos) {
		this.listAlumnos = listAlumnos;
	}

	/**
	 * @return the listEstatus
	 */
	public List<EstatusDTO> getListEstatus() {
		initListaEstatus();
		return listEstatus;
	}

	/**
	 * @param listEstatus the listEstatus to set
	 */
	public void setListEstatus(List<EstatusDTO> listEstatus) {
		this.listEstatus = listEstatus;
	}

	/**
	 * @return the selectedItem1
	 */
	public String getSelectedItem1() {
		return selectedItem1;
	}

	/**
	 * @param selectedItem1 the selectedItem1 to set
	 */
	public void setSelectedItem1(String selectedItem1) {
		this.selectedItem1 = selectedItem1;
	}

	/**
	 * @return the selectedItem2
	 */
	public Integer getSelectedItem2() {
		return selectedItem2;
	}

	/**
	 * @param selectedItem2 the selectedItem2 to set
	 */
	public void setSelectedItem2(Integer selectedItem2) {
		this.selectedItem2 = selectedItem2;
	}

	/**
	 * @return the selecteItem3
	 */
	public Short getSelectedItem3() {
		return selectedItem3;
	}

	/**
	 * @param selecteItem3 the selecteItem3 to set
	 */
	public void setSelectedItem3(Short selecteItem3) {
		this.selectedItem3 = selecteItem3;
	}

	/**
	 * @return the listItems
	 */
	public List<CertificadoDTO> getListItems() {
		initListItems();
		return listItems;
	}

	/**
	 * @param listItems the listItems to set
	 */
	public void setListItems(List<CertificadoDTO> listItems) {
		this.listItems = listItems;
	}

	/**
	 * @return the filteredList
	 */
	public List<CertificadoDTO> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList the filteredList to set
	 */
	public void setFilteredList(List<CertificadoDTO> filteredList) {
		this.filteredList = filteredList;
	}

	/**
	 * @return the itemSelected
	 */
	public CertificadoDTO getItemSelected() {
		return itemSelected;
	}

	/**
	 * @param itemSelected the itemSelected to set
	 */
	public void setItemSelected(CertificadoDTO itemSelected) {
		this.itemSelected = itemSelected;
	}

	/**
	 * @return the itemToCancel
	 */
	public CertificadoDTO getItemToCancel() {
		return itemToCancel;
	}

	/**
	 * @param itemToCancel the itemToCancel to set
	 */
	public void setItemToCancel(CertificadoDTO itemToCancel) {
		this.itemToCancel = itemToCancel;
	}

	/**
	 * @return the isCancelled
	 */
	public boolean isCancelled() {
		if(itemSelected == null){
			return isCancelled = true;
		}else{
			if(itemSelected.getFechaCancelacion() == null){
				return isCancelled = false;
			}else{
				isCancelled = true;
			}
		}
		return isCancelled;
	}

	/**
	 * @param isCancelled the isCancelled to set
	 */
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		if(itemSelected == null){
			motivo = "";
		}else{
			if(itemSelected.getMotivoCancelacion() == null || itemSelected.getMotivoCancelacion().isEmpty()){
				motivo = "";
			}else{
				motivo = itemSelected.getMotivoCancelacion();
			}
		}
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the enableMotivo
	 */
	public boolean isEnableMotivo() {
		if(itemSelected == null){
			enableMotivo = true;
		}else{
			if(itemSelected.getMotivoCancelacion() == null || itemSelected.getMotivoCancelacion().isEmpty()){
				enableMotivo = false;
			}else{
				enableMotivo = true;
			}
		}
			
		return enableMotivo;
	}

	/**
	 * @param enableMotivo the enableMotivo to set
	 */
	public void setEnableMotivo(boolean enableMotivo) {
		this.enableMotivo = enableMotivo;
	}

}
