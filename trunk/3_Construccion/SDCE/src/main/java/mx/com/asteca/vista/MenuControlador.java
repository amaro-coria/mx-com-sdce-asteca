package mx.com.asteca.vista;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;

@ManagedBean(name = "menuBean")
@ApplicationScoped
public class MenuControlador extends BaseController {
	private String navegacion = "/secured/catalogos/equipo.xhtml";
	private Submenu submenu = new Submenu();

	@PostConstruct
	public void init() {
		MenuItem item = null;
		
		for(int i = 0;i < 10 ;i++){
		item = new MenuItem();

		item.setAjax(true);
		item.setProcess("@this");
		item.setValue("Intituto");
		item.setId("mn_item_instituto"+"_"+i);
		item.setUpdate("@all");
		item.addActionListener(new ActionListener() {
			
			@Override
			public void processAction(ActionEvent arg0) throws AbortProcessingException {
				setNavegacion( "/secured/catalogos/notificacion.xhtml");
			}
		});
		
		submenu.getChildren().add(item);
		}
	}

	public void viewPantalla2() {
		System.out.println("PRUEBA");
		this.navegacion = "/secured/catalogos/pantalla2.xhtml";
	}

	public void viewNotificacion() {
		this.navegacion = "/secured/catalogos/notificacion.xhtml";
	}

	public void viewEquipo() {
		this.navegacion = "/secured/catalogos/equipos.xhtml";
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
	};

}
