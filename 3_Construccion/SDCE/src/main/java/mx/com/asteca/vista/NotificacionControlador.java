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
import mx.com.asteca.comun.dto.NotificacionDTO;
import mx.com.asteca.comun.dto.NotificacionEstadoDTO;
import mx.com.asteca.comun.dto.NotificacionTipoDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.NotificacionEstadoFachada;
import mx.com.asteca.fachada.NotificacionFachada;
import mx.com.asteca.fachada.NotificacionTipoFachada;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = Constantes.BEAN_NOTIFICACION)
@ViewScoped
public class NotificacionControlador extends BaseController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = LoggerFactory.getLogger(NotificacionControlador.class);
	
	private List<NotificacionDTO> listaNotificacion;
	private List<NotificacionDTO> filNotificacion;

	@ManagedProperty("#{notificacionFachadaImpl}")
	private transient NotificacionFachada notificacionFachada;
	
	@ManagedProperty("#{notificacionEstadoFachadaImpl}")
	private NotificacionEstadoFachada notificacionEstadoFachada;
	
	@ManagedProperty("#{notificacionTipoFachadaImpl}")
	private NotificacionTipoFachada notificacionTipoFachada;
	
	private List<SelectItem> listaSelectEstados;
	private List<SelectItem> listaSelectTipos;
	
	private NotificacionDTO notificacionSel;
	private Long estadoSelected;
	private Long tipoSelected;
	private boolean dialogVisible;
	/**
	 * 
	 * @throws FachadaException
	 */
	private void initListaNotificacion() {
		if(CollectionUtils.isEmpty(listaNotificacion)){		
			try {
				if(notificacionFachada != null){	
					listaNotificacion = notificacionFachada.getAll();
				}
				else{
					listaNotificacion = new ArrayList<NotificacionDTO>();
				}
			} catch (FachadaException e) {
				listaNotificacion = new ArrayList<NotificacionDTO>();
				LOGGER.error(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	/**
	 * 
	 * @throws FachadaException
	 */
	private void initNotificacionEstados(){
		if(CollectionUtils.isEmpty(listaSelectEstados)){		
			try {
				List<NotificacionEstadoDTO> estadosAux = notificacionEstadoFachada.getAll();
				if(estadosAux != null){
					listaSelectEstados = new ArrayList<SelectItem>();
					for (NotificacionEstadoDTO notificacionEdos : estadosAux) {
						listaSelectEstados.add(new SelectItem(notificacionEdos.getIdNotifEdo(), 
											notificacionEdos.getNombre()));
					}
				}
				else{
					listaSelectEstados = new ArrayList<SelectItem>();
				}
			} catch (FachadaException e) {
				LOGGER.error(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}
	
	/**
	 * 
	 * @throws FachadaException
	 */
	private void initNotificacionTipos() {
		if(CollectionUtils.isEmpty(listaSelectTipos)){		
			try {
				List<NotificacionTipoDTO> tiposAux = notificacionTipoFachada.getAll();
				if(tiposAux != null){
					listaSelectTipos = new ArrayList<SelectItem>();
					for (NotificacionTipoDTO notificacionTps : tiposAux) {
						listaSelectTipos.add(new SelectItem(notificacionTps.getIdNotifTipo(), notificacionTps.getNombre()));
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
		listaNotificacion = null;
		listaSelectEstados = null;
		listaSelectTipos = null;
		
		estadoSelected = null;
		tipoSelected = null;
		notificacionSel = null;
		filNotificacion = null;
		
		initListaNotificacion();
		initNotificacionEstados();
		initNotificacionTipos();
	}
	/**
	 * 
	 * @param e
	 */
	public void buscarFiltrado(ActionEvent e){
		try {
			if(estadoSelected != null && estadoSelected > 0
					&& tipoSelected != null && tipoSelected > 0){
				listaNotificacion = notificacionFachada.getNotificacionByEstadoAndTipo(estadoSelected, tipoSelected);
			}
			else if(estadoSelected != null && estadoSelected > 0){
				listaNotificacion = notificacionFachada.getNotificacionByEstado(estadoSelected);
			}
			else if(tipoSelected != null && tipoSelected > 0){
				listaNotificacion = notificacionFachada.getNotificacionByTipo(tipoSelected);
			}
			else{
					listaNotificacion = notificacionFachada.getAll();
			}
		}
		catch (FachadaException e1) {
			e1.printStackTrace();
		}
	}
	
	public void ver(ActionEvent e){
		dialogVisible = notificacionSel == null ? false : true; 
		System.out.println("VISIBLE--->" + dialogVisible);
	}
	
	/**
	 * @return the listaNotificacion
	 */
	public List<NotificacionDTO> getListaNotificacion() {
		initListaNotificacion();
		return listaNotificacion;
	}
	
	/**
	 * @param listaNotificacion the listaNotificacion to set
	 */
	public void setListaNotificacion(List<NotificacionDTO> listaNotificacion) {
		this.listaNotificacion = listaNotificacion;
	}

	/**
	 * @param notificacionFachada the notificacionFachada to set
	 */
	public void setNotificacionFachada(NotificacionFachada NotificacionFachada) {
		this.notificacionFachada = NotificacionFachada;
	}
	
	/**
	 * @param notificacionEstadoFachada the notificacionEstadoFachada to set
	 */
	public void setNotificacionEstadoFachada(NotificacionEstadoFachada notificacionEstadoFachada) {
		this.notificacionEstadoFachada = notificacionEstadoFachada;
	}
	
	/**
	 * @param notificacionTipoFachada the notificacionTipoFachada to set
	 */
	public void setNotificacionTipoFachada(NotificacionTipoFachada notificacionTipoFachada) {
		this.notificacionTipoFachada = notificacionTipoFachada;
	}

	/**
	 * @return the estados
	 */
	public List<SelectItem> getListaSelectEstados() {
		initNotificacionEstados();
		return listaSelectEstados;
	}

	/**
	 * @param estados the estados to set
	 */
	public void setListaSelectEstados(List<SelectItem> estados) {
		this.listaSelectEstados = estados;
	}	
	
	/**
	 * @return the tipos
	 */
	public List<SelectItem> getListaSelectTipos() {
		initNotificacionTipos();
		return listaSelectTipos;
	}

	/**
	 * @param tipos the tipos to set
	 */
	public void setListaSelectTipos(List<SelectItem> tipos) {
		this.listaSelectTipos = tipos;
	}

	/**
	 * @return the notificacionSel
	 */
	public NotificacionDTO getNotificacionSel() {
		return notificacionSel;
	}

	/**
	 * @param notificacionSel the notificacionSel to set
	 */
	public void setNotificacionSel(NotificacionDTO notificacionSel) {
		this.notificacionSel = notificacionSel;
	}

	/**
	 * @return the filNotificacion
	 */
	public List<NotificacionDTO> getFilNotificacion() {
		return filNotificacion;
	}

	/**
	 * @param filNotificacion the filNotificacion to set
	 */
	public void setFilNotificacion(List<NotificacionDTO> filNotificacion) {
		this.filNotificacion = filNotificacion;
	}	
	/**
	 * @return the estadoSelected
	 */
	public Long getEstadoSelected() {
		return estadoSelected;
	}

	/**
	 * @param estadoSelected the estadoSelected to set
	 */
	public void setEstadoSelected(Long estadoSelected) {
		this.estadoSelected = estadoSelected;
	}

	/**
	 * @return the tipoSelected
	 */
	public Long getTipoSelected() {
		return tipoSelected;
	}

	/**
	 * @param tipoSelected the tipoSelected to set
	 */
	public void setTipoSelected(Long tipoSelected) {
		this.tipoSelected = tipoSelected;
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
}
