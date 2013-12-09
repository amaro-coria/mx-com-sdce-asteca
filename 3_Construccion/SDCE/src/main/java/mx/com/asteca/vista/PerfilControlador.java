package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.springframework.util.CollectionUtils;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.ModulosDTO;
import mx.com.asteca.comun.dto.PermisosBooleanDTO;
import mx.com.asteca.comun.dto.PermisosDTO;
import mx.com.asteca.comun.dto.RolesDTO;
import mx.com.asteca.comun.dto.RolesModulosDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.ModulosFachada;
import mx.com.asteca.fachada.PermisosFachada;
import mx.com.asteca.fachada.RolesFachada;
import mx.com.asteca.fachada.RolesModulosFachada;

@ManagedBean(name = Constantes.BEAN_PERFIL)
@ViewScoped
public class PerfilControlador extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String modulo = Constantes.MODULO_PERFIL;
	@ManagedProperty("#{rolesFachadaImpl}")
	private RolesFachada rolesFachada;
	@ManagedProperty("#{modulosFachadaImpl}")
	private ModulosFachada modulosFachadaImpl;
	@ManagedProperty("#{rolesModulosFachadaImpl}")
	private RolesModulosFachada rolesModulosFachada;
	@ManagedProperty("#{permisosFachadaImpl}")
	private PermisosFachada permisosFachada;

	private RolesDTO rolSelected;
	private RolesDTO rolNuevo;

	private List<RolesDTO> listaRoles;
	private List<ModulosDTO> listaModulos;
	private List<ModulosDTO> listaModulosEdit;
	private List<ModulosDTO> listaModulosVer;
	private List<RolesDTO> listaFiltered;
	private List<PermisosDTO> listaPermisos;
	private List<RolesModulosDTO> listaRolesModulos;
	private boolean selectedRolActivo;
	private boolean nuevoRolActivo;
	private String selectedClave;
	private String selectedNombre;

	public PerfilControlador() {
		rolNuevo = new RolesDTO();
		rolSelected = new RolesDTO();
		listaPermisos = new ArrayList<PermisosDTO>();
	}

	private PermisosBooleanDTO permisos;

	@PostConstruct
	public void populate() {
		setPermisos(super.stablishSessionPermissions());
	}

	/**
	 * @return the permisos
	 */
	public PermisosBooleanDTO getPermisos() {
		return permisos;
	}

	/**
	 * @param permisos
	 *            the permisos to set
	 */
	public void setPermisos(PermisosBooleanDTO permisos) {
		this.permisos = permisos;
		super.setAlta(permisos.isAlta());
		super.setBorrar(permisos.isBorrar());
		super.setCambios(permisos.isEdicion());
		super.setConsulta(permisos.isConsulta());
		super.setImpresion(permisos.isImpresion());
	}

	public void initListaRoles() {
		if (CollectionUtils.isEmpty(listaRoles)) {
			try {
				listaRoles = rolesFachada.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage("Error al obtener Roles");
			}
		}

	}

	public void initListaModulos() {
		if (CollectionUtils.isEmpty(listaModulos)) {
			try {
				listaModulos = modulosFachadaImpl.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage("Error al obtener Modulos");
			}
		}

	}

	public void initListaModulosEdit() {
		if (CollectionUtils.isEmpty(listaModulosEdit)) {
			try {
				listaModulosEdit = modulosFachadaImpl.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage("Error al obtener Modulos");
			}
		}

	}

	public void initListaModulosVer() {
		if (CollectionUtils.isEmpty(listaModulosVer)) {
			try {
				listaModulosVer = modulosFachadaImpl.getAll();
			} catch (FachadaException e) {
				super.addErrorMessage("Error al obtener Modulos");
			}
		}

	}

	private boolean validaDatos() {
		if (rolNuevo.getClave() == null || rolNuevo.getClave().isEmpty()) {
			return false;
		} else if (rolNuevo.getNombre() == null
				|| rolNuevo.getNombre().isEmpty()) {
			return false;
		}
		return true;
	}

	public void saveRol(ActionEvent e) {
		boolean b = validaDatos();
		if (b) {
			rolNuevo.setActivo(nuevoRolActivo == true ? (short) 1 : (short) 0);
			try {
				int idPermiso = 0;
				int idRol = 0;
				PermisosDTO permisos;
				RolesModulosDTO rolesModulos;
				idRol = rolesFachada.save(rolNuevo);
				if (listaRoles == null) {
					listaRoles = new ArrayList<RolesDTO>();
				}
				rolNuevo.setIdRol(idRol);
				if (!CollectionUtils.isEmpty(listaRoles)) {
					listaRoles.add(rolNuevo);
				} else {
					listaRoles = new ArrayList<RolesDTO>();
					listaRoles.add(rolNuevo);
				}
				for (ModulosDTO modulo : listaModulos) {
					if (modulo.isAlta() || modulo.isBorrar()
							|| modulo.isConsulta() || modulo.isEditar()
							|| modulo.isImprimir()) {
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

						idPermiso = permisosFachada.save(permisos);

						rolesModulos = new RolesModulosDTO();

						rolesModulos.setIdModulo(modulo.getIdModulo());
						rolesModulos.setIdPermisos(idPermiso);
						rolesModulos.setIdRol(idRol);
						rolesModulos.setActivo((short) 1);

						rolesModulosFachada.save(rolesModulos);
					}
				}
				RequestContext.getCurrentInstance().execute(
						"nuevoDialog.hide()");
				super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO,
						Constantes.NUEVO_REGISTRO_EXITOSO);
				addBitacora(Constantes.ACCION_NUEVO_REGISTRO,
						Constantes.ACCION_NUEVO_REGISTRO_EXITOSO_MENSAJE
								+ ":Rol  " + rolNuevo.getIdRol() + ":");
			} catch (FachadaException e1) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
						Constantes.ERROR_NUEVO_REGISTRO);
				addBitacora(Constantes.ACCION_NUEVO_REGISTRO,
						Constantes.ACCION_NUEVO_REGISTRO_FALLIDO_MENSAJE
								+ ":Rol:");
				return;
			}
			rolNuevo = new RolesDTO();
			initListaModulos();
		} else {
			super.addWarningMessage(Constantes.MESSAGE_TITLE_WARNING,
					Constantes.WARNING_NECESITAS_LLENAR_CAMPOS_REQUERIDOS);
		}
	}

	/**
	 * Cancela el guardar nuevo registro
	 * 
	 * @param e
	 */
	public void saveRolCancel(ActionEvent e) {
		rolNuevo = new RolesDTO();
	}

	public void deleteRol(ActionEvent e) {
		try {
			listaRolesModulos = rolesModulosFachada.buscarPorRol(rolSelected
					.getIdRol());
			for (RolesModulosDTO rolesModulos : listaRolesModulos) {

				rolesModulosFachada.remove(rolesModulos);

				permisosFachada.remove(permisosFachada.findByPK(rolesModulos
						.getIdPermisos()));
			}
			rolesFachada.remove(rolSelected);

			listaRoles.remove(rolSelected);
			addBitacora(Constantes.ACCION_DELETE_REGISTRO,
					Constantes.ACCION_DELETE_REGISTRO_EXITOSO_MENSAJE + ":Aula"
							+ rolSelected.getIdRol() + ":");
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_DELETE_REGISTRO);
			addBitacora(Constantes.ACCION_DELETE_REGISTRO,
					Constantes.ACCION_DELETE_REGISTRO_FALLIDO_MENSAJE + ":Rol "
							+ rolSelected.getIdRol() + ":");
			return;
		}
		super.addInfoMessage(Constantes.DELETE_REGISTRO_EXITOSO);
	}

	public void updateRol(ActionEvent e) {
		int idPermiso = 0;
		int index = 0;
		PermisosDTO permisos;
		RolesModulosDTO rolModulo;
		rolSelected
				.setActivo(selectedRolActivo == true ? (short) 1 : (short) 0);
		if (selectedClave != null && !selectedClave.isEmpty()) {
			rolSelected.setClave(selectedClave);
		}
		if (selectedNombre != null && !selectedNombre.isEmpty()) {
			rolSelected.setNombre(selectedNombre);
		}
		try {
			rolesFachada.update(rolSelected);
			int indexListFilter = listaRoles.indexOf(rolSelected);
			if (indexListFilter > 0) {
				listaRoles.set(indexListFilter, rolSelected);
			}
			for (ModulosDTO modulo : listaModulosEdit) {
				for (RolesModulosDTO rolesModulos : listaRolesModulos) {
					if (rolesModulos.getIdModulo().equals(modulo.getIdModulo())) {
						index += 1;
						for (PermisosDTO permiso : listaPermisos) {
							if (permiso.getIdPermiso() == rolesModulos
									.getIdPermisos()) {
								permiso.setAlta(modulo.isAlta() ? (short) 1
										: (short) 0);
								permiso.setBorrar(modulo.isBorrar() ? (short) 1
										: (short) 0);
								permiso.setCambios(modulo.isEditar() ? (short) 1
										: (short) 0);
								permiso.setConsulta(modulo.isConsulta() ? (short) 1
										: (short) 0);
								permiso.setImpresion(modulo.isImprimir() ? (short) 1
										: (short) 0);

								permisosFachada.update(permiso);
							}
						}

					}
				}
				if (index == 0) {
					if (modulo.isAlta() || modulo.isBorrar()
							|| modulo.isConsulta() || modulo.isEditar()
							|| modulo.isImprimir()) {
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

						idPermiso = permisosFachada.save(permisos);

						rolModulo = new RolesModulosDTO();

						rolModulo.setIdModulo(modulo.getIdModulo());
						rolModulo.setIdPermisos(idPermiso);
						rolModulo.setIdRol(rolSelected.getIdRol());
						rolModulo.setActivo((short) 1);

						rolesModulosFachada.save(rolModulo);
					}
				}
				index = 0;

			}
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR,
					Constantes.ERROR_UPDATE_REGISTRO);
			addBitacora(Constantes.ACCION_UPDATE_REGISTRO,
					Constantes.ACCION_UPDATE_REGISTRO_FALLIDO_MENSAJE + ":Rol:");
			return;
		}
		setSelectedClave("");
		setSelectedNombre("");
		initListaModulos();
		super.addInfoMessage(Constantes.MESSAGE_TITLE_INFO,
				Constantes.UPDATE_REGISTRO_EXITOSO);
		addBitacora(Constantes.ACCION_UPDATE_REGISTRO,
				Constantes.ACCION_UPDATE_REGISTRO_EXITOSO_MENSAJE + ":Rol "
						+ rolSelected.getIdRol() + ":");
	}

	public void editar(ActionEvent e) {
		initListaModulos();
		listaRolesModulos = rolesModulosFachada.buscarPorRol(rolSelected
				.getIdRol());
		if (listaPermisos.size() > 0) {
			listaPermisos.clear();
		}
		for (RolesModulosDTO rolesModulos : listaRolesModulos) {
			try {
				listaPermisos.add(permisosFachada.findByPK(rolesModulos
						.getIdPermisos()));
			} catch (FachadaException e1) {
				e1.printStackTrace();
			}
			initListaModulosEdit();
			for (ModulosDTO modulo : listaModulosEdit) {
				if (rolesModulos.getIdModulo().equals(modulo.getIdModulo())) {
					for (PermisosDTO permiso : listaPermisos) {
						if (permiso.getIdPermiso() == rolesModulos
								.getIdPermisos()) {
							modulo.setAlta(permiso.getAlta().equals((short) 0) ? false
									: true);
							modulo.setBorrar(permiso.getBorrar().equals(
									(short) 0) ? false : true);
							modulo.setConsulta(permiso.getConsulta().equals(
									(short) 0) ? false : true);
							modulo.setEditar(permiso.getCambios().equals(
									(short) 0) ? false : true);
							modulo.setImprimir(permiso.getImpresion().equals(
									(short) 0) ? false : true);

						}
					}
				}
			}
		}

	}

	public void ver(ActionEvent e) {
		initListaModulos();
		listaRolesModulos = rolesModulosFachada.buscarPorRol(rolSelected
				.getIdRol());
		if (listaPermisos.size() > 0) {
			listaPermisos.clear();
		}
		initListaModulosVer();
		for (RolesModulosDTO rolesModulos : listaRolesModulos) {
			try {
				listaPermisos.add(permisosFachada.findByPK(rolesModulos
						.getIdPermisos()));
			} catch (FachadaException e1) {
				e1.printStackTrace();
			}
			for (ModulosDTO modulo : listaModulosVer) {
				if (rolesModulos.getIdModulo().equals(modulo.getIdModulo())) {
					for (PermisosDTO permiso : listaPermisos) {
						if (permiso.getIdPermiso() == rolesModulos
								.getIdPermisos()) {
							modulo.setAlta(permiso.getAlta().equals((short) 0) ? false
									: true);
							modulo.setBorrar(permiso.getBorrar().equals(
									(short) 0) ? false : true);
							modulo.setConsulta(permiso.getConsulta().equals(
									(short) 0) ? false : true);
							modulo.setEditar(permiso.getCambios().equals(
									(short) 0) ? false : true);
							modulo.setImprimir(permiso.getImpresion().equals(
									(short) 0) ? false : true);

						}
					}
				}
			}
		}

	}

	public void cancelDeleteRol(ActionEvent e) {
		setSelectedClave("");
		setSelectedNombre("");
	}

	private void limpiarModulos() {
		if (listaModulosEdit.size() > 0) {
			listaModulosEdit.clear();
		}
		for (ModulosDTO modulo : listaModulos) {
			modulo.setAlta(false);
			modulo.setBorrar(false);
			modulo.setConsulta(false);
			modulo.setEditar(false);
			modulo.setImprimir(false);
		}
	}

	public RolesDTO getRolSelected() {
		return rolSelected;
	}

	public void setRolSelected(RolesDTO rolSelected) {
		this.rolSelected = rolSelected;
	}

	public RolesDTO getRolNuevo() {
		return rolNuevo;
	}

	public void setRolNuevo(RolesDTO rolNuevo) {
		this.rolNuevo = rolNuevo;
	}

	public List<RolesDTO> getListaRoles() {
		initListaRoles();
		return listaRoles;
	}

	public void setListaRoles(List<RolesDTO> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public List<ModulosDTO> getListaModulos() {
		initListaModulos();
		return listaModulos;
	}

	public void setListaModulos(List<ModulosDTO> listaModulos) {
		this.listaModulos = listaModulos;
	}

	public List<RolesDTO> getListaFiltered() {
		return listaFiltered;
	}

	public void setListaFiltered(List<RolesDTO> listaFiltered) {
		this.listaFiltered = listaFiltered;
	}

	public boolean isSelectedRolActivo() {
		return selectedRolActivo;
	}

	public void setSelectedRolActivo(boolean selectedRolActivo) {
		this.selectedRolActivo = selectedRolActivo;
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

	public RolesFachada getRolesFachada() {
		return rolesFachada;
	}

	public void setRolesFachada(RolesFachada rolesFachada) {
		this.rolesFachada = rolesFachada;
	}

	public ModulosFachada getModulosFachadaImpl() {
		return modulosFachadaImpl;
	}

	public void setModulosFachadaImpl(ModulosFachada modulosFachada) {
		this.modulosFachadaImpl = modulosFachada;
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

	public List<ModulosDTO> getListaModulosEdit() {
		initListaModulosEdit();
		return listaModulosEdit;
	}

	public void setListaModulosEdit(List<ModulosDTO> listaModulosEdit) {
		this.listaModulosEdit = listaModulosEdit;
	}

	public List<ModulosDTO> getListaModulosVer() {
		initListaModulosVer();
		return listaModulosVer;
	}

	public void setListaModulosVer(List<ModulosDTO> listaModulosVer) {
		this.listaModulosVer = listaModulosVer;
	}

	public List<PermisosDTO> getListaPermisos() {
		return listaPermisos;
	}

	public void setListaPermisos(List<PermisosDTO> listaPermisos) {
		this.listaPermisos = listaPermisos;
	}

	public List<RolesModulosDTO> getListaRolesModulos() {
		return listaRolesModulos;
	}

	public void setListaRolesModulos(List<RolesModulosDTO> listaRolesModulos) {
		this.listaRolesModulos = listaRolesModulos;
	}

	@Override
	String getModulo() {
		return modulo;
	}
}