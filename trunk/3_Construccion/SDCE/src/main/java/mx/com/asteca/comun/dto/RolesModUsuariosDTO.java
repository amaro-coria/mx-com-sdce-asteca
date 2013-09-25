package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.Date;


public class RolesModUsuariosDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idRolModUsr;
    private int idRol;
    private int idPersona;
    private Date periodoInicio;
    private Date periodoFin;
    private Short activo;
    
    
	public int getIdRolModUsr() {
		return idRolModUsr;
	}
	public void setIdRolModUsr(int idRolModUsr) {
		this.idRolModUsr = idRolModUsr;
	}
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public Date getPeriodoInicio() {
		return periodoInicio;
	}
	public void setPeriodoInicio(Date periodoInicio) {
		this.periodoInicio = periodoInicio;
	}
	public Date getPeriodoFin() {
		return periodoFin;
	}
	public void setPeriodoFin(Date periodoFin) {
		this.periodoFin = periodoFin;
	}
	public Short getActivo() {
		return activo;
	}
	public void setActivo(Short activo) {
		this.activo = activo;
	}

}
