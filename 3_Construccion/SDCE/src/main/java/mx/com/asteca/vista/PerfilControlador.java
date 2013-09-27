package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.util.CollectionUtils;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.ModulosDTO;
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
	@ManagedProperty("#{rolesFachadaImpl}")
	private RolesFachada rolesFachada;
	@ManagedProperty("#{modulosFachadaImpl}")
	private ModulosFachada modulosFachada;
	@ManagedProperty("#{rolesModulosFachadaImpl}")
	private RolesModulosFachada rolesModulosFachada;
	@ManagedProperty("#{permisosFachadaImpl}")
	private PermisosFachada permisosFachada;
	
	private RolesDTO rolSelected;
	private RolesDTO rolNuevo;

	
	private List<RolesDTO> listaRoles;
	private List<ModulosDTO> listaModulos;
	private List<ModulosDTO> listaModulosTmp;
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
		listaModulosTmp = new ArrayList<ModulosDTO>();
		initListaModulos();
	}
	
	
	public void initListaRoles() {
		if(CollectionUtils.isEmpty(listaRoles)) {
			try {
				listaRoles = rolesFachada.getAll();
			} catch (FachadaException e) {
				
			}
		}
		
	}
	
	public void initListaModulos() {
		if(CollectionUtils.isEmpty(listaModulos)) {
			try {
				listaModulos = modulosFachada.getAll();
			} catch (FachadaException e) {
				
			}
		}
		
	}
	
	public void saveRol(ActionEvent e) {
		rolNuevo
				.setActivo(nuevoRolActivo == true ? (short) 1 : (short) 0);
		try {
			int idPermiso = 0;
			int idRol = 0;
			PermisosDTO permisos;
			RolesModulosDTO rolesModulos;
			idRol = rolesFachada.save(rolNuevo);
			listaRoles.add(rolNuevo);
			for(ModulosDTO modulo:listaModulos) {
				if(modulo.isAlta() || modulo .isBorrar() || modulo.isConsulta() || modulo.isEditar() || modulo.isImprimir()) {
					permisos = new PermisosDTO();
					permisos.setAlta(modulo.isAlta()?(short)1:(short)0);
					permisos.setBorrar(modulo.isBorrar()?(short)1:(short)0);
					permisos.setCambios(modulo.isEditar()?(short)1:(short)0);
					permisos.setConsulta(modulo.isConsulta()?(short)1:(short)0);
					permisos.setImpresion(modulo.isImprimir()?(short)1:(short)0);
					
					idPermiso = permisosFachada.save(permisos);
					
					rolesModulos = new RolesModulosDTO();
					
					rolesModulos.setIdModulo(modulo.getIdModulo());
					rolesModulos.setIdPermisos(idPermiso);
					rolesModulos.setIdRol(idRol);
					rolesModulos.setActivo((short)1);
					
					rolesModulosFachada.save(rolesModulos);
				}
			}
			
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_NUEVO_REGISTRO);
			return;
		}
		rolNuevo = new RolesDTO();
		initListaModulos();
		super.addInfoMessage(Constantes.NUEVO_REGISTRO_EXITOSO);
	}
	
	
	/**
	 * Cancela el guardar nuevo registro
	 * @param e
	 */
	public void saveRolCancel(ActionEvent e){
		rolNuevo = new RolesDTO();
	}	
	
	public void deleteRol(ActionEvent e) {
		try {
			rolesFachada.remove(rolSelected);
			listaRoles.remove(rolSelected);
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_DELETE_REGISTRO);
			return;
		}
		super.addInfoMessage(Constantes.DELETE_REGISTRO_EXITOSO);
	}
	
	public void updateRol(ActionEvent e) {
		rolSelected.setActivo(selectedRolActivo == true ? (short) 1
				: (short) 0);
		if(selectedClave != null && !selectedClave.isEmpty()){
			rolSelected.setClave(selectedClave);
		}
		if(selectedNombre != null && !selectedNombre.isEmpty()){
			rolSelected.setNombre(selectedNombre);
		}
		try {
			rolesFachada.update(rolSelected);
			int indexListFilter = listaRoles.indexOf(rolSelected);
			if(indexListFilter > 0){
				listaRoles.set(indexListFilter, rolSelected);
			}
			for(ModulosDTO modulo:listaModulos) {
				for(RolesModulosDTO rolesModulos:listaRolesModulos) {
					if(rolesModulos.getIdModulo().equals(modulo.getIdModulo())) {
						for(PermisosDTO permiso:listaPermisos) {
							if(permiso.getIdPermiso() == rolesModulos.getIdPermisos()) {
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
		} catch (FachadaException e1) {
			super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_UPDATE_REGISTRO);
			return;
		}
		setSelectedClave("");
		setSelectedNombre("");
		initListaModulos();
		super.addInfoMessage(Constantes.UPDATE_REGISTRO_EXITOSO);
	}
	
	public void editar(ActionEvent e) {
		initListaModulos();
		listaRolesModulos = rolesModulosFachada.buscarPorRol(rolSelected.getIdRol());
		if(listaPermisos.size() > 0) {
			listaPermisos.clear();
		}
		if(listaModulosTmp.size() > 0) {
			listaModulosTmp.clear();
		}
		for(RolesModulosDTO rolesModulos:listaRolesModulos) {
			try {
				listaPermisos.add(permisosFachada.findByPK(rolesModulos.getIdPermisos()));
			} catch (FachadaException e1) {
				e1.printStackTrace();
			}
			for(ModulosDTO modulo:listaModulos) {
				if(rolesModulos.getIdModulo().equals(modulo.getIdModulo())) {
					for(PermisosDTO permiso:listaPermisos) {
						if(permiso.getIdPermiso() == rolesModulos.getIdPermisos()) {
							modulo.setAlta(permiso.getAlta().equals((short)0)?false:true);
							modulo.setBorrar(permiso.getBorrar().equals((short)0)?false:true);
							modulo.setConsulta(permiso.getConsulta().equals((short)0)?false:true);
							modulo.setEditar(permiso.getCambios().equals((short)0)?false:true);
							modulo.setImprimir(permiso.getImpresion().equals((short)0)?false:true);
							
							listaModulosTmp.add(modulo);
						}
					}
				}
			}
			listaModulos.clear();
			listaModulos.addAll(listaModulosTmp);
		}
		
	}
	
	public void cancelDeleteRol(ActionEvent e){
		setSelectedClave("");
		setSelectedNombre("");
	}
	private void limpiarModulos() {
		if(listaModulosTmp.size() > 0) {
			listaModulosTmp.clear();
		}
		for(ModulosDTO modulo:listaModulos) {
			modulo.setAlta(false);
			modulo.setBorrar(false);
			modulo.setConsulta(false);
			modulo.setEditar(false);
			modulo.setImprimir(false);
			
			listaModulosTmp.add(modulo);
		}
		listaModulos.clear();
		listaModulos.addAll(listaModulosTmp);
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
}
