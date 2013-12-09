/**
 * 
 */
package mx.com.asteca.vista;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.CursoDTO;
import mx.com.asteca.comun.dto.InstructorDTO;
import mx.com.asteca.fachada.CatGralFachada;
import mx.com.asteca.fachada.CursoFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.InstructorFachada;
import net.sf.jasperreports.engine.JRException;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = Constantes.BEAN_REPORTE_CURSOS)
@ViewScoped
public class ReporteCursosControlador extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private static final String modulo = Constantes.MODULO_REPORTE_CURSOS;
	private String url;
	private FacesContext context;

	@ManagedProperty("#{cursoFachadaImpl}")
	private transient CursoFachada cursoFachada;
	private List<CursoDTO> listaCursos;

	@ManagedProperty("#{catGralFachadaImpl}")
	private transient CatGralFachada fachadaCatGral;
	
	@ManagedProperty("#{instructorFachadaImpl}")
	private transient InstructorFachada fachadaInstructor;
	
	private Integer instructorSelected;
	
	private List<SelectItem> listaSelectInstructor;

	private List<SelectItem> listaSelectArea;
	private Integer areaSelected;

	private List<SelectItem> listaSelectSede;
	private Integer sedeSelected;

	private Date fechaIni;
	private Date fechaFin;

	private static Logger LOGGER = LoggerFactory
			.getLogger(ReporteCursosControlador.class);

	@PostConstruct
	public void init() {
		context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String requestURL = request.getRequestURL().toString();
		setUrl(requestURL.substring(0, requestURL.lastIndexOf("faces")));

		initListaCatGralArea();
		initListaCatGralSede();
	}

	private void initListaSelectInstructor(){
		if(CollectionUtils.isEmpty(listaSelectInstructor)){
			listaSelectInstructor = new ArrayList<SelectItem>();
			List<InstructorDTO> lista = null;
			try {
				lista = fachadaInstructor.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
			if(!CollectionUtils.isEmpty(lista)){
				for(InstructorDTO ins : lista){
					SelectItem item = new SelectItem(ins.getIdInstructor(), ins.getNombre());
					listaSelectInstructor.add(item);
				}
			}
		}
	}
	
	/**
	 * Inicializa la lista de catalogoGeneral con la lista de area
	 */
	private void initListaCatGralArea() {

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

	/**
	 * Inicializa la lista de catalogoGeneral con la lista de sede
	 */
	private void initListaCatGralSede() {

		if (CollectionUtils.isEmpty(listaSelectSede)) {
			try {
				List<CatGralDTO> listaCatGral = fachadaCatGral
						.findByTiposSede();
				if (listaCatGral != null) {
					listaSelectSede = new ArrayList<SelectItem>();
					for (CatGralDTO areas : listaCatGral) {
						listaSelectSede.add(new SelectItem(
								areas.getIdCatGral(), areas.getDsc()));
					}
				} else {
					listaSelectSede = new ArrayList<SelectItem>();
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
	private void initListaCursos() {
		if (CollectionUtils.isEmpty(listaCursos)) {
			try {
				LOGGER.debug("BUSCANDO... ");
				if (cursoFachada != null) {
					listaCursos = cursoFachada.getAll();
				} else {
					listaCursos = new ArrayList<CursoDTO>();
				}
			} catch (FachadaException e) {
				listaCursos = new ArrayList<CursoDTO>();
				LOGGER.error(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	public void mostrarReporte() throws JRException, IOException,
			ClassNotFoundException {
		initListaCursos();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("cursosReporte", listaCursos);
		RequestContext.getCurrentInstance().execute("window.open('" + url + "ReporteCursos?name=Reporte de Cursos"
						+ "')");
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the cursoFachada
	 */
	public CursoFachada getCursoFachada() {
		return cursoFachada;
	}

	/**
	 * @param cursoFachada
	 *            the cursoFachada to set
	 */
	public void setCursoFachada(CursoFachada cursoFachada) {
		this.cursoFachada = cursoFachada;
	}

	/**
	 * @return the listaCursos
	 */
	public List<CursoDTO> getListaCursos() {
		return listaCursos;
	}

	/**
	 * @param listaCursos
	 *            the listaCursos to set
	 */
	public void setListaCursos(List<CursoDTO> listaCursos) {
		this.listaCursos = listaCursos;
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
	 * @return the listaSelectSede
	 */
	public List<SelectItem> getListaSelectSede() {
		return listaSelectSede;
	}

	/**
	 * @param listaSelectSede
	 *            the listaSelectSede to set
	 */
	public void setListaSelectSede(List<SelectItem> listaSelectSede) {
		this.listaSelectSede = listaSelectSede;
	}

	/**
	 * @return the sedeSelected
	 */
	public Integer getSedeSelected() {
		return sedeSelected;
	}

	/**
	 * @param sedeSelected
	 *            the sedeSelected to set
	 */
	public void setSedeSelected(Integer sedeSelected) {
		this.sedeSelected = sedeSelected;
	}

	/**
	 * @return the fechaIni
	 */
	public Date getFechaIni() {
		return fechaIni;
	}

	/**
	 * @param fechaIni
	 *            the fechaIni to set
	 */
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin
	 *            the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	String getModulo() {
		return modulo;
	}

	/**
	 * @return the listaSelectInstructor
	 */
	public List<SelectItem> getListaSelectInstructor() {
		initListaSelectInstructor();
		return listaSelectInstructor;
	}

	/**
	 * @param listaSelectInstructor the listaSelectInstructor to set
	 */
	public void setListaSelectInstructor(List<SelectItem> listaSelectInstructor) {
		this.listaSelectInstructor = listaSelectInstructor;
	}

	/**
	 * @return the fachadaInstructor
	 */
	public InstructorFachada getFachadaInstructor() {
		return fachadaInstructor;
	}

	/**
	 * @param fachadaInstructor the fachadaInstructor to set
	 */
	public void setFachadaInstructor(InstructorFachada fachadaInstructor) {
		this.fachadaInstructor = fachadaInstructor;
	}

	/**
	 * @return the instructorSelected
	 */
	public Integer getInstructorSelected() {
		return instructorSelected;
	}

	/**
	 * @param instructorSelected the instructorSelected to set
	 */
	public void setInstructorSelected(Integer instructorSelected) {
		this.instructorSelected = instructorSelected;
	}
}