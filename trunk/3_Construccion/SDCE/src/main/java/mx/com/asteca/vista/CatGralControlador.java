/**
 * 
 */
package mx.com.asteca.vista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import mx.com.asteca.fachada.CatGralFachada;

/**
 * @author Javier
 *
 */
@ManagedBean
@ViewScoped
public class CatGralControlador extends BaseController {
	
	@Autowired
	private CatGralFachada catGralFachada;

}
