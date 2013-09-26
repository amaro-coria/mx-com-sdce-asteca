/**
 * 
 */
package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.Date;

import mx.com.asteca.util.FechaUtil;

/**
 * @author JAMARO
 *
 */
public class MateriaDTO implements Serializable{
	
	private int idMateria;
	private int idMateriaRegistro;
	private String nombre;
	private int idAula;
	private String nombreAula;
	private int idInstructor;
	private String nombreInstructor;
	private Date fechaFinal;
	private String fechaFinalString;
	private Date fechaInicial;
	private String fechaInicialString;
	private int idTipo;
	private String tipoNombre;
	/**
	 * @return the idMateria
	 */
	public int getIdMateria() {
		return idMateria;
	}
	/**
	 * @param idMateria the idMateria to set
	 */
	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
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
	 * @return the idAula
	 */
	public int getIdAula() {
		return idAula;
	}
	/**
	 * @param idAula the idAula to set
	 */
	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}
	/**
	 * @return the nombreAula
	 */
	public String getNombreAula() {
		return nombreAula;
	}
	/**
	 * @param nombreAula the nombreAula to set
	 */
	public void setNombreAula(String nombreAula) {
		this.nombreAula = nombreAula;
	}
	/**
	 * @return the idInstructor
	 */
	public int getIdInstructor() {
		return idInstructor;
	}
	/**
	 * @param idInstructor the idInstructor to set
	 */
	public void setIdInstructor(int idInstructor) {
		this.idInstructor = idInstructor;
	}
	/**
	 * @return the nombreInstructor
	 */
	public String getNombreInstructor() {
		return nombreInstructor;
	}
	/**
	 * @param nombreInstructor the nombreInstructor to set
	 */
	public void setNombreInstructor(String nombreInstructor) {
		this.nombreInstructor = nombreInstructor;
	}
	/**
	 * @return the fechaFinal
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}
	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	/**
	 * @return the fechaFinalString
	 */
	public String getFechaFinalString() {
		if(fechaFinal == null){
			return "";
		}else{
			fechaFinalString = FechaUtil.getInstance().parseDateMM_dd_yy(fechaFinal);
		}
		return fechaFinalString;
	}
	/**
	 * @param fechaFinalString the fechaFinalString to set
	 */
	public void setFechaFinalString(String fechaFinalString) {
		this.fechaFinalString = fechaFinalString;
	}
	/**
	 * @return the fechaInicial
	 */
	public Date getFechaInicial() {
		return fechaInicial;
	}
	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	/**
	 * @return the fechaInicialString
	 */
	public String getFechaInicialString() {
		if(fechaInicial == null){
			return "";
		}else{
			fechaInicialString = FechaUtil.getInstance().parseDateMM_dd_yy(fechaInicial);
		}
		return fechaInicialString;
	}
	/**
	 * @param fechaInicialString the fechaInicialString to set
	 */
	public void setFechaInicialString(String fechaInicialString) {
		this.fechaInicialString = fechaInicialString;
	}
	/**
	 * @return the idTipo
	 */
	public int getIdTipo() {
		return idTipo;
	}
	/**
	 * @param idTipo the idTipo to set
	 */
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	/**
	 * @return the tipoNombre
	 */
	public String getTipoNombre() {
		return tipoNombre;
	}
	/**
	 * @param tipoNombre the tipoNombre to set
	 */
	public void setTipoNombre(String tipoNombre) {
		this.tipoNombre = tipoNombre;
	}
	/**
	 * @return the idMateriaRegistro
	 */
	public int getIdMateriaRegistro() {
		return idMateriaRegistro;
	}
	/**
	 * @param idMateriaRegistro the idMateriaRegistro to set
	 */
	public void setIdMateriaRegistro(int idMateriaRegistro) {
		this.idMateriaRegistro = idMateriaRegistro;
	}
	

}
