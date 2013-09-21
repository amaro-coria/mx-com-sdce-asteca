package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class AlumnoTempDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idAlumno;
	private String matricula;
	private String nombre;
	private String apellidoP;
	private String apellidoM;
	private String descEstatus;
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
	 * @return the apellidoP
	 */
	public String getApellidoP() {
		return apellidoP;
	}
	/**
	 * @param apellidoP the apellidoP to set
	 */
	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}
	/**
	 * @return the apellidoM
	 */
	public String getApellidoM() {
		return apellidoM;
	}
	/**
	 * @param apellidoM the apellidoM to set
	 */
	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}
	/**
	 * @return the descEstatus
	 */
	public String getDescEstatus() {
		return descEstatus;
	}
	/**
	 * @param descEstatus the descEstatus to set
	 */
	public void setDescEstatus(String descEstatus) {
		this.descEstatus = descEstatus;
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
	
}
