package mx.com.asteca.vista;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.NotificacionDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.NotificacionFachada;
import mx.com.asteca.fachada.NotificacionFachadaImpl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean(name = Constantes.BEAN_NOTIFICACION)
@ViewScoped
public class NotificacionControlador extends BaseController {

	private List<NotificacionDTO> listaNotificacion;

	@Autowired
	private NotificacionFachada notificacionFachada;

	private void initListaNotificacion() throws FachadaException {
		if (listaNotificacion == null || CollectionUtils.isEmpty(listaNotificacion)) {
			notificacionFachada = new NotificacionFachadaImpl();
			listaNotificacion = notificacionFachada.getAllNotificacion();
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
	 * @param listaNotificacion
	 *            the listaNotificacion to set
	 */
	public void setListaNotificacion(List<NotificacionDTO> listaNotificacion) {
		this.listaNotificacion = listaNotificacion;
	}

}
