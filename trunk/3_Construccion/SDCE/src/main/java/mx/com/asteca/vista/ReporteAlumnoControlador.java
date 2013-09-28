/**
 * 
 */
package mx.com.asteca.vista;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.fachada.AlumnoFachada;
import mx.com.asteca.fachada.CatGralFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.reportes.UtilReporte;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = Constantes.BEAN_REPORTE_ALUMNO)
@ViewScoped
public class ReporteAlumnoControlador extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private String url;
	private FacesContext context;
	
	@ManagedProperty("#{alumnoFachadaImpl}")
	private AlumnoFachada alumnosFachada;
	private List<AlumnoDTO> listaAlumnos;
	
	@ManagedProperty("#{catGralFachadaImpl}")
	private CatGralFachada fachadaCatGral;
	private List<SelectItem> listaSelectArea;
	
	private Integer areaSelected;
	
	private static Logger LOGGER = LoggerFactory.getLogger(ReporteAlumnoControlador.class);

	@PostConstruct
	public void init() {
		context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String requestURL = request.getRequestURL().toString();
		setUrl(requestURL.substring(0, requestURL.lastIndexOf("faces")));
		
		initListaCatGral();
	}

	

	/**
	 * 
	 * @throws FachadaException
	 */
	private void initListaAlumnos() {
		if(CollectionUtils.isEmpty(listaAlumnos)){		
			try {
				LOGGER.debug("BUSCANDO... ");
				if(alumnosFachada != null){	
					listaAlumnos = alumnosFachada.findByArea(areaSelected);
				}
				else{
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
		
		if(CollectionUtils.isEmpty(listaSelectArea)){		
			try {
				List<CatGralDTO> listaCatGral = fachadaCatGral.findTiposArea();
				if(listaCatGral != null){
					listaSelectArea = new ArrayList<SelectItem>();
					for (CatGralDTO areas : listaCatGral) {
						listaSelectArea.add(new SelectItem(areas.getIdCatGral(), 
											areas.getDsc()));
					}
				}
				else{
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
		
		for(AlumnoDTO lista : listaAlumnos){
			LOGGER.debug(lista.getNombre());
		}
	}
	public void enviarPdf() throws JRException {
		
		final List<String> empleados = 
		        Arrays.asList("Jose Manuel Sánchez", "Alfonso Blanco", "Angel García", "Rubén Aguilera");
		
		final Map<String,Object> parameters = new HashMap<String,Object>();
	    parameters.put("Alumnos", empleados);
	    
		 InputStream ins = UtilReporte.class.getResourceAsStream("Cedula5.jrxml");
		 JasperDesign jasDesign = JRXmlLoader.load(ins);
         JasperReport jasReport = JasperCompileManager.compileReport(jasDesign);
         JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, parameters,  new JREmptyDataSource());
         JasperExportManager.exportReportToPdfFile(jasPrint, "NombrePDD.pdf");
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
	 * @param listaAlumnos the listaAlumnos to set
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
	 * @param alumnosFachada the alumnosFachada to set
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
	 * @param areaSelected the areaSelected to set
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
	 * @param fachadaCatGral the fachadaCatGral to set
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
	 * @param listaSelectArea the listaSelectArea to set
	 */
	public void setListaSelectArea(List<SelectItem> listaSelectArea) {
		this.listaSelectArea = listaSelectArea;
	}
}
