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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.PermisosBooleanDTO;
import mx.com.asteca.fachada.AlumnoFachada;
import mx.com.asteca.fachada.CatGralFachada;
import mx.com.asteca.fachada.FachadaException;
import net.sf.jasperreports.engine.JRException;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = Constantes.BEAN_REPORTE_ALUMNO)
@SessionScoped
public class ReporteAlumnoControlador extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private static final String modulo = Constantes.MODULO_REPORTE_ALUMNO;
	private String url;
	private FacesContext context = FacesContext.getCurrentInstance();

	@ManagedProperty("#{alumnoFachadaImpl}")
	private AlumnoFachada alumnosFachada;
	private List<AlumnoDTO> listaAlumnos;

	@ManagedProperty("#{catGralFachadaImpl}")
	private CatGralFachada fachadaCatGral;
	private List<SelectItem> listaSelectArea;

	private Integer areaSelected;

	private static Logger LOGGER = LoggerFactory
			.getLogger(ReporteAlumnoControlador.class);

	private PermisosBooleanDTO permisos;

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

	@PostConstruct
	public void init() {
		context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String requestURL = request.getRequestURL().toString();
		setUrl(requestURL.substring(0, requestURL.lastIndexOf("faces")));

		initListaCatGral();
		setPermisos(super.stablishSessionPermissions());
	}

	/**
	 * 
	 * @throws FachadaException
	 */
	private void initListaAlumnos() {
		if (CollectionUtils.isEmpty(listaAlumnos)) {
			try {
				LOGGER.debug("BUSCANDO... ");
				if (alumnosFachada != null) {
					listaAlumnos = alumnosFachada.findByArea(areaSelected);
				} else {
					listaAlumnos = new ArrayList<AlumnoDTO>();
				}
			} catch (FachadaException e) {
				listaAlumnos = new ArrayList<AlumnoDTO>();
				LOGGER.error(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	/**
	 * Inicializa la lista de catalogoGeneral con la lista de tipos de equipos
	 */
	private void initListaCatGral() {

		if (CollectionUtils.isEmpty(listaSelectArea)) {
			try {
				List<CatGralDTO> listaCatGral = fachadaCatGral
						.findByTiposArea();
				if (listaCatGral != null) {
					listaSelectArea = new ArrayList<SelectItem>();
					for (CatGralDTO areas : listaCatGral) {
						listaSelectArea.add(new SelectItem(
								areas.getIdCatGral(), areas.getDsc()));
					}
				} else {
					listaSelectArea = new ArrayList<SelectItem>();
				}
			} catch (FachadaException e) {
				LOGGER.error(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	public void mostrarReporte() throws JRException, IOException,
			ClassNotFoundException {
		initListaAlumnos();	
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("alumnosReporte", listaAlumnos);
		RequestContext.getCurrentInstance().execute(
				"window.open('" + url + "Reportes?name=Reporte de Alumnos"
						+ "')");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the listaAlumnos
	 */
	public List<AlumnoDTO> getListaAlumnos() {
		return listaAlumnos;
	}

	/**
	 * @param listaAlumnos
	 *            the listaAlumnos to set
	 */
	public void setListaAlumnos(List<AlumnoDTO> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

	/**
	 * @return the alumnosFachada
	 */
	public AlumnoFachada getAlumnosFachada() {
		return alumnosFachada;
	}

	/**
	 * @param alumnosFachada
	 *            the alumnosFachada to set
	 */
	public void setAlumnosFachada(AlumnoFachada alumnosFachada) {
		this.alumnosFachada = alumnosFachada;
	}

	/**
	 * @return the areaSelected
	 */
	public Integer getAreaSelected() {
		return areaSelected;
	}

	/**
	 * @param areaSelected
	 *            the areaSelected to set
	 */
	public void setAreaSelected(Integer areaSelected) {
		this.areaSelected = areaSelected;
	}

	/**
	 * @return the fachadaCatGral
	 */
	public CatGralFachada getFachadaCatGral() {
		return fachadaCatGral;
	}

	/**
	 * @param fachadaCatGral
	 *            the fachadaCatGral to set
	 */
	public void setFachadaCatGral(CatGralFachada fachadaCatGral) {
		this.fachadaCatGral = fachadaCatGral;
	}

	/**
	 * @return the listaSelectArea
	 */
	public List<SelectItem> getListaSelectArea() {
		return listaSelectArea;
	}

	/**
	 * @param listaSelectArea
	 *            the listaSelectArea to set
	 */
	public void setListaSelectArea(List<SelectItem> listaSelectArea) {
		this.listaSelectArea = listaSelectArea;
	}

	@Override
	String getModulo() {
		return modulo;
	}
}
