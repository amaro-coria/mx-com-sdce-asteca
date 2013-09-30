/**
 * 
 */
package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.MateriaDTO;
import mx.com.asteca.fachada.MateriaFachada;

/**
 * @author JAMARO
 *
 */
@ManagedBean(name = Constantes.BEAN_CURSO)
@ViewScoped
public class CursoControlador extends BaseController implements Serializable{

	private static final String modulo = Constantes.MODULO_CURSO;
	@ManagedProperty("#{materiaFachadaImpl}")
	private transient MateriaFachada fachadaMateria;
	
	private List<MateriaDTO> listMaterias;

	@Override
	String getModulo() {
		return modulo;
	}
	
}
