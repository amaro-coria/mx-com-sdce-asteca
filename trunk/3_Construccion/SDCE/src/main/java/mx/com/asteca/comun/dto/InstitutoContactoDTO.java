package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class InstitutoContactoDTO implements Serializable{

	private int idInstitutoContacto;
	private PersonaDTO dtoPersona;
	private int idPuesto;
	private String puesto;
	/**
	 * @return the idInstitutoContacto
	 */
	public int getIdInstitutoContacto() {
		return idInstitutoContacto;
	}
	/**
	 * @param idInstitutoContacto the idInstitutoContacto to set
	 */
	public void setIdInstitutoContacto(int idInstitutoContacto) {
		this.idInstitutoContacto = idInstitutoContacto;
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
	 * @return the idPuesto
	 */
	public int getIdPuesto() {
		return idPuesto;
	}
	/**
	 * @param idPuesto the idPuesto to set
	 */
	public void setIdPuesto(int idPuesto) {
		this.idPuesto = idPuesto;
	}
	/**
	 * @return the puesto
	 */
	public String getPuesto() {
		return puesto;
	}
	/**
	 * @param puesto the puesto to set
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
}
