package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
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

	@ManagedProperty("#{notificacionFachadaImpl}")
	private NotificacionFachada notificacionFachada;
	
	@ManagedProperty("#{notificacionEstadoFachadaImpl}")
	private NotificacionEstadoFachada notificacionEstadoFachada;
	
	@ManagedProperty("#{notificacionTipoFachadaImpl}")
	private NotificacionTipoFachada notificacionTipoFachada;
	
	private Long estadoId;
	private List<SelectItem> estados;
	
	private Long tipoId;
	private List<SelectItem> tipos;
	
	private Long notificacionSel;
	/**
	 * 
	 * @throws FachadaException
	 */
	private void initListaNotificacion() throws FachadaException{
		if(CollectionUtils.isEmpty(listaNotificacion)){		
			LOGGER.debug("LLAMANDO A NOTIFICACION FACHADA:"+notificacionFachada);
			if(notificacionFachada == null){
				LOGGER.debug("NOTIFICACION FACHADA NULO");
			}
			listaNotificacion = notificacionFachada.getAll();
			if(listaNotificacion != null){
//				for (NotificacionDTO notificacion : listaNotificacion) {
//					LOGGER.debug("INICIALIZANDO NOTIFICACION: "+notificacion.getMensaje());
//				}
			}
			else{
				listaNotificacion = new ArrayList<NotificacionDTO>();
			}
		}else{
//			LOGGER.debug("LISTA YA INICIALIZADA");
//			LOGGER.debug("REGRESANDO UNA LISTA CON ELEMENTOS:"+listaNotificacion.size());
		}
	}
	
	/**
	 * 
	 * @throws FachadaException
	 */
	private void initNotificacionEstados() throws FachadaException{
		if(CollectionUtils.isEmpty(estados)){		
			LOGGER.debug("LLAMANDO A NOTIFICACION ESTADOS FACHADA: " + notificacionEstadoFachada);
			if(notificacionEstadoFachada == null){
				LOGGER.debug("NOTIFICACION ESTADO FACHADA NULO");
			}
			List<NotificacionEstadoDTO> estadosAux = notificacionEstadoFachada.getAll();
			if(estadosAux != null){
				estados = new ArrayList<SelectItem>();
				for (NotificacionEstadoDTO notificacionEdos : estadosAux) {
//					LOGGER.debug("INICIALIZANDO NOTIFICACION ESTADOS: "+notificacionEdos.getNombre());
					estados.add(new SelectItem(notificacionEdos.getIdNotifEdo(), notificacionEdos.getNombre()));
				}
			}
			else{
				estados = new ArrayList<SelectItem>();
			}
		}else{
//			LOGGER.debug("LISTA YA INICIALIZADA ESTADOS");
//			LOGGER.debug("REGRESANDO UNA LISTA CON ELEMENTOS ESTADOS:"+estados.size());
		}
	}
	
	/**
	 * 
	 * @throws FachadaException
	 */
	private void initNotificacionTipos() throws FachadaException{
		if(CollectionUtils.isEmpty(tipos)){		
			LOGGER.debug("LLAMANDO A NOTIFICACION TIPOS FACHADA: " + notificacionTipoFachada);
			if(notificacionTipoFachada == null){
				LOGGER.debug("NOTIFICACION TIPO FACHADA NULO");
			}
			List<NotificacionTipoDTO> tiposAux = notificacionTipoFachada.getAll();
			if(tiposAux != null){
				tipos = new ArrayList<SelectItem>();
				for (NotificacionTipoDTO notificacionTps : tiposAux) {
//					LOGGER.debug("INICIALIZANDO NOTIFICACION TIPOS: "+notificacionTps.getNombre());
					tipos.add(new SelectItem(notificacionTps.getIdNotifTipo(), notificacionTps.getNombre()));
				}
			}
			else{
				tipos = new ArrayList<SelectItem>();
			}
		}else{
//			LOGGER.debug("LISTA YA INICIALIZADA TIPOS");
//			LOGGER.debug("REGRESANDO UNA LISTA CON ELEMENTOS TIPOS:"+tipos.size());
		}
	}
	
	public void ver(ActionEvent e){
		LOGGER.debug("REGISTRO SELECCIONADO: " + notificacionSel);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void actualizaEstado(ValueChangeEvent e){
		estadoId = (Long) e.getNewValue();
	}
	
	/**
	 * 
	 * @param e
	 */
	public void actualizaTipo(ValueChangeEvent e){
		tipoId = (Long) e.getNewValue();
	}
	
	public void actualizaSel(ValueChangeEvent e){
		notificacionSel = (Long) e.getNewValue();
		LOGGER.debug("REGISTRO : " + notificacionSel);
	}
	
	/**
	 * @return the listaNotificacion
	 */
	public List<NotificacionDTO> getListaNotificacion() {
		try {
			initListaNotificacion();
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
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
	 * @return the estadoId
	 */
	public Long getEstadoId() {
		return estadoId;
	}

	/**
	 * @param estadoId the estadoId to set
	 */
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}
	
	/**
	 * @return the tipoId
	 */
	public Long getTipoId() {
		return tipoId;
	}

	/**
	 * @param tipoId the tipoId to set
	 */
	public void setTipoId(Long tipoId) {
		this.tipoId = tipoId;
	}


	/**
	 * @return the estados
	 */
	public List<SelectItem> getEstados() {
		try {
			initNotificacionEstados();
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
		return estados;
	}

	/**
	 * @param estados the estados to set
	 */
	public void setEstados(List<SelectItem> estados) {
		this.estados = estados;
	}	
	
	/**
	 * @return the tipos
	 */
	public List<SelectItem> getTipos() {
		try {
			initNotificacionTipos();
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
		return tipos;
	}

	/**
	 * @param tipos the tipos to set
	 */
	public void setTipos(List<SelectItem> tipos) {
		this.tipos = tipos;
	}

	/**
	 * @return the notificacionSel
	 */
	public Long getNotificacionSel() {
		return notificacionSel;
	}

	/**
	 * @param notificacionSel the notificacionSel to set
	 */
	public void setNotificacionSel(Long notificacionSel) {
		this.notificacionSel = notificacionSel;
	}	
}
