package mx.com.asteca.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.NotificacionDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.NotificacionFachada;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = Constantes.BEAN_NOTIFICACION)
@ViewScoped
public class NotificacionControlador extends BaseController{

	private static Logger LOGGER = LoggerFactory.getLogger(NotificacionControlador.class);
	
	private List<NotificacionDTO> listaNotificacion;

	@ManagedProperty("#{notificacionFachadaImpl}")
	private NotificacionFachada notificacionFachada;
	
	private void initListaNotificacion() throws FachadaException{
		if(CollectionUtils.isEmpty(listaNotificacion)){		
			LOGGER.debug("LLAMANDO A NOTIFICACION FACHADA:"+notificacionFachada);
			if(notificacionFachada == null){
				LOGGER.debug("NOTIFICACION FACHADA NULO");
			}
			listaNotificacion = notificacionFachada.getAll();
			if(listaNotificacion != null){
				for (NotificacionDTO notificacion : listaNotificacion) {
					LOGGER.debug("INICIALIZANDO NOTIFICACION: "+notificacion.getMensaje());
				}
			}
			else{
				listaNotificacion = new ArrayList<NotificacionDTO>();
			}
		}else{
			LOGGER.debug("LISTA YA INICIALIZADA");
			LOGGER.debug("REGRESANDO UNA LISTA CON ELEMENTOS:"+listaNotificacion.size());
		}
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
}