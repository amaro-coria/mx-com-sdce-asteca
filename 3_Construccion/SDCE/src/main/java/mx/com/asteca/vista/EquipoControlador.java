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
import mx.com.asteca.fachada.CatGralFachada;
import mx.com.asteca.fachada.EquipoFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.impl.CatGralFachadaImpl;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = Constantes.BEAN_EQUIPO)
@ViewScoped
public class EquipoControlador extends BaseController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = LoggerFactory.getLogger(EquipoControlador.class);
	
	private List<EquipoDTO> listaEquipo;
	private List<EquipoDTO> filterEquipo;

	@ManagedProperty("#{equipoFachadaImpl}")
	private EquipoFachada equipoFachada;
	
	@ManagedProperty("#{catGralFachadaImpl}")
	private CatGralFachada tipoEquipoFachada;
	
	private List<SelectItem> listaSelectTipos;
	
	private EquipoDTO equipoSelected;
	private String clave;
	private String descripcion;
	private Long tipoEquipo;
	private boolean dialogVisible;
	/**
	 * 
	 * @throws FachadaException
	 */
	private void initListaEquipo() {
		if(CollectionUtils.isEmpty(listaEquipo)){		
			try {
				if(equipoFachada != null){	
					listaEquipo = equipoFachada.getAll();
				}
				else{
					listaEquipo = new ArrayList<EquipoDTO>();
				}
			} catch (FachadaException e) {
				listaEquipo = new ArrayList<EquipoDTO>();
				LOGGER.error(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	/**
	 * 
	 * @throws FachadaException
	 */
	private void initTiposEquipos() {
		if(CollectionUtils.isEmpty(listaSelectTipos)){		
			try {
				List<CatGralDTO> tiposAux = tipoEquipoFachada.findByCve("TIPOEQUIPO");
				if(tiposAux != null){
					listaSelectTipos = new ArrayList<SelectItem>();
					for (CatGralDTO tiposEquipo : tiposAux) {
						listaSelectTipos.add(new SelectItem(tiposEquipo.getIdCatGral(), tiposEquipo.getDsc()));
					}
				}
				else{
					listaSelectTipos = new ArrayList<SelectItem>();
				}
			} catch (FachadaException e) {
				LOGGER.error(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	/**
	 * 
	 * @param e
	 */
	public void limpiarFiltrado(ActionEvent e) {
		listaEquipo = null;
		listaSelectTipos = null;
		
		clave = null;
		descripcion = null;
		tipoEquipo = null;
		equipoSelected = null;
		filterEquipo = null;
		
		initListaEquipo();
		initTiposEquipos();
	}
	/**
	 * 
	 * @param e
	 */
	public void buscarFiltrado(ActionEvent e){
		try {
			if(clave != null && clave.trim().length() > 0
					&& tipoEquipo != null && tipoEquipo > 0){
//				listaNotificacion = notificacionFachada.getNotificacionByEstadoAndTipo(Selected, tipoSelected);
			}
			else if(clave != null && clave.trim().length() > 0){
//				listaNotificacion = notificacionFachada.getNotificacionByEstado(clave);
			}
			else if(tipoEquipo != null && tipoEquipo > 0){
//				listaEquipo = equipoFachada.getNotificacionByTipo(tipoEquipo);
			}
			else{
					listaEquipo = equipoFachada.getAll();
			}
		}
		catch (FachadaException e1) {
			e1.printStackTrace();
		}
	}
	
	public void ver(ActionEvent e){
		dialogVisible = equipoSelected == null ? false : true; 
	}
	
	/**
	 * @return the listaEquipo
	 */
	public List<EquipoDTO> getListaEquipo() {
		initListaEquipo();
		return listaEquipo;
	}
	
	/**
	 * @param listaEquipo the listaEquipo to set
	 */
	public void setListaEquipo(List<EquipoDTO> listaEquipo) {
		this.listaEquipo = listaEquipo;
	}

	/**
	 * @param equipoFachada the equipoFachada to set
	 */
	public void setEquipoFachada(EquipoFachada equipoFachada) {
		this.equipoFachada = equipoFachada;
	}
	
	/**
	 * @param tipoEquipoFachada the tipoEquipoFachada to set
	 */
	public void setTipoEquipoFachada(CatGralFachada tipoEquipoFachada) {
		this.tipoEquipoFachada = tipoEquipoFachada;
	}
	
	/**
	 * @return the tipos
	 */
	public List<SelectItem> getListaSelectTipos() {
		initTiposEquipos();
		return listaSelectTipos;
	}

	/**
	 * @param tipos the tipos to set
	 */
	public void setListaSelectTipos(List<SelectItem> tipos) {
		this.listaSelectTipos = tipos;
	}

	/**
	 * @return the equipocacionSelected
	 */
	public EquipoDTO getEquipoSelected() {
		return equipoSelected;
	}

	/**
	 * @param setEquipoSelected the equipoSelected to set
	 */
	public void setEquipoSelected(EquipoDTO equipoSelected) {
		this.equipoSelected = equipoSelected;
	}

	/**
	 * @return the filterEquipo
	 */
	public List<EquipoDTO> getFilterEquipo() {
		return filterEquipo;
	}

	/**
	 * @param filterEquipo the filterEquipo to set
	 */
	public void setFilterEquipo(List<EquipoDTO> filterEquipo) {
		this.filterEquipo = filterEquipo;
	}	
	/**
	 * @return the getClave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param setClave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the tipoEquipo
	 */
	public Long getTipoEquipo() {
		return tipoEquipo;
	}

	/**
	 * @param setTipoEquipo the tipoEquipo to set
	 */
	public void setTipoEquipo(Long tipoEquipo) {
		this.tipoEquipo = tipoEquipo;
	}

	/**
	 * @return the dialogVisible
	 */
	public boolean getDialogVisible() {
		return dialogVisible;
	}

	/**
	 * @param dialogVisible the dialogVisible to set
	 */
	public void setDialogVisible(boolean dialogVisible) {
		this.dialogVisible = dialogVisible;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
