package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.Date;

import mx.com.asteca.util.FechaUtil;

public class CertificadoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idCert;
	private int idAlumno;
	private String nombreAlumno;
	private int idStatus;
	private String status;
	private Date fechaEmision;
	private String stringFechaEmision;
	private Date fechaCancelacion;
	private String stringFechaCancelacion;
	private String motivoCancelacion;
	private String noCertificado;
	/**
	 * @return the idCert
	 */
	public int getIdCert() {
		return idCert;
	}
	/**
	 * @param idCert the idCert to set
	 */
	public void setIdCert(int idCert) {
		this.idCert = idCert;
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
	 * @return the nombreAlumno
	 */
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	/**
	 * @param nombreAlumno the nombreAlumno to set
	 */
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
	/**
	 * @return the idStatus
	 */
	public int getIdStatus() {
		return idStatus;
	}
	/**
	 * @param idStatus the idStatus to set
	 */
	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return the stringFechaEmision
	 */
	public String getStringFechaEmision() {
		if(fechaEmision == null){
			stringFechaEmision = "";
		}else{
			stringFechaEmision = FechaUtil.getInstance().parseDateMM_dd_yy(fechaEmision);
		}
		return stringFechaEmision;
	}
	/**
	 * @param stringFechaEmision the stringFechaEmision to set
	 */
	public void setStringFechaEmision(String stringFechaEmision) {
		this.stringFechaEmision = stringFechaEmision;
	}
	/**
	 * @return the fechaCancelacion
	 */
	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}
	/**
	 * @param fechaCancelacion the fechaCancelacion to set
	 */
	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}
	/**
	 * @return the stringFechaCancelacion
	 */
	public String getStringFechaCancelacion() {
		if(fechaCancelacion == null){
			stringFechaCancelacion = "";
		}else{
			stringFechaCancelacion = FechaUtil.getInstance().parseDateMM_dd_yy(fechaCancelacion);
		}
		return stringFechaCancelacion;
	}
	/**
	 * @param stringFechaCancelacion the stringFechaCancelacion to set
	 */
	public void setStringFechaCancelacion(String stringFechaCancelacion) {
		this.stringFechaCancelacion = stringFechaCancelacion;
	}
	/**
	 * @return the motivoCancelacion
	 */
	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}
	/**
	 * @param motivoCancelacion the motivoCancelacion to set
	 */
	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}
	/**
	 * @return the noCertificado
	 */
	public String getNoCertificado() {
		return noCertificado;
	}
	/**
	 * @param noCertificado the noCertificado to set
	 */
	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
	}
	
	
	
}
