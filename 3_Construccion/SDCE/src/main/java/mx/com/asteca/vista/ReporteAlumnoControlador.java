/**
 * 
 */
package mx.com.asteca.vista;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.fachada.AlumnoFachada;
import mx.com.asteca.persistencia.entidades.Alumnos;
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

@ManagedBean(name = Constantes.BEAN_REPORTE_ALUMNO)
@ViewScoped
public class ReporteAlumnoControlador extends BaseController implements
		Serializable {
	@ManagedProperty("#{alumnoFachadaImpl}")
	private transient AlumnoFachada fachada;
	private List<AlumnoDTO> listaItems;
	private String url;
	private FacesContext context;

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
	 */
	private static final long serialVersionUID = 1L;

	public void mostrarReporte() throws JRException, IOException,
			ClassNotFoundException {
		
	}
	public void enviarPdf() throws JRException {
		
		final List<String> empleados = 
		        Arrays.asList("Jose Manuel Sánchez", "Alfonso Blanco", "Angel García", "Rubén Aguilera");
		Alumnos alum = new Alumnos();
		
		
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

	public List<AlumnoDTO> getListaItems() {
		return listaItems;
	}

	public void setListaItems(List<AlumnoDTO> listaItems) {
		this.listaItems = listaItems;
	}

}
