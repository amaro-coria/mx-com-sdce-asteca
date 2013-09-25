package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class RolesModPermisosUsrDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idRolModPerUsr;
    private int idRolesModUsuarios;
    private int idRolesModulos;
    
	public int getIdRolModPerUsr() {
		return idRolModPerUsr;
	}
	public void setIdRolModPerUsr(int idRolModPerUsr) {
		this.idRolModPerUsr = idRolModPerUsr;
	}
	public int getIdRolesModUsuarios() {
		return idRolesModUsuarios;
	}
	public void setIdRolesModUsuarios(int idRolesModUsuarios) {
		this.idRolesModUsuarios = idRolesModUsuarios;
	}
	public int getIdRolesModulos() {
		return idRolesModulos;
	}
	public void setIdRolesModulos(int idRolesModulos) {
		this.idRolesModulos = idRolesModulos;
	}
    
    
}
