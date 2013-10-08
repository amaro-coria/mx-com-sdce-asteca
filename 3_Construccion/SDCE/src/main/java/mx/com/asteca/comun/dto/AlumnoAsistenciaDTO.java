/**
 * 
 */
package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author JAMARO
 *
 */
public class AlumnoAsistenciaDTO implements Serializable{

	private String matricula;
	private String nombre;
	private List<String> dias;
	private List<String> asistencias;
	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}
	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the dias
	 */
	public List<String> getDias() {
		return dias;
	}
	/**
	 * @param dias the dias to set
	 */
	public void setDias(List<String> dias) {
		this.dias = dias;
	}
	/**
	 * @return the asistencias
	 */
	public List<String> getAsistencias() {
		return asistencias;
	}
	/**
	 * @param asistencias the asistencias to set
	 */
	public void setAsistencias(List<String> asistencias) {
		this.asistencias = asistencias;
	}
}
