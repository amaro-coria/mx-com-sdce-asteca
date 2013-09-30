/**
 * 
 */
package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.fachada.AlumnoFachada;
import mx.com.asteca.fachada.FachadaException;

/**
 * @author ribarra
 * 
 */
@ManagedBean(name = Constantes.BEAN_ADMINISTRATIVOS)
@ViewScoped
public class CedulaControlador extends BaseController implements Serializable {
	@ManagedProperty("#{alumnoFachadaImpl}")
	private transient AlumnoFachada fachada;
	private List<AlumnoDTO> listaItems;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String modulo=Constantes.MODULO_CEDULAS;

	private void reporteAlumnos() {
		try {
			setListaItems(fachada.getAlumnosDatosBasicos());
		} catch (FachadaException e) {
			this.addErrorMessage("Existio un error al consultar la lista de Alumnos");
			e.printStackTrace();
		}
	}

	public List<AlumnoDTO> getListaItems() {
		return listaItems;
	}

	public void setListaItems(List<AlumnoDTO> listaItems) {
		this.listaItems = listaItems;
	}

	@Override
	String getModulo() {
		return modulo;
	}
}
