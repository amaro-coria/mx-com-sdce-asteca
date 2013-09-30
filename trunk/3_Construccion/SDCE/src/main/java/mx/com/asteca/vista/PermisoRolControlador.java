package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.model.SelectItem;

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

@ManagedBean(name = Constantes.BEAN_PERMISO_ROL)
@ViewScoped
public class PermisoRolControlador extends BaseController implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String modulo = Constantes.MODULO_PERMISOS;
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

	/* Obtener el idRol,idRolModUsr de rolesModUsuarios por medio del idPersona */
	/* Obtener Lista de modulos de acceso con idRol de rolesModulos */
	/*
	 * Obtener Lista idRolModPerUsr de rolesModPermisosUsr mediante el
	 * idRolModUsr,idRolMod
	 */
	/*
	 * Obtener Lista de idPermisos de rolesModPermisosSobre mediante el
	 * idRolModPerUsr
	 */
	/* Obtener Lista de permisos asociados al usuario mediante idPermisos */
	/*********************************************************************/
	/* Orden de Borrado */
	/* permisos */
	/* rolesModUsuarios */
	/* rolesModPermisosUsr */
	/* rolesModPermisosSobre */
	/*********************************************************************/
	/* Edicion de permisos */
	/* personas contraseña */
	/* Permisos */

	public PermisoRolControlador() {
		rolesModUsuarios = new RolesModUsuariosDTO();
		userSelected = new PersonaDTO();
		nuevoUser = new PersonaDTO();
		rolItems = new ArrayList<SelectItem>();
		listaModulos = new ArrayList<ModulosDTO>();
		listaModulosEdit = new ArrayList<ModulosDTO>();
		listaModulosVer = new ArrayList<ModulosDTO>();
		listaPermisos = new ArrayList<PermisosDTO>();
	}

	public void editar(ActionEvent ev) {
		try {
			rolesModUsuarios = rolesModUsuariosFachada
					.buscarPorUsuario(userSelected.getIdPersona());
			listaRolesModulos = rolesModulosFachada
					.buscarPorRol(rolesModUsuarios.getIdRol());
			initListaModulosEdit();
			
			roles = rolesFachada.findByPK(rolesModUsuarios.getIdRol());

			
			
			rolesModUsuarios = rolesModUsuariosFachada
					.buscarPorUsuario(userSelected.getIdPersona());
			listaRolesModPermisosUsr = rolesModPermisosUsrFachada
					.buscarPorRolModUsuario(rolesModUsuarios.getIdRolModUsr());
			selectedRolActivo = rolesModUsuarios.equals((short) 1) ? true:false;
			for (RolesModPermisosUsrDTO rolesModPermisosUsr : listaRolesModPermisosUsr) {
				listaRolesModPermisosSobre = rolesModPermisosSobreFachada
						.buscarPorRolModPermisosUsr(rolesModPermisosUsr
								.getIdRolModPerUsr());
				for (RolesModPermisosSobreDTO rolesModPermisosSobre : listaRolesModPermisosSobre) {
					listaPermisos.add(permisosFachada
							.findByPK(rolesModPermisosSobre.getIdPermisos()));
					for (RolesModulosDTO rolesModulos : listaRolesModulos) {
						for (ModulosDTO modulo : listaModulosEdit) {
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

	public void ver(ActionEvent ev) {
		try {
			rolesModUsuarios = rolesModUsuariosFachada
					.buscarPorUsuario(userSelected.getIdPersona());
			listaRolesModulos = rolesModulosFachada
					.buscarPorRol(rolesModUsuarios.getIdRol());
			initListaModulosVer();

			roles = rolesFachada.findByPK(rolesModUsuarios.getIdRol());
			
			rolesModUsuarios = rolesModUsuariosFachada
					.buscarPorUsuario(userSelected.getIdPersona());
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

	public void saveUsuario(ActionEvent ev) {
		rolesModUsuarios = new RolesModUsuariosDTO();
		rolesModUsuarios.setActivo(nuevoRolActivo == true ? (short) 1
				: (short) 0);
		try {
			int idRolesModUsuarios = 0;
			int idRolesModPermisosUsr = 0;
			int idPermisos = 0;
			if ((userSelected.getUsuario() == null || userSelected.getUsuario().length() == 0)
					&& personaFachada.getUser(selectedUsuario) == null) {
				userSelected.setUsuario(selectedUsuario);
				if (userSelected.getPassword().equals(confirmarPassword)) {
					personaFachada.update(userSelected);

					rolesModUsuarios.setIdPersona(userSelected.getIdPersona());
					rolesModUsuarios.setIdRol(Integer.parseInt(idRol));

					idRolesModUsuarios = rolesModUsuariosFachada
							.save(rolesModUsuarios);
					for (RolesModulosDTO rolesModulos : listaRolesModulos) {
						rolesModPermisosUsr = new RolesModPermisosUsrDTO();

						rolesModPermisosUsr.setIdRolesModulos(rolesModulos
								.getIdRolMod());
						rolesModPermisosUsr
								.setIdRolesModUsuarios(idRolesModUsuarios);

						idRolesModPermisosUsr = rolesModPermisosUsrFachada
								.save(rolesModPermisosUsr);
						for (ModulosDTO modulo : listaModulos) {
							permisos = new PermisosDTO();

							permisos.setAlta(modulo.isAlta() ? (short) 1
									: (short) 0);
							permisos.setBorrar(modulo.isBorrar() ? (short) 1
									: (short) 0);
							permisos.setCambios(modulo.isEditar() ? (short) 1
									: (short) 0);
							permisos.setConsulta(modulo.isConsulta() ? (short) 1
									: (short) 0);
							permisos.setImpresion(modulo.isImprimir() ? (short) 1
									: (short) 0);

							idPermisos = permisosFachada.save(permisos);

							rolesModPermisosSobre = new RolesModPermisosSobreDTO();
							rolesModPermisosSobre.setIdPermisos(idPermisos);
							rolesModPermisosSobre
									.setIdRolesModPermisosUsr(idRolesModPermisosUsr);

							rolesModPermisosSobreFachada
									.save(rolesModPermisosSobre);

						}

					}

				} else {
					super.addErrorMessage("La contraseña no coincide");
				}
			} else {
				super.addErrorMessage("Ya existe un usuario registrado");
			}

		} catch (Exception e) {
			e.printStackTrace();
			super.addErrorMessage("Error al guardar Usuario");
		}

	}

	public void saveUsuarioCancel(ActionEvent ev) {

	}

	public void cancelDeleteUsuario(ActionEvent ev) {

	}

	public void updateUsuario(ActionEvent ev) {
		rolesModUsuarios = new RolesModUsuariosDTO();
		rolesModUsuarios.setActivo(nuevoRolActivo == true ? (short) 1
				: (short) 0);
		try {
			
			if ((userSelected.getUsuario() != null || userSelected.getUsuario().length() > 0)
					&& personaFachada.getUser(selectedUsuario) == null) {
				if (userSelected.getPassword().equals(confirmarPassword)) {
					personaFachada.update(userSelected);
					rolesModUsuarios = rolesModUsuariosFachada
							.buscarPorUsuario(userSelected.getIdPersona());
					listaRolesModulos = rolesModulosFachada
							.buscarPorRol(rolesModUsuarios.getIdRol());
					initListaModulosEdit();
					
					roles = rolesFachada.findByPK(rolesModUsuarios.getIdRol());

					
					
					rolesModUsuarios = rolesModUsuariosFachada
							.buscarPorUsuario(userSelected.getIdPersona());
					listaRolesModPermisosUsr = rolesModPermisosUsrFachada
							.buscarPorRolModUsuario(rolesModUsuarios.getIdRolModUsr());
					selectedRolActivo = rolesModUsuarios.equals((short) 1) ? true:false;
					for (RolesModPermisosUsrDTO rolesModPermisosUsr : listaRolesModPermisosUsr) {
						listaRolesModPermisosSobre = rolesModPermisosSobreFachada
								.buscarPorRolModPermisosUsr(rolesModPermisosUsr
										.getIdRolModPerUsr());
						for (RolesModPermisosSobreDTO rolesModPermisosSobre : listaRolesModPermisosSobre) {
							listaPermisos.add(permisosFachada
									.findByPK(rolesModPermisosSobre.getIdPermisos()));
							for (RolesModulosDTO rolesModulos : listaRolesModulos) {
								for (ModulosDTO modulo : listaModulosEdit) {
									if (rolesModulos.getIdModulo().equals(
											modulo.getIdModulo())) {
										for (PermisosDTO permiso : listaPermisos) {
											if (permiso.getIdPermiso() == rolesModulos
													.getIdPermisos()) {
												
												permiso.setAlta(modulo.isAlta()?(short)1:(short)0);
												permiso.setBorrar(modulo.isBorrar()?(short)1:(short)0);
												permiso.setCambios(modulo.isEditar()?(short)1:(short)0);
												permiso.setConsulta(modulo.isConsulta()?(short)1:(short)0);
												permiso.setImpresion(modulo.isImprimir()?(short)1:(short)0);
												
												permisosFachada.update(permiso);
											}
										}
									}
								}
								
							}
						}
					}					
					int indexListFilter = listaPersonas.indexOf(userSelected);
					if(indexListFilter > 0){
						listaPersonas.set(indexListFilter, userSelected);
					}
				} else {
					super.addErrorMessage("La contraseña no coincide");
				}
			} else {
				super.addErrorMessage("Ya existe un usuario registrado");
			}

		} catch (Exception e) {
			e.printStackTrace();
			super.addErrorMessage("Error al guardar Usuario");
		}
	}

	public void deleteUsuario(ActionEvent ev) {
		try {
		rolesModUsuarios = rolesModUsuariosFachada
				.buscarPorUsuario(userSelected.getIdPersona());
		listaRolesModulos = rolesModulosFachada
				.buscarPorRol(rolesModUsuarios.getIdRol());
		initListaModulosEdit();
		
		roles = rolesFachada.findByPK(rolesModUsuarios.getIdRol());

		
		
		rolesModUsuarios = rolesModUsuariosFachada
				.buscarPorUsuario(userSelected.getIdPersona());
		listaRolesModPermisosUsr = rolesModPermisosUsrFachada
				.buscarPorRolModUsuario(rolesModUsuarios.getIdRolModUsr());
		selectedRolActivo = rolesModUsuarios.equals((short) 1) ? true:false;
		for (RolesModPermisosUsrDTO rolesModPermisosUsr : listaRolesModPermisosUsr) {
			listaRolesModPermisosSobre = rolesModPermisosSobreFachada
					.buscarPorRolModPermisosUsr(rolesModPermisosUsr
							.getIdRolModPerUsr());
			for (RolesModPermisosSobreDTO rolesModPermisosSobre : listaRolesModPermisosSobre) {
				listaPermisos.add(permisosFachada
						.findByPK(rolesModPermisosSobre.getIdPermisos()));
				for (RolesModulosDTO rolesModulos : listaRolesModulos) {
					for (ModulosDTO modulo : listaModulosEdit) {
						if (rolesModulos.getIdModulo().equals(
								modulo.getIdModulo())) {
							for (PermisosDTO permiso : listaPermisos) {
								if (permiso.getIdPermiso() == rolesModulos
										.getIdPermisos()) {									
									permisosFachada.remove(permiso);
								}
							}
						}
					}
					
				}
				rolesModPermisosSobreFachada.remove(rolesModPermisosSobre);
			}
			rolesModPermisosUsrFachada.remove(rolesModPermisosUsr);
		}
		rolesModUsuariosFachada.remove(rolesModUsuarios);
		userSelected.setUsuario("");
		personaFachada.update(userSelected);
		
		int indexListFilter = listaPersonas.indexOf(userSelected);
		if(indexListFilter > 0){
			listaPersonas.set(indexListFilter, userSelected);
		}

	} catch (FachadaException e) {
		super.addErrorMessage(Constantes.ERROR_NUEVO_REGISTRO);
	}

	}

	public void updateModulos(ValueChangeEvent ev) {
		try {
			idRol = ev.getNewValue().toString();
			if (!idRol.equals("-1")) {
				listaRolesModulos = rolesModulosFachada.buscarPorRol(Integer
						.parseInt(idRol));
				initListaModulos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.addErrorMessage("Error a obtener Modulos");
		}
	}

	private void initRolSelectItems() {
		try {

			listaRoles = rolesFachada.getAll();

			if (!CollectionUtils.isEmpty(rolItems)) {
				rolItems.clear();
				rolItems.add(new SelectItem(-1, "Seleccione"));
			} else {
				rolItems.add(new SelectItem(-1, "Seleccione"));
			}

			for (RolesDTO rol : listaRoles) {
				item = new SelectItem(rol.getIdRol(), rol.getNombre());
				rolItems.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.addErrorMessage(Constantes.ERROR_NUEVO_REGISTRO);
		}
	}

	private void initListaPersonas() {
		if (CollectionUtils.isEmpty(listaPersonas)) {
			try {
				listaPersonas = personaFachada.getAll();
				initListaRoles();
			} catch (FachadaException e) {
				e.printStackTrace();
				super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			} catch (Exception e) {
				e.printStackTrace();
				super.addErrorMessage(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}

		}
	}

	public void initListaModulos() {
		if (CollectionUtils.isEmpty(listaModulos)) {
			try {
				for (RolesModulosDTO rolesModulos : listaRolesModulos) {
					listaModulos.add(modulosFachada.findByPK(rolesModulos
							.getIdModulo()));
					listaPermisos.add(permisosFachada.findByPK(rolesModulos
							.getIdPermisos()));
					for (ModulosDTO modulo : listaModulos) {
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

			} catch (FachadaException e) {
				super.addErrorMessage("Error al obtener Modulos");
			}
		}

	}

	public void initListaModulosEdit() {

		if (CollectionUtils.isEmpty(listaModulosEdit)) {
			try {
				for (RolesModulosDTO rolesModulos : listaRolesModulos) {
					listaModulosEdit.add(modulosFachada.findByPK(rolesModulos
							.getIdModulo()));
					listaPermisos.add(permisosFachada.findByPK(rolesModulos
							.getIdPermisos()));
					for (ModulosDTO modulo : listaModulosEdit) {
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

	private void initListaRoles() {
		for (PersonaDTO persona : listaPersonas) {
			try {
				super.addErrorMessage(persona.getNombre());
				rolesModUsuarios = rolesModUsuariosFachada
						.buscarPorUsuario(persona.getIdPersona());

				roles = rolesFachada.findByPK(rolesModUsuarios.getIdRol());

				persona.setRol(roles.getNombre());
				persona.setActivo(rolesModUsuarios.getActivo());
			} catch (Exception e) {

			}

		}

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

	public List<PersonaDTO> getListaPersonas() {
		initListaPersonas();
		initRolSelectItems();
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

	public RolesModUsuariosDTO getRolesModUsuarios() {
		return rolesModUsuarios;
	}

	public void setRolesModUsuarios(RolesModUsuariosDTO rolesModUsuarios) {
		this.rolesModUsuarios = rolesModUsuarios;
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

	public String getSelectedClave() {
		return selectedClave;
	}

	public void setSelectedClave(String selectedClave) {
		this.selectedClave = selectedClave;
	}

	public String getSelectedNombre() {
		return selectedNombre;
	}

	public void setSelectedNombre(String selectedNombre) {
		this.selectedNombre = selectedNombre;
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

	public List<SelectItem> getRolItems() {
		return rolItems;
	}

	public void setRolItems(List<SelectItem> rolItems) {
		this.rolItems = rolItems;
	}

	public boolean isSelectedRolActivo() {
		return selectedRolActivo;
	}

	public void setSelectedRolActivo(boolean selectedRolActivo) {
		this.selectedRolActivo = selectedRolActivo;
	}

	public String getSelectedRol() {
		return selectedRol;
	}

	public void setSelectedRol(String selectedRol) {
		this.selectedRol = selectedRol;
	}

	public String getConfirmarPassword() {
		return confirmarPassword;
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

	public SelectItem getItem() {
		return item;
	}

	public void setItem(SelectItem item) {
		this.item = item;
	}

	public String getSelectedUsuario() {
		return selectedUsuario;
	}

	public void setSelectedUsuario(String selectedUsuario) {
		this.selectedUsuario = selectedUsuario;
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
		return modulo;
	}

}
