/**
 * 
 */
package mx.com.asteca.vista;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.InstitutoContactoDTO;
import mx.com.asteca.comun.dto.InstitutoDTO;
import mx.com.asteca.comun.dto.PermisosBooleanDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.InstitutoFachada;

/**
 * @author JAMARO
 *
 */
@ManagedBean(name = Constantes.BEAN_INSTITUTOS)
@ViewScoped
public class InstitutoControlador extends BaseController implements
		Serializable {
	
	private static final String modulo = Constantes.MODULO_INSTITUTO;
	
	@ManagedProperty("#{institutoFachadaImpl}")
	private transient InstitutoFachada fachada;
	
	private InstitutoDTO item;
	
	private InstitutoContactoDTO director;
	private InstitutoContactoDTO representante;
private PermisosBooleanDTO permisos;
	
	@PostConstruct
	public void populate(){
		setPermisos(super.stablishSessionPermissions());
	}

	/**
	 * @return the permisos
	 */
	public PermisosBooleanDTO getPermisos() {
		return permisos;
	}



	/**
	 * @param permisos the permisos to set
	 */
	public void setPermisos(PermisosBooleanDTO permisos) {
		this.permisos = permisos;
		super.setAlta(permisos.isAlta());
		super.setBorrar(permisos.isBorrar());
		super.setCambios(permisos.isEdicion());
		super.setConsulta(permisos.isConsulta());
		super.setImpresion(permisos.isImpresion());
	}
	
	private void initItem(){
		if(item == null){
			try {
				List<InstitutoDTO> institutos = fachada.getAll();
				item = institutos.get(0);
				List<InstitutoContactoDTO> list = item.getListContactos();
				InstitutoContactoDTO dto1 = list.get(0);
				InstitutoContactoDTO dto2 = list.get(1);
				if(dto1.getIdPuesto() == 1){
					director = dto1;
				}else if(dto1.getIdPuesto() == 2){
					representante = dto1;
				}
				if(dto2.getIdPuesto() == 1){
					director = dto2;
				}else if(dto2.getIdPuesto() == 2){
					representante = dto2;
				}
			} catch (FachadaException e) {
				super.addErrorMessage(Constantes.MESSAGE_TITLE_ERROR, Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO);
			}
		}
	}

	/**
	 * @return the item
	 */
	public InstitutoDTO getItem() {
		initItem();
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(InstitutoDTO item) {
		this.item = item;
	}

	/**
	 * @return the fachada
	 */
	public InstitutoFachada getFachada() {
		return fachada;
	}

	/**
	 * @return the director
	 */
	public InstitutoContactoDTO getDirector() {
		return director;
	}

	/**
	 * @param director the director to set
	 */
	public void setDirector(InstitutoContactoDTO director) {
		this.director = director;
	}

	/**
	 * @return the representante
	 */
	public InstitutoContactoDTO getRepresentante() {
		return representante;
	}

	/**
	 * @param representante the representante to set
	 */
	public void setRepresentante(InstitutoContactoDTO representante) {
		this.representante = representante;
	}

	/**
	 * @param fachada the fachada to set
	 */
	public void setFachada(InstitutoFachada fachada) {
		this.fachada = fachada;
	}

	@Override
	String getModulo() {
		return modulo;
	}

	
	

}
