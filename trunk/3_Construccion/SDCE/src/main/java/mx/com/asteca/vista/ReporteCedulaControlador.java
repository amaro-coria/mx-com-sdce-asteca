/**
 * 
 */
package mx.com.asteca.vista;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.PermisosBooleanDTO;
import net.sf.jasperreports.engine.JRException;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = Constantes.BEAN_REPORTE_CEDULA)
@ViewScoped
public class ReporteCedulaControlador extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private static final String modulo = Constantes.MODULO_REPORTE_CEDULA;
	private String url;
	private FacesContext context = FacesContext.getCurrentInstance();

	private static Logger LOGGER = LoggerFactory
			.getLogger(ReporteCedulaControlador.class);

	private PermisosBooleanDTO permisos;

	/**
	 * @return the permisos
	 */
	public PermisosBooleanDTO getPermisos() {
		return permisos;
	}

	@PostConstruct
	public void init() {
		context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String requestURL = request.getRequestURL().toString();
		setUrl(requestURL.substring(0, requestURL.lastIndexOf("faces")));

	}

	public void mostrarReporte(String nombreCedula) {
		RequestContext.getCurrentInstance().execute(
				"window.open('" + url + "Reportes?name="+nombreCedula+"&nombreCedula="+nombreCedula+ "')");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	String getModulo() {
		return modulo;
	}
}
