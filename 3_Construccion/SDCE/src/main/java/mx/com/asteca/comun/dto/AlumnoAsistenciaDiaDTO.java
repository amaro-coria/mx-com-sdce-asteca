/**
 * 
 */
package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author JAMARO
 *
 */
public class AlumnoAsistenciaDiaDTO implements Serializable{

	private Date fechaAsistencia;
	private int idAlumno;
	private String alumno;
	private String matricula;
	private boolean asistencia;
	
	/**
	 * @return the fechaAsistencia
	 */
	public Date getFechaAsistencia() {
		return fechaAsistencia;
	}
	/**
	 * @param fechaAsistencia the fechaAsistencia to set
	 */
	public void setFechaAsistencia(Date fechaAsistencia) {
		this.fechaAsistencia = fechaAsistencia;
	}
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
	 * @return the alumno
	 */
	public String getAlumno() {
		return alumno;
	}
	/**
	 * @param alumno the alumno to set
	 */
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	/**
	 * @return the asistencia
	 */
	public boolean isAsistencia() {
		return asistencia;
	}
	/**
	 * @param asistencia the asistencia to set
	 */
	public void setAsistencia(boolean asistencia) {
		this.asistencia = asistencia;
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
}
