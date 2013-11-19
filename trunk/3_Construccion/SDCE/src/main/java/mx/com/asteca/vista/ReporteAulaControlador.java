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
import mx.com.asteca.comun.dto.AulaDTO;
import mx.com.asteca.fachada.AulaFachada;
import mx.com.asteca.fachada.FachadaException;
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

	@ManagedProperty("#{aulaFachadaImpl}")
	private AulaFachada aulaFachada;
	private List<AulaDTO> listaAula;

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
	private void initListaaula() {
		if (CollectionUtils.isEmpty(listaAula)) {
			try {
				LOGGER.debug("BUSCANDO... ");
				if (aulaFachada != null) {
					listaAula = aulaFachada.findBySede("");
				} else {
					listaAula = new ArrayList<AulaDTO>();
				}
			} catch (FachadaException e) {
				listaAula = new ArrayList<AulaDTO>();
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
		initListaaula();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("aulasReporte", listaAula);
		RequestContext.getCurrentInstance().execute(
				"window.open('" + url + "Reportes?name=Reporte de aula" + "')");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the listaAula
	 */
	public List<AulaDTO> getListaAula() {
		return listaAula;
	}

	/**
	 * @param listaaula
	 *            the listaAula to set
	 */
	public void setListaaula(List<AulaDTO> listaAula) {
		this.listaAula = listaAula;
	}

	/**
	 * @return the aulaFachada
	 */
	public AulaFachada getaulaFachada() {
		return aulaFachada;
	}

	/**
	 * @param aulaFachada
	 *            the aulaFachada to set
	 */
	public void setaulaFachada(AulaFachada aulaFachada) {
		this.aulaFachada = aulaFachada;
	}

	@Override
	String getModulo() {
		return modulo;
	}
}
