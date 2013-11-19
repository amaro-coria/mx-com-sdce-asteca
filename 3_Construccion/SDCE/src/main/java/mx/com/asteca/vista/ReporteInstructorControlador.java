/**
 * 
 */
package mx.com.asteca.vista;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.MateriaDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.MateriaFachada;
import net.sf.jasperreports.engine.JRException;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = Constantes.BEAN_REPORTE_INSTRUCTOR)
@ViewScoped
public class ReporteInstructorControlador extends BaseController implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String modulo = Constantes.MODULO_REPORTE_INSTRUCTOR;
	private String url;
	private FacesContext context = FacesContext.getCurrentInstance();

	@ManagedProperty("#{materiaFachadaImpl}")
	private MateriaFachada materiaFachada;
	private List<MateriaDTO> listaMaterias;

	private static Logger LOGGER = LoggerFactory
			.getLogger(ReporteAlumnoControlador.class);
	
	@PostConstruct
	public void init() {
		context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String requestURL = request.getRequestURL().toString();
		setUrl(requestURL.substring(0, requestURL.lastIndexOf("faces")));

	}

	/**
	 * 
	 * @throws FachadaException
	 */
	private void initListaMaterias() {
		if (CollectionUtils.isEmpty(listaMaterias)) {
			try {
				LOGGER.debug("BUSCANDO... ");
				if (materiaFachada != null) {
					listaMaterias = materiaFachada.getAll();
				} else {
					listaMaterias = new ArrayList<MateriaDTO>();
				}
			} catch (FachadaException e) {
				listaMaterias = new ArrayList<MateriaDTO>();
				LOGGER.error(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	public void mostrarReporte() throws JRException, IOException,
			ClassNotFoundException {
		initListaMaterias();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("instructuresReporte", listaMaterias);
		RequestContext.getCurrentInstance().execute(
				"window.open('" + url
						+ "ReporteInstructores?name=Reporte de Instructores"
						+ "')");
	}

	@Override
	String getModulo() {
		return modulo;
	}

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MateriaFachada getMateriaFachada() {
		return materiaFachada;
	}

	public void setMateriaFachada(MateriaFachada materiaFachada) {
		this.materiaFachada = materiaFachada;
	}

	public List<MateriaDTO> getListaMaterias() {
		return listaMaterias;
	}

	public void setListaMaterias(List<MateriaDTO> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}

}
