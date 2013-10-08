package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.List;

public class InstructorDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idInstructor;
	private DomicilioDTO dtoDomicilio;
	private short idTipo;
	private String tipo;
	private PersonaDTO dtoPersona;
	private String noEmpleado;
	private List<InstructorMateriaDTO> listInstructoresMaterias;
	private List<InstructorDocumentoDTO> listInstructoresDocumentos;
	private short idEstatus;
	private String estatus;
	private String nombre;
	/**
	 * @return the idInstructor
	 */
	public int getIdInstructor() {
		return idInstructor;
	}
	/**
	 * @param idInstructor the idInstructor to set
	 */
	public void setIdInstructor(int idInstructor) {
		this.idInstructor = idInstructor;
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
	 * @return the idTipo
	 */
	public short getIdTipo() {
		return idTipo;
	}
	/**
	 * @param idTipo the idTipo to set
	 */
	public void setIdTipo(short idTipo) {
		this.idTipo = idTipo;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * @return the listInstructoresMaterias
	 */
	public List<InstructorMateriaDTO> getListInstructoresMaterias() {
		return listInstructoresMaterias;
	}
	/**
	 * @param listInstructoresMaterias the listInstructoresMaterias to set
	 */
	public void setListInstructoresMaterias(
			List<InstructorMateriaDTO> listInstructoresMaterias) {
		this.listInstructoresMaterias = listInstructoresMaterias;
	}
	/**
	 * @return the listInstructoresDocumentos
	 */
	public List<InstructorDocumentoDTO> getListInstructoresDocumentos() {
		return listInstructoresDocumentos;
	}
	/**
	 * @param listInstructoresDocumentos the listInstructoresDocumentos to set
	 */
	public void setListInstructoresDocumentos(
			List<InstructorDocumentoDTO> listInstructoresDocumentos) {
		this.listInstructoresDocumentos = listInstructoresDocumentos;
	}
	/**
	 * @return the idEstatus
	 */
	public short getIdEstatus() {
		return idEstatus;
	}
	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(short idEstatus) {
		this.idEstatus = idEstatus;
	}
	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
