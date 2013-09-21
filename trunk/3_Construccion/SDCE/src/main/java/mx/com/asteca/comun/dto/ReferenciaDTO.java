package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class ReferenciaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idReferencia;
	private PersonaDTO dtoPersona;
	private DomicilioDTO dtoDomicilio;
	private int idAlumno;
	/**
	 * @return the idReferencia
	 */
	public int getIdReferencia() {
		return idReferencia;
	}
	/**
	 * @param idReferencia the idReferencia to set
	 */
	public void setIdReferencia(int idReferencia) {
		this.idReferencia = idReferencia;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idReferencia;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReferenciaDTO other = (ReferenciaDTO) obj;
		if (idReferencia != other.idReferencia)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReferenciaDTO [idReferencia=" + idReferencia + ", dtoPersona="
				+ dtoPersona + ", dtoDomicilio=" + dtoDomicilio + "]";
	}
	/**
	 * @return the idAlumno
	 */
	public int getIdAlumno() {
		return idAlumno;
	}
	/**
	 * @param idAlumno the idAlumno to set
	 */
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	
	
}
