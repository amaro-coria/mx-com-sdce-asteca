/**
 * 
 */
package mx.com.asteca.vista;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.com.asteca.comun.Constantes;

@ManagedBean(name = Constantes.BEAN_REPORTE_MENSUAL)
@ViewScoped
public class ReporteMensualControlador extends BaseController implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String modulo = Constantes.MODULO_REPORTE_MENSUAL;
	@Override
	String getModulo() {
		return modulo;
	}
	
	

}
