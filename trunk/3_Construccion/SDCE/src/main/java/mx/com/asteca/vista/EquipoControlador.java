package mx.com.asteca.vista;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EquipoDTO;
import mx.com.asteca.fachada.EquipoFachada;
import mx.com.asteca.fachada.FachadaException;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean(name = Constantes.BEAN_EQUIPO)
@ViewScoped
public class EquipoControlador extends BaseController {

	private List<EquipoDTO> listaEquipo;

	@Autowired
	private EquipoFachada equipoFachada;

	private void initListaEquipo() throws FachadaException {
		if (CollectionUtils.isEmpty(listaEquipo)) {
			listaEquipo = equipoFachada.getAllEquipo();
		}
	}

	/**
	 * @return the listaEquipo
	 */
	public List<EquipoDTO> getListaEquipo() {
		try {
			initListaEquipo();
		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
		}
		return listaEquipo;
	}

	/**
	 * @param listaEquipo
	 *            the listaEquipo to set
	 */
	public void setListaEquipo(List<EquipoDTO> listaEquipo) {
		this.listaEquipo = listaEquipo;
	}

}
