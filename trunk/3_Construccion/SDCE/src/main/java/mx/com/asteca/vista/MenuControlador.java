package mx.com.asteca.vista;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "menuBean")
@ApplicationScoped
public class MenuControlador extends BaseController {
	private String navegacion = "/equipo.xhtml";

	public void viewPantalla2() {
		System.out.println("PRUEBA");
		this.navegacion = "/pantalla2.xhtml";
	}

	public void viewNotificacion() {
		this.navegacion = "/notificacion.xhtml";
	}

	public void viewEquipo() {
		this.navegacion = "/equipo.xhtml";
	}

	public String getNavegacion() {
		return navegacion;
	}

	public void setNavegacion(String navegacion) {
		this.navegacion = navegacion;
	}
}
