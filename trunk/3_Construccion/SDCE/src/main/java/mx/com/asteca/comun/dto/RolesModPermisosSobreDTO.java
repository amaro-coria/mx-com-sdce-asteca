package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class RolesModPermisosSobreDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idRolModPermSobre;
	private int idPermisos;
	private int idRolesModPermisosUsr;
	
	public int getIdRolModPermSobre() {
		return idRolModPermSobre;
	}
	public void setIdRolModPermSobre(int idRolModPermSobre) {
		this.idRolModPermSobre = idRolModPermSobre;
	}
	public int getIdPermisos() {
		return idPermisos;
	}
	public void setIdPermisos(int idPermisos) {
		this.idPermisos = idPermisos;
	}
	public int getIdRolesModPermisosUsr() {
		return idRolesModPermisosUsr;
	}
	public void setIdRolesModPermisosUsr(int idRolesModPermisosUsr) {
		this.idRolesModPermisosUsr = idRolesModPermisosUsr;
	}
	
}
