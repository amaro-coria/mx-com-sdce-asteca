package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.List;

public class AlumnoCalificacionMateriaDTO implements Serializable{

	private int idAlumno;
	private String matricula;
	private String nombre;
	private List<String> listaMaterias;
	private List<String> listCalificaciones;
	/**
	 * @return the idAlumno
	 */
	public int getIdAlumno() {
		return idAlumno;
	}
	/**
	 * @param idAlumno the idAlumno to set
	 */
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
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
	 * @return the listaMaterias
	 */
	public List<String> getListaMaterias() {
		return listaMaterias;
	}
	/**
	 * @param listaMaterias the listaMaterias to set
	 */
	public void setListaMaterias(List<String> listaMaterias) {
		this.listaMaterias = listaMaterias;
	}
	/**
	 * @return the listCalificaciones
	 */
	public List<String> getListCalificaciones() {
		return listCalificaciones;
	}
	/**
	 * @param listCalificaciones the listCalificaciones to set
	 */
	public void setListCalificaciones(List<String> listCalificaciones) {
		this.listCalificaciones = listCalificaciones;
	}
}
