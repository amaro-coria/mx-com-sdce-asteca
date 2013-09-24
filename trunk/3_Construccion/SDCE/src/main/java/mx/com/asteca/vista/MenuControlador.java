package mx.com.asteca.vista;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ModulosDTO;
import mx.com.asteca.fachada.ModulosFachada;
import mx.com.asteca.reportes.UtilReporte;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;

@ManagedBean(name = Constantes.BEAN_MENU)
@ApplicationScoped
public class MenuControlador extends BaseController {

	@ManagedProperty("#{modulosFachadaImpl}")
	private transient ModulosFachada fachadaModulos;

	private String navegacion = "";
	private Submenu submenu = new Submenu();

	@PostConstruct
	public void init() {
		try {
			buildMenu(getMenuItems());
			navegacion = "";
		} catch (Exception e) {
			super.addErrorMessage("Ocurrio un error: " + e.getMessage());
		}
		navegacion = "";
	}

	public String getNavegacion() {
		return navegacion;
	}

	public void setNavegacion(String navegacion) {
		this.navegacion = navegacion;
	}

	public Submenu getSubmenu() {
		return submenu;
	}

	public void setSubmenu(Submenu submenu) {
		this.submenu = submenu;
	}

	private void buildMenu(List<ModulosDTO> items) {
		MenuItem item = null;

		for (ModulosDTO it : items) {
			item = new MenuItem();
			final String url = it.getDsc();

			item.setAjax(true);
			item.setProcess(Constantes.MENU_ITEM_PROCESS);
			item.setUpdate(Constantes.MENU_ITEM_UPDATE);
			item.setValue(it.getNombre());
			item.setId(getItemId(it.getNombre()));
			item.addActionListener(new ActionListener() {
				@Override
				public void processAction(ActionEvent arg0)
						throws AbortProcessingException {
					setNavegacion(url);
				}
			});
			submenu.getChildren().add(item);
		}
		submenu.getChildren().add(addReport());
		submenu.getChildren().add(addLogoutItem());
	}

	private List<ModulosDTO> getMenuItems() throws Exception {
		return fachadaModulos.getAll();
	}

	private String getItemId(String modulo) {
		modulo = modulo.toLowerCase();
		modulo = modulo.replace(" ", "_");

		return Constantes.MENU_ITEM_PREFIX + modulo;
	}

	private MenuItem addLogoutItem() {
		MenuItem logout = new MenuItem();

		logout.setAjax(true);
		logout.setProcess(Constantes.MENU_ITEM_PROCESS);
		logout.setUpdate(Constantes.MENU_ITEM_UPDATE);
		logout.setValue("Cerrar Sesión");
		logout.setUrl("/j_spring_security_logout");

		return logout;
	}

	private MenuItem addReport() {
		MenuItem report = new MenuItem();
		report.setAjax(true);
		report.setProcess(Constantes.MENU_ITEM_PROCESS);
		report.setUpdate(Constantes.MENU_ITEM_UPDATE);
		report.setValue("Reportes");
		report.addActionListener(new ActionListener() {
			@Override
			public void processAction(ActionEvent arg0)
					throws AbortProcessingException {
				viewReportPDF("Cedula_5");
			}
		});
		return report;
	}
	
	
	public void viewReportPDF(String name) {
		InputStream ins = UtilReporte.class.getResourceAsStream(name+".jrxml");
		try {
			JasperReport report = JasperCompileManager.compileReport(ins);
			HashMap<String, InputStream> param = new HashMap<String, InputStream>();
			param.put("Sct", UtilReporte.class.getResourceAsStream("img_sct.jpg"));
			param.put("Aero", UtilReporte.class.getResourceAsStream("img_aero.jpg"));
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(null);
			JasperPrint print = JasperFillManager.fillReport(report, param, ds);
			byte[] file = JasperExportManager.exportReportToPdf(print);
			downloadFile("pdf", file);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public void downloadFile(String ext, byte[] file) {

		String physicallName = "Reporte Evaluación Empleado." + ext;
		String mimeType = "application/vnd.ms-excel";

		FacesContext faces = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) faces.getExternalContext().getResponse();

		if (mimeType != null) {
			response.setContentLength(file.length);
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setHeader("Content-disposition", "attachment; filename=\""
					+ physicallName + "\"");
			try {
				ServletOutputStream out;
				out = response.getOutputStream();
				out.write(file);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			faces.responseComplete();
		}
	}

	public ModulosFachada getFachadaModulos() {
		return fachadaModulos;
	}

	public void setFachadaModulos(ModulosFachada fachadaModulos) {
		this.fachadaModulos = fachadaModulos;
	}

}
