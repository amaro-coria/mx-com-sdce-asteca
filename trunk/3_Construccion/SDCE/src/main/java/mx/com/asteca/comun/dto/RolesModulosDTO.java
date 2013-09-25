package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class RolesModulosDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idRolMod;
	private int idPermisos;
	private Integer idRol;
	private Integer idModulo;
	private Short activo;
	
	
	public int getIdRolMod() {
		return idRolMod;
	}
	public void setIdRolMod(int idRolMod) {
		this.idRolMod = idRolMod;
	}
	public int getIdPermisos() {
		return idPermisos;
	}
	public void setIdPermisos(int idPermisos) {
		this.idPermisos = idPermisos;
	}
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public Integer getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}
	public Short getActivo() {
		return activo;
	}
	public void setActivo(Short activo) {
		this.activo = activo;
	}
	
}
