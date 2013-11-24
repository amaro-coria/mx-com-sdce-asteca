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
import mx.com.asteca.comun.dto.ReporteAulasDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.ReporteAulasFachada;
import net.sf.jasperreports.engine.JRException;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = Constantes.BEAN_REPORTE_AULA)
@ViewScoped
public class ReporteAulaControlador extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private static final String modulo = Constantes.MODULO_REPORTE_AULA;
	private String url;
	private FacesContext context = FacesContext.getCurrentInstance();

	@ManagedProperty("#{reporteAulasFachadaImpl}")
	private ReporteAulasFachada reportAulaFachada;
	private List<ReporteAulasDTO> listaReportAula;

	private static Logger LOGGER = LoggerFactory
			.getLogger(ReporteAulaControlador.class);

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
	private void initListaAula() {
		if (CollectionUtils.isEmpty(listaReportAula)) {
			try {
				LOGGER.debug("BUSCANDO... ");
				if (reportAulaFachada != null) {
					listaReportAula = reportAulaFachada.getAll();
				} else {
					listaReportAula = new ArrayList<ReporteAulasDTO>();
				}
			} catch (FachadaException e) {
				listaReportAula = new ArrayList<ReporteAulasDTO>();
				LOGGER.error(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	/**
	 * Inicializa la lista de catalogoGeneral con la lista de tipos de equipos
	 */
	private void initListaCatGral() {

	}

	public void mostrarReporte() throws JRException, IOException,
			ClassNotFoundException {
		initListaAula();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("aulasReporte", listaReportAula);
		RequestContext.getCurrentInstance().execute(
				"window.open('" + url + "ReporteAulas?name=Reporte de aula" + "')");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public ReporteAulasFachada getReportAulaFachada() {
		return reportAulaFachada;
	}

	public void setReportAulaFachada(ReporteAulasFachada reportAulaFachada) {
		this.reportAulaFachada = reportAulaFachada;
	}

	public List<ReporteAulasDTO> getListaReportAula() {
		return listaReportAula;
	}

	public void setListaReportAula(List<ReporteAulasDTO> listaReportAula) {
		this.listaReportAula = listaReportAula;
	}

	@Override
	String getModulo() {
		return modulo;
	}
}
