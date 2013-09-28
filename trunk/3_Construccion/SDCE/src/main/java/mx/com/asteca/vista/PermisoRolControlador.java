package mx.com.asteca.vista;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.fachada.ModulosFachada;
import mx.com.asteca.fachada.PermisosFachada;
import mx.com.asteca.fachada.RolesFachada;
import mx.com.asteca.fachada.RolesModPermisosSobreFachada;
import mx.com.asteca.fachada.RolesModPermisosUsrFachada;
import mx.com.asteca.fachada.RolesModUsuariosFachada;
import mx.com.asteca.fachada.RolesModulosFachada;
import mx.com.asteca.persistencia.entidades.RolesModPermisosUsr;

@ManagedBean(name = Constantes.BEAN_PERMISO_ROL)
@ViewScoped
public class PermisoRolControlador extends BaseController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{rolesFachadaImpl}")
	private RolesFachada rolesFachada;
	@ManagedProperty("#{modulosFachadaImpl}")
	private ModulosFachada modulosFachada;
	@ManagedProperty("#{rolesModulosFachadaImpl}")
	private RolesModulosFachada rolesModulosFachada;
	@ManagedProperty("#{permisosFachadaImpl}")
	private PermisosFachada permisosFachada;
	@ManagedProperty("#{rolesModPermisosUsrFachadaImpl}")
	private RolesModPermisosUsrFachada rolesModPermisosUsrFachada;
	@ManagedProperty("#{rolesModUsuariosFachadaImpl}")
	private RolesModUsuariosFachada rolesModUsuariosFachada;
	@ManagedProperty("#{rolesModPermisosSobreFachadaImpl}")
	private RolesModPermisosSobreFachada rolesModPermisosSobreFachada;

}
