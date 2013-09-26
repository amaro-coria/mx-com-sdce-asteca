/**
 * 
 */
package mx.com.asteca.comun.dto;

import java.io.Serializable;

/**
 * @author JAMARO
 *
 */
public class AdministrativoDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idAmin;
	private String noEmpleado;
	private DomicilioDTO dtoDomicilio;
	private PersonaDTO dtoPersona;
	private String nombreCompleto;
	/**
	 * @return the idAmin
	 */
	public int getIdAmin() {
		return idAmin;
	}
	/**
	 * @param idAmin the idAmin to set
	 */
	public void setIdAmin(int idAmin) {
		this.idAmin = idAmin;
	}
	/**
	 * @return the noEmpleado
	 */
	public String getNoEmpleado() {
		return noEmpleado;
	}
	/**
	 * @param noEmpleado the noEmpleado to set
	 */
	public void setNoEmpleado(String noEmpleado) {
		this.noEmpleado = noEmpleado;
	}
	/**
	 * @return the dtoDomicilio
	 */
	public DomicilioDTO getDtoDomicilio() {
		return dtoDomicilio;
	}
	/**
	 * @param dtoDomicilio the dtoDomicilio to set
	 */
	public void setDtoDomicilio(DomicilioDTO dtoDomicilio) {
		this.dtoDomicilio = dtoDomicilio;
	}
	/**
	 * @return the dtoPersona
	 */
	public PersonaDTO getDtoPersona() {
		return dtoPersona;
	}
	/**
	 * @param dtoPersona the dtoPersona to set
	 */
	public void setDtoPersona(PersonaDTO dtoPersona) {
		this.dtoPersona = dtoPersona;
	}
	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
}
