/**
 * 
 */
package mx.com.asteca.vista;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.reportes.Reportes;
import mx.com.asteca.reportes.UtilReporte;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@ManagedBean(name = Constantes.BEAN_REPORTE_ALUMNO)
@ViewScoped
public class ReporteAlumnoControlador extends BaseController implements
		Serializable {
	private String url;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
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
		String tipo = "text/html";
		ExternalContext econtext = this.getFacesContext().getExternalContext();

		InputStream inputStream = Reportes.class
				.getResourceAsStream("Cedula_5.jasper");
		if (inputStream == null) {
			throw new ClassNotFoundException(
					"Archivo datos_jugador.jasper no se encontró");
		}
		FacesContext fcontext = FacesContext.getCurrentInstance();
		try {
			JRExporter exporter = null;
			// Context ctx = new InitialContext();
			// DataSource ds = (DataSource) ctx.lookup(dataSourceName);
			// Connection conn = ds.getConnection();
			HashMap<String, InputStream> param = new HashMap<String, InputStream>();
			param.put("Sct",
					UtilReporte.class.getResourceAsStream("img_sct.jpg"));
			param.put("Aero",
					UtilReporte.class.getResourceAsStream("img_aero.jpg"));

			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream,
					param);
			HttpServletResponse response = (HttpServletResponse) econtext
					.getResponse();
			HttpServletRequest request = (HttpServletRequest) econtext
					.getRequest();
			response.setContentType(tipo);
			if ("application/pdf".equals(tipo)) {
				exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
						response.getOutputStream());
			} else if ("text/html".equals(tipo)) {
				exporter = new JRHtmlExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,
						response.getWriter());
				exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
						request.getContextPath() + "/image?image=");

			}
			if (exporter != null) {
				exporter.exportReport();
			}
		} catch (Exception ex) {
			Logger.getLogger(Reportes.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
			throw new FacesException(ex);
		}
		fcontext.responseComplete();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
