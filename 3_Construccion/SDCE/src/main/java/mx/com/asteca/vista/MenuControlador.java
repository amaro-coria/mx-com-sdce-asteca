package mx.com.asteca.vista;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.http.HttpServletRequest;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ModulosDTO;
import mx.com.asteca.fachada.ModulosFachada;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.springframework.util.CollectionUtils;

@ManagedBean(name = Constantes.BEAN_MENU)
@ApplicationScoped
public class MenuControlador extends BaseController implements Serializable {

	private static final String modulo = "menu";
	@ManagedProperty("#{modulosFachadaImpl}")
	private transient ModulosFachada fachadaModulos;

	private List<ModulosDTO> listaModulos;

	private String navegacion = "";
	private Submenu submenu = new Submenu();

	public void initMenu() {
		try {
			buildMenu(getMenuItems());
		} catch (Exception e) {
			e.printStackTrace();
			super.addErrorMessage("Ocurrio un error: " + e.getMessage());
		}
	}

	public String getNavegacion() {
		return navegacion;
	}

	public void setNavegacion(String navegacion) {
		this.navegacion = navegacion;
	}

	public Submenu getSubmenu() {
		initMenu();
		return submenu;
	}

	public void setSubmenu(Submenu submenu) {
		this.submenu = submenu;
	}

	private void buildMenu(List<ModulosDTO> items) {
		MenuItem item = null;
		if (!CollectionUtils.isEmpty(submenu.getChildren())) {
			submenu.getChildren().clear();
		}
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
		listaModulos = new ArrayList<ModulosDTO>();
		HashMap<Integer, ModulosDTO> permisos = getMenuItemsSession();

		Iterator<Integer> it = permisos.keySet().iterator();
		Integer index = null;
		while (it.hasNext()) {
			index = it.next();
			listaModulos.add(permisos.get(index));
		}

		return listaModulos;
	}

	private HashMap<Integer, ModulosDTO> getMenuItemsSession() {
		HttpServletRequest request = (HttpServletRequest) getFacesContext().getCurrentInstance().getExternalContext().getRequest();
		HashMap<Integer, ModulosDTO> permisos = (HashMap<Integer, ModulosDTO>) request
				.getSession()
				.getAttribute("permisos");

		return permisos;
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
				try {
					FacesContext context = FacesContext.getCurrentInstance();
					HttpServletRequest request = (HttpServletRequest) context
							.getExternalContext().getRequest();
					String requestURL = request.getRequestURL().toString();
					String url = requestURL.substring(0,
							requestURL.lastIndexOf("faces"));
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect(url + "Reportes?name=Cedula_5");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		return report;
	}

	public ModulosFachada getFachadaModulos() {
		return fachadaModulos;
	}

	public void setFachadaModulos(ModulosFachada fachadaModulos) {
		this.fachadaModulos = fachadaModulos;
	}

	@Override
	String getModulo() {
		return modulo;
	}

}
