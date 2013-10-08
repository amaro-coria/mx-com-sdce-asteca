package mx.com.asteca.vista;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.rmi.CORBA.UtilDelegate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.CollectionUtils;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ModulosDTO;
import mx.com.asteca.comun.dto.PermisosDTO;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.comun.dto.RolesDTO;
import mx.com.asteca.comun.dto.RolesModPermisosSobreDTO;
import mx.com.asteca.comun.dto.RolesModPermisosUsrDTO;
import mx.com.asteca.comun.dto.RolesModUsuariosDTO;
import mx.com.asteca.comun.dto.RolesModulosDTO;
import mx.com.asteca.fachada.BaseFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.ModulosFachada;
import mx.com.asteca.fachada.PermisosFachada;
import mx.com.asteca.fachada.PersonaFachada;
import mx.com.asteca.fachada.RolesFachada;
import mx.com.asteca.fachada.RolesModPermisosSobreFachada;
import mx.com.asteca.fachada.RolesModPermisosUsrFachada;
import mx.com.asteca.fachada.RolesModUsuariosFachada;
import mx.com.asteca.fachada.RolesModulosFachada;
import mx.com.asteca.util.FechaUtil;

@ManagedBean(name = Constantes.BEAN_LOGIN)
@RequestScoped
public class LoginControlador extends BaseController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	
	@ManagedProperty("#{authenticationManager}")
	private AuthenticationManager am;
	@ManagedProperty("#{personaFachadaImpl}")
	private transient PersonaFachada fachadaPersona;
	
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
	@ManagedProperty("#{personaFachadaImpl}")
	private PersonaFachada personaFachada;

	private PersonaDTO userSelected;
	private PersonaDTO nuevoUser;

	private List<PersonaDTO> listaPersonas;
	private List<ModulosDTO> listaModulos;
	private List<ModulosDTO> listaModulosEdit;
	private List<ModulosDTO> listaModulosVer;
	private List<PersonaDTO> listaFiltered;
	private List<RolesDTO> listaRoles;
	private List<RolesModulosDTO> listaRolesModulos;
	private List<RolesModPermisosUsrDTO> listaRolesModPermisosUsr;
	private List<RolesModPermisosSobreDTO> listaRolesModPermisosSobre;
	private List<SelectItem> rolItems;
	private SelectItem item;
	private RolesModUsuariosDTO rolesModUsuarios;
	private RolesModPermisosUsrDTO rolesModPermisosUsr;
	private RolesModPermisosSobreDTO rolesModPermisosSobre;
	private PermisosDTO permisos;
	private RolesDTO roles;
	private boolean nuevoRolActivo;
	private boolean selectedRolActivo;
	private String selectedClave;
	private String selectedUsuario;
	private String selectedNombre;
	private String selectedRol;
	private String idRol;
	private String confirmarPassword;
	private List<PermisosDTO> listaPermisos;
	
	private HashMap<Integer, ModulosDTO> menu;
	
	protected AuthenticationDetailsSource authenticationDetailsSource = new WebAuthenticationDetailsSource();
	
	public LoginControlador() {
		rolesModUsuarios = new RolesModUsuariosDTO();
		userSelected = new PersonaDTO();
		nuevoUser = new PersonaDTO();
		rolItems = new ArrayList<SelectItem>();
		listaModulos = new ArrayList<ModulosDTO>();
		listaModulosEdit = new ArrayList<ModulosDTO>();
		listaModulosVer = new ArrayList<ModulosDTO>();
		listaPermisos = new ArrayList<PermisosDTO>();
	}
	
	public String login() {
		try{
			HttpServletRequest servletReq = (HttpServletRequest) super.getFacesContext().getCurrentInstance().getExternalContext().getRequest();
			
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(this.getUserName(), this.getPassword());
			
			HttpSession session = servletReq.getSession(false);
			
			if(session != null) {
				PersonaDTO usuario = fachadaPersona.getUser(this.getUserName());
				obtenerModulosPermisosUsr(usuario.getIdPersona());
				servletReq.getSession().setAttribute("SPRING_SECURITY_CURRENT_USER", usuario);
				menu = new HashMap<Integer, ModulosDTO>();
				
				for (ModulosDTO modulo:listaModulosVer) {
					menu.put(modulo.getIdModulo(), modulo);
				}
				servletReq.getSession().setAttribute("permisos",menu);
				StringBuffer nombreCompleto=new StringBuffer();
				nombreCompleto.append(usuario.getNombre()!=null?usuario.getNombre():"").append(" ")
							  .append(usuario.getApellidoP()!=null?usuario.getApellidoP():"").append(" ")
							  .append(usuario.getApellidoM()!=null?usuario.getApellidoM():"");
				servletReq.getSession().setAttribute("nombreUsuario", nombreCompleto.toString());
				servletReq.getSession().setAttribute("fechaHora", DateFormat.getDateInstance(DateFormat.FULL).format(new Date()));
				servletReq.getSession().setAttribute("fechaHoraStr", FechaUtil.getInstance().parseDateMM_dd_yy(new Date())); 
						//DateFormat.getDateInstance(DateFormat.FULL).format(new Date()));
				
			}
			
			authRequest.setDetails(authenticationDetailsSource.buildDetails(servletReq));
			Authentication authResult = am.authenticate(authRequest);
			
			SecurityContextHolder.getContext().setAuthentication(authResult);
		} catch (BadCredentialsException bce) {
			super.addErrorMessage(Constantes.ERROR_LOGIN);
			return null;
		} catch (AuthenticationException ae) {
			super.addErrorMessage(Constantes.ERROR_AUTENTICACION);
			return null;
		} catch (FachadaException fe) {
			super.addErrorMessage(fe.getMessage());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			super.addErrorMessage(e.getMessage());
			return null;
		}
		return "/faces/secured/workarea?faces-redirect=true";
	}
	
	public void obtenerModulosPermisosUsr(int idUser) {
		try {
			rolesModUsuarios = rolesModUsuariosFachada
					.buscarPorUsuario(idUser);
			listaRolesModulos = rolesModulosFachada
					.buscarPorRol(rolesModUsuarios.getIdRol());
			initListaModulosVer();

			roles = rolesFachada.findByPK(rolesModUsuarios.getIdRol());
			selectedRolActivo = rolesModUsuarios.equals((short) 1) ? true:false;
			listaRolesModPermisosUsr = rolesModPermisosUsrFachada
					.buscarPorRolModUsuario(rolesModUsuarios.getIdRolModUsr());
			for (RolesModPermisosUsrDTO rolesModPermisosUsr : listaRolesModPermisosUsr) {
				listaRolesModPermisosSobre = rolesModPermisosSobreFachada
						.buscarPorRolModPermisosUsr(rolesModPermisosUsr
								.getIdRolModPerUsr());
				for (RolesModPermisosSobreDTO rolesModPermisosSobre : listaRolesModPermisosSobre) {
					listaPermisos.add(permisosFachada
							.findByPK(rolesModPermisosSobre.getIdPermisos()));
					for (RolesModulosDTO rolesModulos : listaRolesModulos) {
						for (ModulosDTO modulo : listaModulosVer) {
							if (rolesModulos.getIdModulo().equals(
									modulo.getIdModulo())) {
								for (PermisosDTO permiso : listaPermisos) {
									if (permiso.getIdPermiso() == rolesModulos
											.getIdPermisos()) {
										modulo.setAlta(permiso.getAlta()
												.equals((short) 0) ? false
												: true);
										modulo.setBorrar(permiso.getBorrar()
												.equals((short) 0) ? false
												: true);
										modulo.setConsulta(permiso
												.getConsulta()
												.equals((short) 0) ? false
												: true);
										modulo.setEditar(permiso.getCambios()
												.equals((short) 0) ? false
												: true);
										modulo.setImprimir(permiso
												.getImpresion().equals(
														(short) 0) ? false
												: true);

									}
								}
							}
						}
					}
				}
			}

		} catch (FachadaException e) {
			super.addErrorMessage(Constantes.ERROR_NUEVO_REGISTRO);
		}

	}
	
	public void initListaModulosVer() {
		if (CollectionUtils.isEmpty(listaModulosEdit)) {
			try {
				for (RolesModulosDTO rolesModulos : listaRolesModulos) {
					listaModulosVer.add(modulosFachada.findByPK(rolesModulos
							.getIdModulo()));
					listaPermisos.add(permisosFachada.findByPK(rolesModulos
							.getIdPermisos()));
					for (ModulosDTO modulo : listaModulosVer) {
						if (rolesModulos.getIdModulo().equals(
								modulo.getIdModulo())) {
							for (PermisosDTO permiso : listaPermisos) {
								if (permiso.getIdPermiso() == rolesModulos
										.getIdPermisos()) {
									modulo.setAlta(permiso.getAlta().equals(
											(short) 0) ? false : true);
									modulo.setBorrar(permiso.getBorrar()
											.equals((short) 0) ? false : true);
									modulo.setConsulta(permiso.getConsulta()
											.equals((short) 0) ? false : true);
									modulo.setEditar(permiso.getCambios()
											.equals((short) 0) ? false : true);
									modulo.setImprimir(permiso.getImpresion()
											.equals((short) 0) ? false : true);

								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}


	public PersonaFachada getFachadaPersona() {
		return fachadaPersona;
	}

	public void setFachadaPersona(PersonaFachada fachadaPersona) {
		this.fachadaPersona = fachadaPersona;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationManager getAm() {
		return am;
	}

	public void setAm(AuthenticationManager am) {
		this.am = am;
	}

	public AuthenticationDetailsSource getAuthenticationDetailsSource() {
		return authenticationDetailsSource;
	}

	public void setAuthenticationDetailsSource(
			AuthenticationDetailsSource authenticationDetailsSource) {
		this.authenticationDetailsSource = authenticationDetailsSource;
	}

	public RolesFachada getRolesFachada() {
		return rolesFachada;
	}

	public void setRolesFachada(RolesFachada rolesFachada) {
		this.rolesFachada = rolesFachada;
	}

	public ModulosFachada getModulosFachada() {
		return modulosFachada;
	}

	public void setModulosFachada(ModulosFachada modulosFachada) {
		this.modulosFachada = modulosFachada;
	}

	public RolesModulosFachada getRolesModulosFachada() {
		return rolesModulosFachada;
	}

	public void setRolesModulosFachada(RolesModulosFachada rolesModulosFachada) {
		this.rolesModulosFachada = rolesModulosFachada;
	}

	public PermisosFachada getPermisosFachada() {
		return permisosFachada;
	}

	public void setPermisosFachada(PermisosFachada permisosFachada) {
		this.permisosFachada = permisosFachada;
	}

	public RolesModPermisosUsrFachada getRolesModPermisosUsrFachada() {
		return rolesModPermisosUsrFachada;
	}

	public void setRolesModPermisosUsrFachada(
			RolesModPermisosUsrFachada rolesModPermisosUsrFachada) {
		this.rolesModPermisosUsrFachada = rolesModPermisosUsrFachada;
	}

	public RolesModUsuariosFachada getRolesModUsuariosFachada() {
		return rolesModUsuariosFachada;
	}

	public void setRolesModUsuariosFachada(
			RolesModUsuariosFachada rolesModUsuariosFachada) {
		this.rolesModUsuariosFachada = rolesModUsuariosFachada;
	}

	public RolesModPermisosSobreFachada getRolesModPermisosSobreFachada() {
		return rolesModPermisosSobreFachada;
	}

	public void setRolesModPermisosSobreFachada(
			RolesModPermisosSobreFachada rolesModPermisosSobreFachada) {
		this.rolesModPermisosSobreFachada = rolesModPermisosSobreFachada;
	}

	public PersonaFachada getPersonaFachada() {
		return personaFachada;
	}

	public void setPersonaFachada(PersonaFachada personaFachada) {
		this.personaFachada = personaFachada;
	}

	public PersonaDTO getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(PersonaDTO userSelected) {
		this.userSelected = userSelected;
	}

	public PersonaDTO getNuevoUser() {
		return nuevoUser;
	}

	public void setNuevoUser(PersonaDTO nuevoUser) {
		this.nuevoUser = nuevoUser;
	}

	public List<PersonaDTO> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<PersonaDTO> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public List<ModulosDTO> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<ModulosDTO> listaModulos) {
		this.listaModulos = listaModulos;
	}

	public List<ModulosDTO> getListaModulosEdit() {
		return listaModulosEdit;
	}

	public void setListaModulosEdit(List<ModulosDTO> listaModulosEdit) {
		this.listaModulosEdit = listaModulosEdit;
	}

	public List<ModulosDTO> getListaModulosVer() {
		return listaModulosVer;
	}

	public void setListaModulosVer(List<ModulosDTO> listaModulosVer) {
		this.listaModulosVer = listaModulosVer;
	}

	public List<PersonaDTO> getListaFiltered() {
		return listaFiltered;
	}

	public void setListaFiltered(List<PersonaDTO> listaFiltered) {
		this.listaFiltered = listaFiltered;
	}

	public List<RolesDTO> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<RolesDTO> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public List<RolesModulosDTO> getListaRolesModulos() {
		return listaRolesModulos;
	}

	public void setListaRolesModulos(List<RolesModulosDTO> listaRolesModulos) {
		this.listaRolesModulos = listaRolesModulos;
	}

	public List<RolesModPermisosUsrDTO> getListaRolesModPermisosUsr() {
		return listaRolesModPermisosUsr;
	}

	public void setListaRolesModPermisosUsr(
			List<RolesModPermisosUsrDTO> listaRolesModPermisosUsr) {
		this.listaRolesModPermisosUsr = listaRolesModPermisosUsr;
	}

	public List<RolesModPermisosSobreDTO> getListaRolesModPermisosSobre() {
		return listaRolesModPermisosSobre;
	}

	public void setListaRolesModPermisosSobre(
			List<RolesModPermisosSobreDTO> listaRolesModPermisosSobre) {
		this.listaRolesModPermisosSobre = listaRolesModPermisosSobre;
	}

	public List<SelectItem> getRolItems() {
		return rolItems;
	}

	public void setRolItems(List<SelectItem> rolItems) {
		this.rolItems = rolItems;
	}

	public SelectItem getItem() {
		return item;
	}

	public void setItem(SelectItem item) {
		this.item = item;
	}

	public RolesModUsuariosDTO getRolesModUsuarios() {
		return rolesModUsuarios;
	}

	public void setRolesModUsuarios(RolesModUsuariosDTO rolesModUsuarios) {
		this.rolesModUsuarios = rolesModUsuarios;
	}

	public RolesModPermisosUsrDTO getRolesModPermisosUsr() {
		return rolesModPermisosUsr;
	}

	public void setRolesModPermisosUsr(RolesModPermisosUsrDTO rolesModPermisosUsr) {
		this.rolesModPermisosUsr = rolesModPermisosUsr;
	}

	public RolesModPermisosSobreDTO getRolesModPermisosSobre() {
		return rolesModPermisosSobre;
	}

	public void setRolesModPermisosSobre(
			RolesModPermisosSobreDTO rolesModPermisosSobre) {
		this.rolesModPermisosSobre = rolesModPermisosSobre;
	}

	public PermisosDTO getPermisos() {
		return permisos;
	}

	public void setPermisos(PermisosDTO permisos) {
		this.permisos = permisos;
	}

	public RolesDTO getRoles() {
		return roles;
	}

	public void setRoles(RolesDTO roles) {
		this.roles = roles;
	}

	public boolean isNuevoRolActivo() {
		return nuevoRolActivo;
	}

	public void setNuevoRolActivo(boolean nuevoRolActivo) {
		this.nuevoRolActivo = nuevoRolActivo;
	}

	public boolean isSelectedRolActivo() {
		return selectedRolActivo;
	}

	public void setSelectedRolActivo(boolean selectedRolActivo) {
		this.selectedRolActivo = selectedRolActivo;
	}

	public String getSelectedClave() {
		return selectedClave;
	}

	public void setSelectedClave(String selectedClave) {
		this.selectedClave = selectedClave;
	}

	public String getSelectedUsuario() {
		return selectedUsuario;
	}

	public void setSelectedUsuario(String selectedUsuario) {
		this.selectedUsuario = selectedUsuario;
	}

	public String getSelectedNombre() {
		return selectedNombre;
	}

	public void setSelectedNombre(String selectedNombre) {
		this.selectedNombre = selectedNombre;
	}

	public String getSelectedRol() {
		return selectedRol;
	}

	public void setSelectedRol(String selectedRol) {
		this.selectedRol = selectedRol;
	}

	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public String getConfirmarPassword() {
		return confirmarPassword;
	}

	public void setConfirmarPassword(String confirmarPassword) {
		this.confirmarPassword = confirmarPassword;
	}

	public List<PermisosDTO> getListaPermisos() {
		return listaPermisos;
	}

	public void setListaPermisos(List<PermisosDTO> listaPermisos) {
		this.listaPermisos = listaPermisos;
	}

	@Override
	String getModulo() {
		// TODO Auto-generated method stub
		return null;
	}
}
